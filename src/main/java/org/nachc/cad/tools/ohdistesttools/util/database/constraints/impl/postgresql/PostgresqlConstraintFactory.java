package org.nachc.cad.tools.ohdistesttools.util.database.constraints.impl.postgresql;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.cad.tools.ohdistesttools.util.database.connection.ConnectionFactory;
import org.nachc.cad.tools.ohdistesttools.util.database.constraints.def.IConstraintFactory;
import org.nachc.cad.tools.ohdistesttools.util.database.dvo.ConstraintDvo;
import org.nachc.cad.tools.ohdistesttools.util.properties.app.OhdsiTestToolsAppProperties;
import org.yaorma.database.Data;
import org.yaorma.database.Database;
import org.yaorma.database.Row;

public class PostgresqlConstraintFactory implements IConstraintFactory {

	public List<ConstraintDvo> getCdmConstraints() {
		String schemaName = OhdsiTestToolsAppProperties.getPostgresCdmSchema();
		return getConstraints(schemaName);
	}

	public List<ConstraintDvo> getWebApiConstraints() {
		String schemaName = OhdsiTestToolsAppProperties.getPostgresWebApiSchema();
		return getConstraints(schemaName);
	}

	public List<ConstraintDvo> getConstraints(String schemaName) {
		ArrayList<ConstraintDvo> rtn = new ArrayList<ConstraintDvo>();
		Connection conn = ConnectionFactory.getCdmConnection();
		try {
			String sqlString = getSqlString();
			Data data = Database.query(sqlString,  schemaName, conn);
			String prevIndexName = null;
			ConstraintDvo dvo = null;
			for(Row row : data) {
				String tableName = row.get("tableName");
				String constraintName = row.get("constraintName");
				String constraintType = row.get("constraintType");
				if(constraintName.equals(prevIndexName) == false) {
					if(dvo != null) {
						rtn.add(dvo);
					}
					dvo = new ConstraintDvo();
					dvo.setSchemaName(schemaName);
					dvo.setTableName(tableName);
					dvo.setConstraintName(constraintName);
					dvo.setConstraintType(constraintType);
				}
				dvo.getColumns().add(row.get("columnName"));
				prevIndexName = constraintName;
			}
			rtn.add(dvo);
			return rtn;
		} finally {
			Database.close(conn);
		}
	}

	private String getSqlString() {
		String sqlString = "";
		sqlString += "select \n";
		sqlString += "	ns.nspname as schema, \n";
		sqlString += "	con.contype as constraint_type, \n";
		sqlString += "	t.relname as table_name, \n";
		sqlString += "	con.conname as constraint_name, \n";
		sqlString += "	a.attname as column_name \n";
		sqlString += "from \n";
		sqlString += "	pg_constraint con \n";
		sqlString += "	join pg_class t on con.conrelid = t.oid \n";
		sqlString += "	join pg_namespace ns on t.relnamespace = ns.oid \n";
		sqlString += "	join pg_attribute a on 1=1 \n";
		sqlString += "		and a.attrelid = t.oid \n";
		sqlString += "		and a.attnum = any(con.conkey) \n";
		sqlString += "where 1=1 \n";
		sqlString += "	and ns.nspname = ? \n";
		sqlString += "order by  \n";
		sqlString += "	1,2,3,4 \n";
		return sqlString;
	}
	
}

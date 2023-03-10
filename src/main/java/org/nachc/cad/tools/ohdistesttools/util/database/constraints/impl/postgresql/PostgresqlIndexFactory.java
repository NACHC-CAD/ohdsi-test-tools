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

public class PostgresqlIndexFactory implements IConstraintFactory {

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
				String indexName = row.get("indexName");
				String indexDef = row.get("sqlString");
				if(indexName.equals(prevIndexName) == false) {
					if(dvo != null) {
						rtn.add(dvo);
					}
					dvo = new ConstraintDvo();
					dvo.setSchemaName(schemaName);
					dvo.setTableName(tableName);
					dvo.setConstraintName(indexName);
					dvo.setSqlString(indexDef);
				}
				dvo.getColumns().add(row.get("columnName"));
				prevIndexName = indexName;
			}
			if(dvo != null) {
				rtn.add(dvo);
			}
			return rtn;
		} finally {
			Database.close(conn);
		}
	}

	private String getSqlString() {
		String sqlString = "";
		sqlString += "select \n";
		sqlString += "	ns.nspname as schema, \n";
		sqlString += "	t.relname as table_name, \n";
		sqlString += "	i.relname as index_name, \n";
		sqlString += "	a.attname as column_name, \n";
		sqlString += "	ixs.indexdef as sql_string \n";
		sqlString += "from \n";
		sqlString += "	pg_index ix \n"; 
		sqlString += "	join pg_class i on ix.indexrelid = i.oid \n";
		sqlString += "	join pg_class t on ix.indrelid = t.oid and t.relkind = 'r' \n";
		sqlString += "	join pg_namespace ns on t.relnamespace = ns.oid \n";
		sqlString += "	join pg_indexes ixs on 1=1 \n";
		sqlString += "		and ns.nspname = ixs.schemaname \n";
		sqlString += "		and t.relname = ixs.tablename \n";
		sqlString += "		and i.relname = ixs.indexname \n";
		sqlString += "	join pg_attribute a on 1=1 \n";
		sqlString += "		and a.attrelid = t.oid \n";
		sqlString += "		and a.attnum = any(ix.indkey) \n";
		sqlString += "where 1=1 \n";
		sqlString += "	and ns.nspname = ? \n";
		sqlString += "order by  \n";
		sqlString += "	1,2,3,4 \n";
		return sqlString;
	}
	
}

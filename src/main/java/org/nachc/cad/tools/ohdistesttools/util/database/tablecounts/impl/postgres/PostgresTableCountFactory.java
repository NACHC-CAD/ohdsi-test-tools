package org.nachc.cad.tools.ohdistesttools.util.database.tablecounts.impl.postgres;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.nachc.cad.tools.ohdistesttools.util.database.connection.ConnectionFactory;
import org.nachc.cad.tools.ohdistesttools.util.database.dvo.TableCountDvo;
import org.nachc.cad.tools.ohdistesttools.util.database.tablecounts.def.ITableCountFactory;
import org.yaorma.database.Data;
import org.yaorma.database.Database;
import org.yaorma.database.Row;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostgresTableCountFactory implements ITableCountFactory {

	@Override
	public List<TableCountDvo> getTableCounts(String schemaName, Connection conn) {
		ArrayList<TableCountDvo> rtn = new ArrayList<>();
		try {
			List<String> tableNames = getTableNames(schemaName, conn);
			for(String tableName : tableNames) {
				log.info("Getting count for " + tableName);
				Integer cnt = Database.count(schemaName + "." + tableName, conn);
				TableCountDvo dvo = new TableCountDvo();
				dvo.setSchemaName(schemaName);
				dvo.setTableName(tableName);
				dvo.setCount(cnt);
				rtn.add(dvo);
			}
		} finally {
			log.info("Closing connection...");
			Database.close(conn);
			log.info("Connection closed.");			
		}
		log.info("Done getting table counts.");
		return rtn;
	}

	public List<String> getTableNames(String schemaName, Connection conn) {
		ArrayList<String> rtn = new ArrayList<String>();
		String sqlString = "";
		sqlString += "select table_name from information_schema.tables  \n";
		sqlString += "where table_schema = ? \n";
		sqlString += "order by table_name \n";
		Data data = Database.query(sqlString, schemaName, conn);
		for(Row row : data) {
			rtn.add(row.get("tableName"));
		}
		return rtn;
	}
	
}

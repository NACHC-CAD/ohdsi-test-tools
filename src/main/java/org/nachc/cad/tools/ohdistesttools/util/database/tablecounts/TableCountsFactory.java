package org.nachc.cad.tools.ohdistesttools.util.database.tablecounts;

import java.sql.Connection;
import java.util.List;

import org.nachc.cad.tools.ohdistesttools.util.database.dvo.TableCountDvo;
import org.nachc.cad.tools.ohdistesttools.util.database.tablecounts.def.ITableCountFactory;
import org.nachc.cad.tools.ohdistesttools.util.database.tablecounts.impl.postgres.PostgresTableCountFactory;
import org.nachc.cad.tools.ohdistesttools.util.database.types.DbmsType;
import org.nachc.cad.tools.ohdistesttools.util.properties.app.OhdsiTestToolsAppProperties;

public class TableCountsFactory {

	private static ITableCountFactory getConstraintFactory() {
		ITableCountFactory rtn = null;
		DbmsType type = OhdsiTestToolsAppProperties.getDbmsType();
		if (type == DbmsType.POSTGRESQL) {
			rtn = new PostgresTableCountFactory();
		} else {
			throw new RuntimeException("Connection factory not defined for DBMS type: " + type);
		}
		return rtn;
	}

	public static List<TableCountDvo> getTableCounts(String schemaName, Connection conn) {
		return getConstraintFactory().getTableCounts(schemaName, conn);
	}

	public static List<String> getTableNames(String schemaName, Connection conn) {
		return getConstraintFactory().getTableNames(schemaName, conn);
	}

}

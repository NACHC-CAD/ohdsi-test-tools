package org.nachc.cad.tools.ohdistesttools.util.database.tablecounts.def;

import java.sql.Connection;
import java.util.List;

import org.nachc.cad.tools.ohdistesttools.util.database.dvo.TableCountDvo;

public interface ITableCountFactory {

	public List<TableCountDvo> getTableCounts(String schemaName, Connection conn);
	
	public List<String> getTableNames(String schemaName, Connection conn);

}

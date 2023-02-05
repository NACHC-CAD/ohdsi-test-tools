package org.nachc.cad.tools.ohdistesttools.util.database.tablecounts;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;
import org.nachc.cad.tools.ohdistesttools.util.database.connection.ConnectionFactory;
import org.nachc.cad.tools.ohdistesttools.util.database.dvo.TableCountDvo;
import org.nachc.cad.tools.ohdistesttools.util.properties.app.OhdsiTestToolsAppProperties;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TableCountsIntegrationTest {

	@Test
	public void runTests() {
		shouldGetTableNames();
		shouldGetTableCounts();
	}
	
	public void shouldGetTableNames() {
		log.info("------------------------");
		log.info("TABLE NAMES TEST:");
		log.info("Starting test...");
		Connection conn = ConnectionFactory.getCdmConnection();
		try {
			String schemaName = OhdsiTestToolsAppProperties.getPostgresCdmSchema();
			List<String> tableNames = TableCountsFactory.getTableNames(schemaName, conn);
			log.info("Got: " + tableNames.size() + " tables.");
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}
	
	public void shouldGetTableCounts() {
		log.info("------------------------");
		log.info("TABLE COUNTS TEST:");
		log.info("Starting test...");
		Connection conn = ConnectionFactory.getCdmConnection();
		try {
			String schemaName = OhdsiTestToolsAppProperties.getPostgresCdmSchema();
			List<TableCountDvo> tableCountsList = TableCountsFactory.getTableCounts(schemaName, conn);
			log.info("Got counts for: " + tableCountsList.size() + " tables.");
		} finally {
			Database.close(conn);
		}
		log.info("Done.");
	}
}

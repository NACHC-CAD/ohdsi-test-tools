package org.nachc.cad.tools.ohdistesttools.util.database.connection;

import java.sql.Connection;

import org.junit.Test;
import org.nachc.cad.tools.ohdistesttools.util.database.connection.ConnectionFactory;
import org.yaorma.database.Database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionFactoryIntegrationTest {

	@Test
	public void shouldGetBootstrapConnection() {
		log.info("Getting connection...");
		Connection conn = ConnectionFactory.getBootstrapConnection();
		log.info("Got connection: " + conn);
		log.info("Closing connection...");
		Database.close(conn);
		log.info("Done.");
	}
	
}

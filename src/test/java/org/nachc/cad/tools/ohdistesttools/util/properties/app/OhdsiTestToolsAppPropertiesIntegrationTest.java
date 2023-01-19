package org.nachc.cad.tools.ohdistesttools.util.properties.app;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OhdsiTestToolsAppPropertiesIntegrationTest {

	@Test
	public void shouldGetProperty() {
		log.info("Starting test...");
		String dbms = OhdsiTestToolsAppProperties.getDbmsType();
		log.info("dbms: " + dbms);
		assertTrue(dbms != null);
		log.info("Done.");
	}
	
}

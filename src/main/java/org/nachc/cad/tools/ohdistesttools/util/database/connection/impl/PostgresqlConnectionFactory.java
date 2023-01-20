package org.nachc.cad.tools.ohdistesttools.util.database.connection.impl;

import java.sql.Connection;
import java.sql.DriverManager;

import org.nachc.cad.tools.ohdistesttools.util.database.connection.def.IConnectionFactory;
import org.nachc.cad.tools.ohdistesttools.util.properties.app.OhdsiTestToolsAppProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostgresqlConnectionFactory implements IConnectionFactory {

	@Override
	public Connection getBootstrapConnection() {
		try {
			String url = OhdsiTestToolsAppProperties.getPostgresBootstrapUrl();
			String uid = OhdsiTestToolsAppProperties.getPostgresBootstrapUid();
			String pwd = OhdsiTestToolsAppProperties.getPostgresBootstrapPwd();
			String db = OhdsiTestToolsAppProperties.getPostgresOhdsiDbName();
			String schema = OhdsiTestToolsAppProperties.getPostgresCdmSchema();
			url += "/" + db;
			url += "?" + "user=" + uid;
			url += "&" + "password=" + pwd;
			url += "&" + "currentSchema=" + schema;
			log.info("Getting connection for url: \n" + url);
			Connection conn = DriverManager.getConnection(url);
			return conn;
		} catch (Exception exp) {
			throw (new RuntimeException(exp));
		}
	}
	
	@Override
	public Connection getCdmConnection() {
		try {
			String url = OhdsiTestToolsAppProperties.getPostgresBootstrapUrl();
			String uid = OhdsiTestToolsAppProperties.getPostgresBootstrapUid();
			String pwd = OhdsiTestToolsAppProperties.getPostgresBootstrapPwd();
			String db = OhdsiTestToolsAppProperties.getPostgresOhdsiDbName();
			String schema = OhdsiTestToolsAppProperties.getPostgresCdmSchema();
			url += "/" + db;
			url += "?" + "user=" + uid;
			url += "&" + "password=" + pwd;
			url += "&" + "currentSchema=" + schema;
			log.info("Getting connection for url: \n" + url);
			Connection conn = DriverManager.getConnection(url);
			return conn;
		} catch (Exception exp) {
			throw (new RuntimeException(exp));
		}
	}
	
}

package org.nachc.cad.tools.ohdistesttools.util.database;

import java.sql.Connection;

import org.nachc.cad.tools.ohdistesttools.util.database.def.IConnectionFactory;
import org.nachc.cad.tools.ohdistesttools.util.database.impl.PostgresqlConnectionFactory;
import org.nachc.cad.tools.ohdistesttools.util.database.types.DbmsType;
import org.nachc.cad.tools.ohdistesttools.util.properties.app.OhdsiTestToolsAppProperties;

public class ConnectionFactory implements IConnectionFactory {

	private IConnectionFactory connectionFactory;
	
	public ConnectionFactory() {
		DbmsType type = OhdsiTestToolsAppProperties.getDbmsType();
		if(type == DbmsType.POSTGRESQL) {
			this.connectionFactory = new PostgresqlConnectionFactory();
		} else {
			throw new RuntimeException("Connection factory not defined for DBMS type: " + type);
		}
	}
	
	@Override
	public Connection getBootstrapConnection() {
		return this.connectionFactory.getBootstrapConnection();
	}

	@Override
	public Connection getCdmConnection() {
		return this.connectionFactory.getCdmConnection();
	}

}

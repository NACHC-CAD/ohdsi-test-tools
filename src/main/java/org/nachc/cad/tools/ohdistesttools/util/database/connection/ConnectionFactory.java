package org.nachc.cad.tools.ohdistesttools.util.database.connection;

import java.sql.Connection;

import org.nachc.cad.tools.ohdistesttools.util.database.connection.def.IConnectionFactory;
import org.nachc.cad.tools.ohdistesttools.util.database.connection.impl.PostgresqlConnectionFactory;
import org.nachc.cad.tools.ohdistesttools.util.database.types.DbmsType;
import org.nachc.cad.tools.ohdistesttools.util.properties.app.OhdsiTestToolsAppProperties;

public class ConnectionFactory {

	private static IConnectionFactory getConnectionFactory() {
		IConnectionFactory rtn = null;
		DbmsType type = OhdsiTestToolsAppProperties.getDbmsType();
		if (type == DbmsType.POSTGRESQL) {
			rtn = new PostgresqlConnectionFactory();
		} else {
			throw new RuntimeException("Connection factory not defined for DBMS type: " + type);
		}
		return rtn;
	}

	public static Connection getBootstrapConnection() {
		return getConnectionFactory().getBootstrapConnection();
	}

	public static Connection getCdmConnection() {
		return getConnectionFactory().getCdmConnection();
	}

}

package org.nachc.cad.tools.ohdistesttools.util.database.connection.def;

import java.sql.Connection;

public interface IConnectionFactory {

	public Connection getBootstrapConnection();
	
	public Connection getCdmConnection();
	
}

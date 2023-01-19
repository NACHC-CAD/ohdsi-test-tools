package org.nachc.cad.tools.ohdistesttools.util.database.def;

import java.sql.Connection;

public interface IConnectionFactory {

	public Connection getBootstrapConnection();
	
	public Connection getCdmConnection();
	
}

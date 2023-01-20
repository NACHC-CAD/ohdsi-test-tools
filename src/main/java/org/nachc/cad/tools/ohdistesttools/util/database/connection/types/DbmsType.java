package org.nachc.cad.tools.ohdistesttools.util.database.connection.types;

public enum DbmsType {
	POSTGRESQL, MSSQL;

	public static DbmsType getType(String name) {
		if ("postgresql".equalsIgnoreCase(name)) {
			return POSTGRESQL;
		} else if ("mssql".equalsIgnoreCase(name)) {
			return MSSQL;
		} else {
			throw new RuntimeException("No DbmsType defined for string: " + name);
		}
	}

}

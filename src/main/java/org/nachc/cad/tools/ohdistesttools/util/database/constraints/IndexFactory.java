package org.nachc.cad.tools.ohdistesttools.util.database.constraints;

import java.util.List;

import org.nachc.cad.tools.ohdistesttools.util.database.constraints.def.IIndexFactory;
import org.nachc.cad.tools.ohdistesttools.util.database.constraints.impl.postgresql.PostgresqlIndexFactory;
import org.nachc.cad.tools.ohdistesttools.util.database.dvo.IndexDvo;
import org.nachc.cad.tools.ohdistesttools.util.database.types.DbmsType;
import org.nachc.cad.tools.ohdistesttools.util.properties.app.OhdsiTestToolsAppProperties;

public class IndexFactory {

	private static IIndexFactory getIndexFactory() {
		IIndexFactory rtn = null;
		DbmsType type = OhdsiTestToolsAppProperties.getDbmsType();
		if (type == DbmsType.POSTGRESQL) {
			rtn = new PostgresqlIndexFactory();
		} else {
			throw new RuntimeException("Connection factory not defined for DBMS type: " + type);
		}
		return rtn;
	}

	public static List<IndexDvo> getCdmIndexes() {
		return getIndexFactory().getCdmIndexes();
	}

	public static List<IndexDvo> getWebApiIndexes() {
		return getIndexFactory().getWebApiIndexes();
	}

	public static List<IndexDvo> getIndexes(String schemaName) {
		return getIndexFactory().getIndexes(schemaName);
	}

}

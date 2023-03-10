package org.nachc.cad.tools.ohdistesttools.util.database.constraints;

import java.util.List;

import org.nachc.cad.tools.ohdistesttools.util.database.constraints.def.IConstraintFactory;
import org.nachc.cad.tools.ohdistesttools.util.database.constraints.impl.postgresql.PostgresqlIndexFactory;
import org.nachc.cad.tools.ohdistesttools.util.database.dvo.ConstraintDvo;
import org.nachc.cad.tools.ohdistesttools.util.database.types.DbmsType;
import org.nachc.cad.tools.ohdistesttools.util.properties.app.OhdsiTestToolsAppProperties;

public class IndexFactory {

	private static IConstraintFactory getIndexFactory() {
		IConstraintFactory rtn = null;
		DbmsType type = OhdsiTestToolsAppProperties.getDbmsType();
		if (type == DbmsType.POSTGRESQL) {
			rtn = new PostgresqlIndexFactory();
		} else {
			throw new RuntimeException("Connection factory not defined for DBMS type: " + type);
		}
		return rtn;
	}

	public static List<ConstraintDvo> getCdmIndexes() {
		return getIndexFactory().getCdmConstraints();
	}

	public static List<ConstraintDvo> getWebApiIndexes() {
		return getIndexFactory().getWebApiConstraints();
	}

	public static List<ConstraintDvo> getIndexes(String schemaName) {
		return getIndexFactory().getConstraints(schemaName);
	}

}

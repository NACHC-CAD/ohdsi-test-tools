package org.nachc.cad.tools.ohdistesttools.util.database.constraints.def;

import java.util.List;

import org.nachc.cad.tools.ohdistesttools.util.database.dvo.ConstraintDvo;

public interface IConstraintFactory {

	public List<ConstraintDvo> getCdmConstraints();
	
	public List<ConstraintDvo> getWebApiConstraints();
	
	public List<ConstraintDvo> getConstraints(String schemaName);
	
}

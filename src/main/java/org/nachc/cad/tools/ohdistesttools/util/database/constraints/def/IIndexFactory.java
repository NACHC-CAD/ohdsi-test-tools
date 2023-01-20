package org.nachc.cad.tools.ohdistesttools.util.database.constraints.def;

import java.util.List;

import org.nachc.cad.tools.ohdistesttools.util.database.dvo.IndexDvo;

public interface IIndexFactory {

	public List<IndexDvo> getCdmIndexes();
	
	public List<IndexDvo> getWebApiIndexes();
	
	public List<IndexDvo> getIndexes(String schemaName);
	
}

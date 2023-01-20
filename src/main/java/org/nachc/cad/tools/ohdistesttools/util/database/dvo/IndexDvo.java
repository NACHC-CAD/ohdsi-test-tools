package org.nachc.cad.tools.ohdistesttools.util.database.dvo;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndexDvo {

	private String schemaName;
	
	private String tableName;
	
	private String indexName;

	private String indexDefinition;
	
	private List<String> columns = new ArrayList<String>();
	
	public int getColumnCount() {
		return this.columns.size();
	}
	
	public String getColumnListAsString() {
		String rtn = "";
		for(String col : this.columns) {
			if("".equals(rtn) == false) {
				rtn += ", ";
				rtn += col;
			}
		}
		return rtn;
	}
	
}

package org.nachc.cad.tools.ohdistesttools.util.database.constraints;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.nachc.cad.tools.ohdistesttools.util.database.dvo.IndexDvo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IndexFactoryIntegrationTest {

	@Test
	public void shouldGetIndexes() {
		showIndexList(IndexFactory.getWebApiIndexes());
		showIndexList(IndexFactory.getCdmIndexes());
	}

	private void showIndexList(List<IndexDvo> indexList) {
		log.info("Starting test...");
		log.info("Got " + indexList.size() + " indexes");
		String msg = "";
		msg += "\n";
		msg += "\tcols\t";
		msg += StringUtils.rightPad("schema_name", 32);
		msg += StringUtils.rightPad("table_name", 32);
		msg += StringUtils.rightPad("index_name", 50);
		msg += StringUtils.rightPad("column_list_as_string", 50);
		msg += StringUtils.rightPad("index_definition", 50);
		for (IndexDvo dvo : indexList) {
			msg += "\n\t";
			msg += "(" + dvo.getColumnCount() + ")\t";
			msg += StringUtils.rightPad(dvo.getSchemaName(), 32);
			msg += StringUtils.rightPad(dvo.getTableName(), 32);
			msg += StringUtils.rightPad(dvo.getIndexName(), 50);
			msg += StringUtils.rightPad(dvo.getColumnListAsString(), 50);
			msg += StringUtils.rightPad(dvo.getIndexDefinition(), 50);
		}
		log.info(msg);
		log.info("Got " + indexList.size() + " indexes");
		log.info("Done.");
	}

}

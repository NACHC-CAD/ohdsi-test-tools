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
		msg += "\tcols\t" + StringUtils.rightPad("schema_name", 32) + StringUtils.rightPad("table_name", 32) + StringUtils.rightPad("index_name", 32);
		for (IndexDvo dvo : indexList) {
			msg += "\n\t";
			msg += "(" + dvo.getColumnCount() + ")\t";
			msg += StringUtils.rightPad(dvo.getSchemaName(), 32);
			msg += StringUtils.rightPad(dvo.getTableName(), 32);
			msg += StringUtils.rightPad(dvo.getIndexName(), 50);
			msg += dvo.getColumnListAsString();
			msg += dvo.getIndexDefinition() + "\t";
		}
		log.info(msg);
		log.info("Got " + indexList.size() + " indexes");
		log.info("Done.");
	}

}

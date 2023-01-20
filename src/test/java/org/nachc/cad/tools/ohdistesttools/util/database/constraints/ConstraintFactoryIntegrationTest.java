package org.nachc.cad.tools.ohdistesttools.util.database.constraints;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.nachc.cad.tools.ohdistesttools.util.database.dvo.ConstraintDvo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConstraintFactoryIntegrationTest {

	@Test
	public void shouldGetIndexes() {
		showIndexList(ConstraintFactory.getWebApiIndexes());
		showIndexList(ConstraintFactory.getCdmIndexes());
	}

	private void showIndexList(List<ConstraintDvo> indexList) {
		log.info("Starting test...");
		log.info("Got " + indexList.size() + " indexes");
		String msg = "";
		msg += "\n";
		msg += "\ttype\tcols\t";
		msg += StringUtils.rightPad("schema_name", 32);
		msg += StringUtils.rightPad("table_name", 32);
		msg += StringUtils.rightPad("index_name", 50);
		msg += StringUtils.rightPad("column_list_as_string", 50);
		msg += StringUtils.rightPad("sql_string", 50);
		for (ConstraintDvo dvo : indexList) {
			msg += "\n\t";
			msg += dvo.getConstraintType() + "\t";
			msg += "(" + dvo.getColumnCount() + ")\t";
			msg += StringUtils.rightPad(dvo.getSchemaName(), 32);
			msg += StringUtils.rightPad(dvo.getTableName(), 32);
			msg += StringUtils.rightPad(dvo.getConstraintName(), 50);
			msg += StringUtils.rightPad(dvo.getColumnListAsString(), 50);
			msg += StringUtils.rightPad(dvo.getSqlString(), 50);
		}
		log.info(msg);
		log.info("Got " + indexList.size() + " constraints");
		log.info("Done.");
	}

}

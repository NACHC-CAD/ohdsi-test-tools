package org.nachc.cad.tools.ohdistesttools;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(WildcardPatternSuite.class)

@SuiteClasses({ "**/*IntegrationTest.class" })
public class RunAllIntegrationTests {

	@BeforeClass
	public static void setup() {
	}

	@AfterClass
	public static void cleanup() {
	}

}

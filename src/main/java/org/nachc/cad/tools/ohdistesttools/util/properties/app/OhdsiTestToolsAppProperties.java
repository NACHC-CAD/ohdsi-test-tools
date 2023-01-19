package org.nachc.cad.tools.ohdistesttools.util.properties.app;

import java.io.File;
import java.util.Properties;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.props.PropertiesUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OhdsiTestToolsAppProperties {

	private static final Properties PROPS;
	
	static {
		String propsFilePath = FileUtil.getAsString("/auth/app.properties");
		propsFilePath = propsFilePath.trim();
		File file = new File(propsFilePath);
		PROPS = PropertiesUtil.getAsProperties(file);
	}

	public static String get(String key) {
		return PROPS.getProperty(key);
	}
	
	public static String getDbmsType() {
		return PROPS.getProperty("DbmsType");
	}
}

package org.nachc.cad.tools.ohdistesttools.util.properties.app;

import java.io.File;
import java.util.Properties;

import org.nachc.cad.tools.ohdistesttools.util.database.types.DbmsType;

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
	
	public static String getDbmsTypeName() {
		return PROPS.getProperty("DbmsType");
	}

	public static DbmsType getDbmsType() {
		String name = getDbmsTypeName();
		DbmsType rtn = DbmsType.getType(name);
		return rtn;
	}
	
	// 
	// postgresql getters
	//

	public static String getPostgresOhdsiDbName() {
		return PROPS.getProperty("PostgresOhdsiDbName");
	}
	
	public static String getPostgresCdmSchema() {
		return PROPS.getProperty("PostgresCdmSchema");
	}

	public static String getPostgresBootstrapUrl() {
		return PROPS.getProperty("PostgresBootstrapUrl");
	}
	
	public static String getPostgresBootstrapUid() {
		return PROPS.getProperty("PostgresBootstrapUid");
	}
	
	public static String getPostgresBootstrapPwd() {
		return PROPS.getProperty("PostgresBootstrapPwd");
	}
	
	public static String getPostgresWebApiSchema() {
		return PROPS.getProperty("PostgresWebApiSchema");
	}

}

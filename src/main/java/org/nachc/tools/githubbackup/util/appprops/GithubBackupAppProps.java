package org.nachc.tools.githubbackup.util.appprops;

import java.io.File;
import java.util.Properties;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.props.PropertiesUtil;

public class GithubBackupAppProps {
	
	private static File FILE = FileUtil.getFile("/props/github-backup-app.properties");
	
	private static final Properties PROPS = PropertiesUtil.getAsProperties(FILE);

	private static final String TOKEN;
	
	static {
		String fileLocation = get("GitHubUserTokenLocation");
		File file = new File(fileLocation);
		TOKEN = FileUtil.getAsString(file).trim();
	}
	
	public static String get(String key) {
		return PROPS.getProperty(key);
	}

	public static String getUserName() {
		return get("UserName");
	}
	
	public static String getToken() {
		return TOKEN;
	}
	
	public static String getTargetDir() {
		return get("TargetDir");
	}
	
}

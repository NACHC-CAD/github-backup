package org.nachc.tools.githubbackup.util.appprops;

import java.io.File;
import java.util.Properties;

import com.nach.core.util.file.FileUtil;
import com.nach.core.util.props.PropertiesUtil;

public class GithubBackupAppProps {
	
	private static File FILE = FileUtil.getFile("/props/github-backup-app.properties");

	private static final Properties PROPS = PropertiesUtil.getAsProperties(FILE);

	public static String get(String key) {
		return PROPS.getProperty(key);
	}

	public static String getGithubCertFileLocation() {
		return get("GithubCertFileLocation");
	}

	public static String getGhExeLocation() {
		return get("GhExeLocation");
	}
	
}

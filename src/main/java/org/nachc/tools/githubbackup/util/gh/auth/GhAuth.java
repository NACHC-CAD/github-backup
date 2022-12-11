package org.nachc.tools.githubbackup.util.gh.auth;

import java.io.File;

import org.nachc.tools.githubbackup.util.appprops.GithubBackupAppProps;

import com.nach.core.util.file.FileUtil;

public class GhAuth {

	public static void exec() {
		try {
			String cmd = GithubBackupAppProps.getGhExeLocation();
			String[] args = {"status"};
			File root = FileUtil.getFile("/");
			Runtime.getRuntime().exec(cmd, args, root);
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}
	
}

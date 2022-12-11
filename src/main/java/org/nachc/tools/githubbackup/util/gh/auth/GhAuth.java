package org.nachc.tools.githubbackup.util.gh.auth;

import java.io.File;

import org.nachc.tools.githubbackup.util.appprops.GithubBackupAppProps;

import com.nach.core.util.file.FileUtil;

import lombok.Getter;

@Getter
public class GhAuth {

	private String output;
	
	private String error;
	
	private int exitCode;
	
	public void exec() {
		try {

			// params
//			String cmd = GithubBackupAppProps.getGhExeLocation();
			String cmd = "gh";
			String[] args = {"status"};
			File root = FileUtil.getFile("/");

			// exec
			Process process = new ProcessBuilder(cmd, "status").start();
			this.output = FileUtil.getAsString(process.getInputStream());
			this.error = FileUtil.getAsString(process.getErrorStream());
			this.exitCode = process.waitFor();
			
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}
	
}

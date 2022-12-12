package org.nachc.tools.githubbackup.util.gh.auth;

import java.io.File;

import org.nachc.tools.githubbackup.util.appprops.GithubBackupAppProps;

import com.nach.core.util.file.FileUtil;

import lombok.Getter;

@Getter
public class GhAuthStatus {

	private String output;
	
	private String error;
	
	private int exitCode;
	
	public void exec() {
		try {

			// params
			String exe = GithubBackupAppProps.getGhExeLocation();

			// exec
			Process process = new ProcessBuilder(exe, "auth", "status").start();
			this.output = FileUtil.getAsString(process.getInputStream());
			this.error = FileUtil.getAsString(process.getErrorStream());
			this.exitCode = process.waitFor();
			
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}
	
}

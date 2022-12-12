package org.nachc.tools.githubbackup.util.gh.auth;

import java.io.File;
import java.util.Map;

import org.nachc.tools.githubbackup.util.appprops.GithubBackupAppProps;

import com.nach.core.util.file.FileUtil;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class GhAuth {

	private String exe = GithubBackupAppProps.getGhExeLocation();;
	
	private String tokenLoc = GithubBackupAppProps.getGithubCertFileLocation();
	
	private File tokenFile = new File(tokenLoc);
	
	private String token = FileUtil.getAsString(tokenFile);
	
	private String output;
	
	private String error;
	
	private int exitCode;
	
	public void exec() {
		try {
			log.info("using token: " + token);
			ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\GitHub CLI\\gh.exe", "auth", "login");
			Map<String, String> params = pb.environment();
			params.put("--with-token", token);
			Process process = pb.start();
			this.output = FileUtil.getAsString(process.getInputStream());
			this.error = FileUtil.getAsString(process.getErrorStream());
			this.exitCode = process.waitFor();
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}
	
}

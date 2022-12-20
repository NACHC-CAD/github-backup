package org.nachc.tools.githubbackup.util.gh;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import com.nach.core.util.file.FileUtil;

import lombok.Getter;

@Getter
public class Clone {

	private String out;

	private String err;

	private int exitCode;

	public void exec(String url, File targetDir) {
		try {
			FileUtil.mkdirs(targetDir);
			ProcessBuilder pb = new ProcessBuilder("git.exe", "clone", url);
			pb.directory(targetDir);
			Process process = pb.start();
			readOut(process);
			readErr(process);
			this.exitCode = process.waitFor();
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	private void readOut(Process process) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
			sb.append(System.getProperty("line.separator"));
		}
		this.out = sb.toString();
	}
	
	private void readErr(Process process) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
			sb.append(System.getProperty("line.separator"));
		}
		this.err = sb.toString();
	}
	
}

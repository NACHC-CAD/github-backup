package org.nachc.tools.githubbackup.util.gh;

import java.io.File;

import com.nach.core.util.file.FileUtil;

import lombok.Getter;

@Getter
public class Clone {

	private String out;
	
	private String error;

	private int exitCode;
	
	public void exec(String url, File targetDir) {
	    try {
	    	FileUtil.mkdirs(targetDir);
	        ProcessBuilder pb = new ProcessBuilder("git.exe", "clone", url);
	        pb.directory(targetDir);
	        Process process = pb.start();
	        this.out = FileUtil.getAsString(process.getInputStream());
	        this.error = FileUtil.getAsString(process.getErrorStream());
	        this.exitCode = process.waitFor();
	    } catch(Exception exp) {
	        throw new RuntimeException(exp);
	    }	}
	

	
}

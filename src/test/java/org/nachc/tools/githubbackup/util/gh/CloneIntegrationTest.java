package org.nachc.tools.githubbackup.util.gh;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CloneIntegrationTest {
	
	private static final File DIR = new File("C:\\temp\\GITHUB_BACKUP_TEST");

	@Test
	public void shouldCloneProject() {
		log.info("Starting test...");
		// delete the output dir
		log.info("Deleting output dir: " + FileUtil.getCanonicalPath(DIR));
		FileUtil.rmdir(DIR);
		// clone a NACHC-CAD project
		String url = "https://github.com/NACHC-CAD/curlew";
		log.info("Cloning project: " + url);
		Clone clone = new Clone();
		clone.exec(url, DIR);
		log.info("Exit Code: " + clone.getExitCode());
		assertTrue(clone.getExitCode() == 0);
		log.info("Done.");
	}
	
}

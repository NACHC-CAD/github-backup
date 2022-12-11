package org.nachc.tools.githubbackup.util.appprops;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GithubBackupAppPropsIntegrationTest {

	@Test
	public void shouldGetProperty() {
		log.info("Starting test...");
		String certFileLoc = GithubBackupAppProps.getGithubCertFileLocation();
		log.info("GithubCertFileLocation: " + certFileLoc);
		log.info("Done.");
	}
	
}

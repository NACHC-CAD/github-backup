package org.nachc.tools.githubbackup.util.appprops;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GithubBackupAppPropsIntegrationTest {

	@Test
	public void shouldGetToken() {
		log.info("Starting test...");
		String token = GithubBackupAppProps.getToken();
		log.info("Got token: " + token);
		assertTrue(token != null);
		log.info("Done.");
	}
	
}

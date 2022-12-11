package org.nachc.tools.githubbackup.util.gh.auth;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GhAuthIntegrationTest {

	@Test
	public void shouldDoAuth() {
		log.info("Starting test...");
		GhAuth.exec();
		log.info("Done.");
	}
	
}

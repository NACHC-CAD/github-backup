package org.nachc.tools.githubbackup.util.gh.auth;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GhAuthStatusIntegrationTest {

	@Test
	public void shouldDoAuth() {
		log.info("Starting test...");
		GhAuthStatus auth = new GhAuthStatus();
		auth.exec();
		log.info("ErrorOut:\n" + auth.getError()); 
		log.info("Response:  " + auth.getOutput()); 
		log.info("Got code:  " + auth.getExitCode()); 
		assertTrue(auth.getExitCode() == 0);
		log.info("Done.");
	}
	
}

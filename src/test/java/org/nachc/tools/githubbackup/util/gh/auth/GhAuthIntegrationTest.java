package org.nachc.tools.githubbackup.util.gh.auth;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GhAuthIntegrationTest {

	@Test
	public void shouldDoAuth() {
		log.info("Starting test...");
		GhAuth auth = new GhAuth();
		auth.exec();
		log.info("Got response: " + auth.getOutput()); 
		log.info("Got error:    " + auth.getError()); 
		log.info("Got code:     " + auth.getExitCode()); 
		
		log.info("Done.");
	}
	
}

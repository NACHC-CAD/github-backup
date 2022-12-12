package org.nachc.tools.githubbackup.util.gh.auth;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GhAuthIntegrationTest {

	@Test
	public void shouldDoAuth() {
		log.info("Starting test...");
		GhAuth auth = new GhAuth();
		log.info("exe:      " + FileUtil.getCanonicalPath(auth.getExe()));
		log.info("tokenLoc: " + auth.getTokenLoc());
		auth.exec();
		log.info("ErrorOut:\n" + auth.getError()); 
		log.info("Response:  " + auth.getOutput()); 
		log.info("Got code:  " + auth.getExitCode()); 
		assertTrue(auth.getExitCode() == 0);
		log.info("Done.");
	}
	
}

package org.nachc.tools.githubbackup.util.gh;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetReposForUserIntegrationTest {

	@Test
	public void shouldGetRepoList() {
		log.info("Starting test...");
		List<String> repos = GetReposForUser.exec();
		log.info("Got " + repos.size() + " repositories.");
		for(String url : repos) {
			log.info("\t" + url);
		}
		log.info("Got " + repos.size() + " repositories.");
		assertTrue(repos.size() > 10);
		log.info("Done.");
	}
	
}

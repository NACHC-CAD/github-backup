package org.nachc.tools.githubbackup.main;

import java.text.SimpleDateFormat;
import java.util.List;

import org.nachc.tools.githubbackup.util.gh.GetReposForUser;
import org.yaorma.util.time.TimeUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GitHubBackupMain {

	public static void main(String[] args) {
		String today = TimeUtil.format(TimeUtil.getNow(), "yyyy_MM_dd");
		log.info("Today: " + today);
		List<String> repos = GetReposForUser.exec();
		for(String repo : repos) {
			log.info("\tCloning repo: " + repo);
//			Clone clone = new Clone();
//			clone.exec(repo, null);
		}
	}
	
}

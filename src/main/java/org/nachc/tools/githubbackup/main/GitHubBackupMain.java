package org.nachc.tools.githubbackup.main;

import java.io.File;
import java.util.List;

import org.nachc.tools.githubbackup.util.appprops.GithubBackupAppProps;
import org.nachc.tools.githubbackup.util.gh.Clone;
import org.nachc.tools.githubbackup.util.gh.GetReposForUser;
import org.yaorma.util.time.TimeUtil;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GitHubBackupMain {

	public static void main(String[] args) {
		File targetDir = getTargetDir();
		List<String> repos = GetReposForUser.exec();
		log.info("Got " + repos.size() + " repos");
		int cnt = 0;
		for(String repo : repos) {
			cnt++;
			log.info("\tCloning repo: " + repo);
			log.info("\t(" + cnt + " of " + repos.size() + ")");
			Clone clone = new Clone();
			clone.exec(repo, targetDir);
		}
		log.info("Done!");
	}
	
	private static File getTargetDir() {
		String targetDir = GithubBackupAppProps.getTargetDir();
		String today = TimeUtil.format(TimeUtil.getNow(), "yyyy_MM_dd");
		log.info("Today: " + today);
		File file = new File(targetDir, today);
		FileUtil.mkdirs(file);
		return file;
	}
}

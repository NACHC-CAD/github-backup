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
		File logFile = new File(targetDir, "backup.log");
		List<String> repos = GetReposForUser.exec();
		log.info("Got " + repos.size() + " repos");
		int cnt = 0;
		for(String repo : repos) {
			cnt++;
			log.info("\tCloning repo: " + repo);
			log.info("\t(" + cnt + " of " + repos.size() + ")");
			String msg = "";
			msg += "\n\n----------------------------------\n";
			msg += repo + "\n";
			msg += "\n(" + cnt + " of " + repos.size() + ")";
			FileUtil.write(msg, logFile);
			Clone clone = new Clone();
			clone.exec(repo, targetDir);
//			FileUtil.write(clone.getOut(), logFile);
			if(cnt > 2) {
				log.info("QUITTING AFTER 3 (DELETE THIS BLOCK)");
				break;
			}
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

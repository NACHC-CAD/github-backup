package org.nachc.tools.githubbackup.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.nachc.tools.githubbackup.util.appprops.GithubBackupAppProps;
import org.nachc.tools.githubbackup.util.backup.DeleteOlderThanOneMonth;
import org.nachc.tools.githubbackup.util.gh.Clone;
import org.nachc.tools.githubbackup.util.gh.GetReposForUser;
import org.yaorma.util.time.TimeUtil;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GitHubBackupMain {

	public static void main(String[] args) {
		try {
			File targetDir = getTargetDir();
			File monthlyTargetDir = getMonthlyTargetDir();
			File logFile = new File(targetDir, "backup.log");
			BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
			List<String> repos = GetReposForUser.exec();
			log.info("Got " + repos.size() + " repos");
			int cnt = 0;
			for(String repo : repos) {
				cnt++;
				log.info("\tCloning repo: " + repo);
				log.info("\t(" + cnt + " of " + repos.size() + ")");
				String msg = "\n\n";
				msg += "----------------------------------\n";
				msg += repo + "\n";
				msg += "(" + cnt + " of " + repos.size() + ")\n";
				writer.append(msg);
				// write daily
				Clone clone = new Clone();
				clone.exec(repo, targetDir);
				writer.append(clone.getOut());
				writer.append("\n");
				writer.append(clone.getErr());
				// write monthly
				if(monthlyTargetDir != null) {
					writer.append("\n--- DOING MONTHLY CLONE:");
					Clone monthlyClone = new Clone();
					monthlyClone.exec(repo, monthlyTargetDir);
					writer.append(monthlyClone.getOut());
					writer.append("\n");
					writer.append(monthlyClone.getErr());
					writer.append("\n-- DONE WITH MONTHLY CLONE");
				}
			}
			if(monthlyTargetDir != null) {
				// delete older daily backups
				DeleteOlderThanOneMonth.exec(writer);
			}
			writer.flush();
			writer.close();
			log.info("Done!");
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}
	
	private static File getTargetDir() {
		String targetDir = GithubBackupAppProps.getTargetDir();
		String today = TimeUtil.format(TimeUtil.getNow(), "yyyy_MM_dd");
		log.info("Today: " + today);
		File file = new File(targetDir, today);
		FileUtil.mkdirs(file);
		return file;
	}
	
	private static File getMonthlyTargetDir() {
		String targetDir = GithubBackupAppProps.getTargetDir();
		String today = TimeUtil.format(TimeUtil.getNow(), "yyyy_MM_dd");
		if(today.endsWith("22") == false) {
			return null;
		}
		log.info("Today: " + today);
		File monthlyRoot = new File(targetDir, "MONTHLY");
		File file = new File(monthlyRoot, today);
		FileUtil.mkdirs(file);
		return file;
	}
	
}

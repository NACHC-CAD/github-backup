package org.nachc.tools.githubbackup.util.backup;

import java.io.BufferedWriter;
import java.io.File;
import java.util.Calendar;

import org.nachc.tools.githubbackup.util.appprops.GithubBackupAppProps;
import org.yaorma.util.time.TimeUtil;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteOlderThanOneMonth {

	public static void exec(BufferedWriter writer) {
		try {
			writer.append("\nCLEANING UP OLD FILES...");
			String lastMonth = getLastMonth();
			String thisMonth = getThisMonth();
			String targetDir = GithubBackupAppProps.getTargetDir();
			File dir = new File(targetDir);
			File[] files = dir.listFiles();
			for(File file : files) {
				String fileName = file.getName();
				log.info("FILE: " + fileName);
				writer.append("\nFILE: " + fileName);
				if(file.isDirectory() && fileName.contains(thisMonth) == false && fileName.contains(lastMonth) == false) {
					log.info("!!!REMOVING!!!");
					writer.append("\n!!!REMOVING!!!");
					FileUtil.rmdir(file);
				} else {
					log.info("keeping");
					writer.append("\nkeeping");
				}
			}
			writer.append("\nDONE CLEANING UP OLD FILES.\n");
		} catch(Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	private static String getThisMonth() {
		Calendar cal = Calendar.getInstance();
		String rtn = TimeUtil.format(cal.getTime(), "yyyy_MM");
		return rtn;
	}

	private static String getLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		String rtn = TimeUtil.format(cal.getTime(), "yyyy_MM");
		return rtn;
	}

}

package org.nachc.tools.githubbackup.util.gh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.nachc.tools.githubbackup.util.appprops.GithubBackupAppProps;

import com.nach.core.util.http.HttpRequestClient;
import com.nach.core.util.json.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetReposForUser {

	public static List<String> exec() {
		try {
			String url = "https://api.github.com/user/repos";
			String uid = GithubBackupAppProps.getUserName();
			String pwd = GithubBackupAppProps.getToken();
			log.info("Getting repos for: " + uid);
			HttpRequestClient client = new HttpRequestClient(url);
			client.addBasicAuthentication(uid, pwd);
			client.doGet();
			String response = client.getResponse();
			response = JsonUtil.prettyPrint(response);
			List<String> rtn = parseJson(response);
			return rtn;
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}

	}
	
	private static List<String> parseJson(String json) {
		List<String> rtn = new ArrayList<String>();
		JSONArray jsonArray = new JSONArray(json);
		for(int i=0;i<jsonArray.length();i++) {
			Object obj = jsonArray.get(i);
			JSONObject jsonObj = (JSONObject) obj;
			String url = jsonObj.getString("svn_url");
			rtn.add(url);
		}
		Collections.sort(rtn);
		return rtn;
	}
	
}

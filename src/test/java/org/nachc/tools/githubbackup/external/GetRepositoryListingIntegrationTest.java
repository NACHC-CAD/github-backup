package org.nachc.tools.githubbackup.external;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.nachc.tools.githubbackup.util.appprops.GithubBackupAppProps;

import com.nach.core.util.http.HttpRequestClient;
import com.nach.core.util.json.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetRepositoryListingIntegrationTest {

	@Test
	public void shouldGetRepos() {
		try {
			String url = "https://api.github.com/user/repos";
			String uid = "NACHC-CAD";
			String pwd = GithubBackupAppProps.getToken();
			log.info("\nUsername: " + uid + "\nToken:    " + pwd);
			HttpRequestClient client = new HttpRequestClient(url);
			client.addBasicAuthentication(uid, pwd);
			client.doGet();
			String response = client.getResponse();
			response = JsonUtil.prettyPrint(response);
			log.info("Got response: \n" + response + "\n");
			log.info("Got response code: " + client.getStatusCode());
			assertTrue(client.getStatusCode() == 200);
			log.info("Done.");
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

}

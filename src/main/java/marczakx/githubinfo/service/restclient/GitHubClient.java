package marczakx.githubinfo.service.restclient;

import java.util.Arrays;
import java.util.List;

import javax.management.AttributeNotFoundException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import marczakx.githubinfo.model.dto.BrancheDto;
import marczakx.githubinfo.model.dto.CommitDto;
import marczakx.githubinfo.model.dto.ForkDto;
import marczakx.githubinfo.model.dto.RepoDto;

@Service
public class GitHubClient {
	
	private final String GITHUB_API_BASE_URL = "https://api.github.com/";
	private final String GITHUB_TOKEN = System.getenv("GITHUB_TOKEN");
	private final RestTemplate restTemplate = new RestTemplate();
	
	public List<ForkDto> getForks(String user, String repo) {
		String url = GITHUB_API_BASE_URL + "repos/{user}/{repo}/forks";
		return Arrays.asList(restTemplate.exchange(url, HttpMethod.GET, getRequest(), ForkDto[].class, user, repo).getBody());
	}
	
	public List<RepoDto> getRepos(String user) throws AttributeNotFoundException {
		String url = GITHUB_API_BASE_URL + "users/{user}/repos";
		try {
		return Arrays.asList(restTemplate.exchange(url, HttpMethod.GET, getRequest(),RepoDto[].class, user).getBody());
		} catch (HttpClientErrorException.NotFound ex) {
			throw new AttributeNotFoundException("User " + user + " does not exist");
		}
	}
	
	public CommitDto getCommit(String user, String repo, String sha) {
		String url = GITHUB_API_BASE_URL + "repos/{user}/{repo}/commits/{sha}";
		return restTemplate.exchange(url, HttpMethod.GET, getRequest(), CommitDto.class, user, repo, sha).getBody();
	}
	
	public List<BrancheDto> getBranches(String user, String repo) {
		String url = GITHUB_API_BASE_URL + "repos/{user}/{repo}/branches";
		return Arrays.asList(restTemplate.exchange(url, HttpMethod.GET, getRequest(), BrancheDto[].class, user, repo).getBody());
	}
	
	private HttpEntity<?> getRequest() {
		HttpHeaders headers = new HttpHeaders();
		if (GITHUB_TOKEN != null) {
			headers.add("Authorization", "Bearer " + GITHUB_TOKEN);
		}
		HttpEntity<?> request = new HttpEntity<>(headers);
		return request;
	}
	
}

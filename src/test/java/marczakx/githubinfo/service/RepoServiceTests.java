package marczakx.githubinfo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import javax.management.AttributeNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;
import marczakx.githubinfo.model.dto.BrancheDto;
import marczakx.githubinfo.model.dto.RepoDto;
import marczakx.githubinfo.model.dto.UserDto;
import marczakx.githubinfo.model.dto.branch.CommitDto;
import marczakx.githubinfo.service.restclient.GitHubClient;


@ExtendWith(MockitoExtension.class)
public class RepoServiceTests {

	@Mock
	GitHubClient gitHubClient;

	@InjectMocks
	RepoService repoService;

	@Test
	public void getReposViewTest() throws AttributeNotFoundException {
		UserDto owner = UserDto.builder().login("owner").build();
		List<RepoDto>  repos = new ArrayList<>(); 
		repos.add(RepoDto.builder().name("repo1").owner(owner).build());
		List<BrancheDto> branches = Arrays.asList(new BrancheDto[] {
				BrancheDto.builder().name("b1").commit(CommitDto.builder().sha("sha1").build()).build(),
				BrancheDto.builder().name("b2").commit(CommitDto.builder().sha("sha2").build()).build()
		});
		marczakx.githubinfo.model.dto.CommitDto commit1 = marczakx.githubinfo.model.dto.CommitDto.builder()
				.commit(marczakx.githubinfo.model.dto.commit.CommitDto.builder().message("message1").build())
				.build();
		marczakx.githubinfo.model.dto.CommitDto commit2 = marczakx.githubinfo.model.dto.CommitDto.builder()
		.commit(marczakx.githubinfo.model.dto.commit.CommitDto.builder().message("message2").build())
		.build();
		
		Mockito.when(gitHubClient.getRepos("user")).thenReturn(repos);
		Mockito.when(gitHubClient.getBranches("user", "repo1")).thenReturn(branches);
		Mockito.when(gitHubClient.getCommit("user", "repo1", "sha1")).thenReturn(commit1);
		Mockito.when(gitHubClient.getCommit("user", "repo1", "sha2")).thenReturn(commit2);
		
		var reposView = repoService.getReposView("user");

	    assertEquals("repo1", reposView.get(0).getName());
	    assertEquals("b1", reposView.get(0).getBranches().get(0).getName());
	    assertEquals("message1", reposView.get(0).getBranches().get(0).getLastCommit().message());
	    assertEquals("sha1", reposView.get(0).getBranches().get(0).getLastCommit().sha());
	    assertEquals("b2", reposView.get(0).getBranches().get(1).getName());
	    assertEquals("message2", reposView.get(0).getBranches().get(1).getLastCommit().message());
	    assertEquals("sha2", reposView.get(0).getBranches().get(1).getLastCommit().sha());
	}
	
}

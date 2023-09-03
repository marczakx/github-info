package marczakx.githubinfo.service;

import java.util.List;

import javax.management.AttributeNotFoundException;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import marczakx.githubinfo.model.dto.BrancheDto;
import marczakx.githubinfo.model.view.BranchView;
import marczakx.githubinfo.model.view.CommitView;
import marczakx.githubinfo.model.dto.RepoDto;
import marczakx.githubinfo.model.view.RepoView;
import marczakx.githubinfo.service.restclient.GitHubClient;

@Service
@RequiredArgsConstructor
public class RepoService {

	private final GitHubClient gitHubClientService;
	
	public List<RepoView> getReposView (String user) throws AttributeNotFoundException{
		return getPublicReposWithoutForks(user).stream()
				.map(e -> mapRepo(user, e))
				.toList();
	}
	
	private RepoView mapRepo(String user, RepoDto repoDto) {
		return RepoView.builder()
				.owner(repoDto.getOwner().getLogin())
				.name(repoDto.getName())
				.branches(getBrachesView(user, repoDto.getName()))
				.build();
	}

	private List<BranchView> getBrachesView(String user, String repo) {
		return gitHubClientService.getBranches(user, repo).stream()
				.map(branche -> mapBranche(user, repo, branche))
				.toList();
	}

	private BranchView mapBranche(String user, String repo, BrancheDto branche) {
		var commit = gitHubClientService.getCommit(user, repo, branche.getCommit().getSha());
		return BranchView.builder()
				.lastCommit(CommitView.builder()
						.message(commit.getCommit().getMessage())
						.sha(branche.getCommit().getSha())
						.build())
				.name(branche.getName())
				.build();
	}

	private List<RepoDto> getPublicReposWithoutForks(String user) throws AttributeNotFoundException {
		return gitHubClientService.getRepos(user).stream()
				.filter(repo -> Boolean.FALSE.equals(repo.isFork()))
				.filter(repo -> Boolean.FALSE.equals(repo.isPrivate()))
				.toList();
	}
	
}

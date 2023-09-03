package marczakx.githubinfo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import javax.management.AttributeNotFoundException;

import lombok.RequiredArgsConstructor;
import marczakx.githubinfo.model.view.RepoView;
import marczakx.githubinfo.service.RepoService;

@RestController
@RequiredArgsConstructor
public class GithubInfoController {
	private final RepoService repoService;
	  
	  @GetMapping("repos/{user}")
	  public List<RepoView> getReposView(@PathVariable String user) throws AttributeNotFoundException {
		  return repoService.getReposView(user);
	  } 
}

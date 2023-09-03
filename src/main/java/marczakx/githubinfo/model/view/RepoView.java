package marczakx.githubinfo.model.view;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepoView{
	  private String name;
	  private String owner;
	  private List<BranchView> branches;
}


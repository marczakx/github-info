package marczakx.githubinfo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import marczakx.githubinfo.model.dto.branch.CommitDto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrancheDto {
	private String name;
	private CommitDto commit;

}

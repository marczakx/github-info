package marczakx.githubinfo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommitDto {
	private String sha;
	private marczakx.githubinfo.model.dto.commit.CommitDto commit;
}

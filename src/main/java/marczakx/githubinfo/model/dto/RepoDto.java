package marczakx.githubinfo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepoDto {
	private String name;
	private UserDto owner;
	private boolean Private;
	private boolean fork;
}


package marczakx.githubinfo.model.view;

import lombok.Builder;

@Builder
public  record CommitView(String message, String sha) {
	
}

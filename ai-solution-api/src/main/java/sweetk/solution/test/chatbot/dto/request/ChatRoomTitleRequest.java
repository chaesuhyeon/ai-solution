package sweetk.solution.test.chatbot.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ChatRoomTitleRequest {
    private String newTitle;
}

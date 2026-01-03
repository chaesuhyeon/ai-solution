package sweetk.solution.test.chatbot.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class MessageResponse {
    private String messageId;
    private String senderType;
    private String content;
    private String model;
    private LocalDateTime createAt;
    private List<String> imageUrls;
}

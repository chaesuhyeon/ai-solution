package sweetk.solution.test.chatbot.dto.response;

import lombok.Builder;
import lombok.Getter;
import sweetk.solution.test.chatbot.domain.model.ChatMessage;

import java.time.LocalDateTime;

@Getter
@Builder
public class ChatResponse {
    private String answer;
    private String model;
    private LocalDateTime createdAt;

    public static ChatResponse from(ChatMessage chatMessage) {
        return ChatResponse.builder()
                .answer(chatMessage.getContent())
                .model(chatMessage.getModelUsed())
                .createdAt(chatMessage.getCreatedAt())
                .build();
    }
}

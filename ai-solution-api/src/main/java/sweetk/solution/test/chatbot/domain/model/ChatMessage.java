package sweetk.solution.test.chatbot.domain.model;

import lombok.*;
import sweetk.solution.test.chatbot.enums.SenderType;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class ChatMessage {
    private String id;
    private Long chatRoomId;
    private SenderType senderType;
    private String content;
    private String modelUsed; // 사용된 모델
    private LocalDateTime createdAt;

    public static ChatMessage createUserMessage(Long chatRoomId, String content) {
        return ChatMessage.builder()
                .chatRoomId(chatRoomId)
                .senderType(SenderType.USER)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static ChatMessage createBotMessage(Long chatRoomId, String content, String modelUsed) {
        return ChatMessage.builder()
                .chatRoomId(chatRoomId)
                .senderType(SenderType.BOT)
                .content(content)
                .modelUsed(modelUsed)
                .createdAt(LocalDateTime.now())
                .build();
    }
}

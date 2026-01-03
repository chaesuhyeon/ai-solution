package sweetk.solution.test.chatbot.infrastructure.mapper;

import org.springframework.stereotype.Component;
import sweetk.solution.test.chatbot.domain.model.ChatMessage;
import sweetk.solution.test.chatbot.infrastructure.persistence.jpa.entity.ChatMessageEntity;

@Component
public class ChatMessageMapper {

    public ChatMessageEntity toDocument(ChatMessage chatMessage) {
        if (chatMessage == null) {
            return null;
        }
        return ChatMessageEntity.builder()
                .id(chatMessage.getId())
                .chatRoomId(chatMessage.getChatRoomId())
                .senderType(chatMessage.getSenderType())
                .content(chatMessage.getContent())
                .modelUsed(chatMessage.getModelUsed())
                .createdAt(chatMessage.getCreatedAt())
                .build();
    }

    public ChatMessage toDomain(ChatMessageEntity document) {
        if (document == null) {
            return null;
        }
        return ChatMessage.builder()
                .id(document.getId())
                .chatRoomId(document.getChatRoomId())
                .senderType(document.getSenderType())
                .content(document.getContent())
                .modelUsed(document.getModelUsed())
                .createdAt(document.getCreatedAt())
                .build();
    }
}

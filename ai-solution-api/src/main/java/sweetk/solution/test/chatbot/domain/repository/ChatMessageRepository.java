package sweetk.solution.test.chatbot.domain.repository;

import sweetk.solution.test.chatbot.domain.model.ChatMessage;
import sweetk.solution.test.chatbot.enums.SenderType;

public interface ChatMessageRepository {
    ChatMessage save(ChatMessage chatMessage);
    ChatMessage findFirstByChatRoomIdAndSenderTypeOrderByCreatedAtDesc(Long chatRoomId, SenderType senderType);
}

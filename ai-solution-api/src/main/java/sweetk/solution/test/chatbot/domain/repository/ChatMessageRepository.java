package sweetk.solution.test.chatbot.domain.repository;

import sweetk.solution.test.chatbot.domain.model.ChatMessage;

public interface ChatMessageRepository {
    ChatMessage save(ChatMessage chatMessage);
}

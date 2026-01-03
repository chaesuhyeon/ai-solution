package sweetk.solution.test.chatbot.domain.repository;

import sweetk.solution.test.chatbot.domain.model.ChatRoom;

import java.util.Optional;

public interface ChatRoomRepository {
    ChatRoom save(ChatRoom chatRoom);

    ChatRoom findById(Long chatRoomId);
}

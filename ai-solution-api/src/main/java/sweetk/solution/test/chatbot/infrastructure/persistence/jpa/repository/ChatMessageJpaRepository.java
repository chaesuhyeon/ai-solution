package sweetk.solution.test.chatbot.infrastructure.persistence.jpa.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sweetk.solution.test.chatbot.infrastructure.persistence.jpa.entity.ChatMessageEntity;

import java.util.List;

public interface ChatMessageJpaRepository extends MongoRepository<ChatMessageEntity, String> {
    List<ChatMessageEntity> findByChatRoomIdOrderByCreatedAtDesc(Long chatRoomId);
}

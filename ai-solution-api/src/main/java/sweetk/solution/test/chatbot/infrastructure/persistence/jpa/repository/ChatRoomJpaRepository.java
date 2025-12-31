package sweetk.solution.test.chatbot.infrastructure.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sweetk.solution.test.chatbot.infrastructure.persistence.jpa.entity.ChatRoomEntity;

public interface ChatRoomJpaRepository extends JpaRepository<ChatRoomEntity, Long> {
}

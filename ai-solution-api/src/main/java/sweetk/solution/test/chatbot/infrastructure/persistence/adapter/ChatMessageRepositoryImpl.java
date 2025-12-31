package sweetk.solution.test.chatbot.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sweetk.solution.test.chatbot.domain.model.ChatMessage;
import sweetk.solution.test.chatbot.domain.repository.ChatMessageRepository;
import sweetk.solution.test.chatbot.infrastructure.mapper.ChatMessageMapper;
import sweetk.solution.test.chatbot.infrastructure.persistence.jpa.entity.ChatMessageEntity;
import sweetk.solution.test.chatbot.infrastructure.persistence.jpa.repository.ChatMessageJpaRepository;

@RequiredArgsConstructor
@Repository
public class ChatMessageRepositoryImpl implements ChatMessageRepository {

    private final ChatMessageJpaRepository chatMessageJpaRepository;
    private final ChatMessageMapper chatMessageMapper;

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        ChatMessageEntity message = chatMessageMapper.toDocument(chatMessage);
        ChatMessageEntity savedMessage = chatMessageJpaRepository.save(message);
        return chatMessageMapper.toDomain(savedMessage);
    }
}

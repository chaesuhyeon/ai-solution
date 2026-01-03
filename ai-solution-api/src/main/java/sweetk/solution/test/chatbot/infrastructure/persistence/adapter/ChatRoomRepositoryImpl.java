package sweetk.solution.test.chatbot.infrastructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sweetk.solution.test.chatbot.domain.model.ChatRoom;
import sweetk.solution.test.chatbot.domain.repository.ChatRoomRepository;
import sweetk.solution.test.chatbot.infrastructure.mapper.ChatRoomMapper;
import sweetk.solution.test.chatbot.infrastructure.persistence.jpa.entity.ChatRoomEntity;
import sweetk.solution.test.chatbot.infrastructure.persistence.jpa.repository.ChatRoomJpaRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ChatRoomRepositoryImpl implements ChatRoomRepository {

    private final ChatRoomJpaRepository chatRoomJpaRepository;
    private final ChatRoomMapper chatRoomMapper;

    @Override
    public ChatRoom save(ChatRoom chatRoom) {
        ChatRoomEntity chatRoomEntity = chatRoomMapper.toEntity(chatRoom);
        ChatRoomEntity savedChatRoomEntity = chatRoomJpaRepository.save(chatRoomEntity);
        return chatRoomMapper.toDomain(savedChatRoomEntity);
    }

    @Override
    public ChatRoom findById(Long chatRoomId) {
        ChatRoomEntity chatRoomEntity = chatRoomJpaRepository.findById(chatRoomId).orElseThrow();
        return chatRoomMapper.toDomain(chatRoomEntity);
    }
}

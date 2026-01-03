package sweetk.solution.test.chatbot.infrastructure.mapper;

import org.springframework.stereotype.Component;
import sweetk.solution.test.chatbot.domain.model.ChatRoom;
import sweetk.solution.test.chatbot.infrastructure.persistence.jpa.entity.ChatRoomEntity;

@Component
public class ChatRoomMapper {
    public ChatRoomEntity toEntity(ChatRoom chatRoom) {
        if(chatRoom == null){
            return null;
        }
        return ChatRoomEntity.builder()
                .id(chatRoom.getId())
                .title(chatRoom.getTitle())
                .userId(chatRoom.getUserId())
                .status(chatRoom.getStatus())
                .titleGenerated(chatRoom.isTitleGenerated())
                .build();
    }

    public ChatRoom toDomain(ChatRoomEntity chatRoomEntity) {
        if(chatRoomEntity == null){
            return null;
        }
        return ChatRoom.builder()
                .id(chatRoomEntity.getId())
                .title(chatRoomEntity.getTitle())
                .userId(chatRoomEntity.getUserId())
                .status(chatRoomEntity.getStatus())
                .titleGenerated(chatRoomEntity.isTitleGenerated())
                .build();
    }
}

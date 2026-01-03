package sweetk.solution.test.chatbot.dto.response;

import lombok.Builder;
import lombok.Getter;
import sweetk.solution.test.chatbot.domain.model.ChatRoom;
import sweetk.solution.test.chatbot.enums.ChatRoomStatus;

@Builder
@Getter
public class ChatRoomResponse {
    private Long chatRoomId;
    private String title;
    private ChatRoomStatus status;

    public static  ChatRoomResponse from(ChatRoom chatRoom) {
        return ChatRoomResponse.builder()
                .chatRoomId(chatRoom.getId())
                .title(chatRoom.getTitle())
                .status(chatRoom.getStatus())
                .build();
    }
}

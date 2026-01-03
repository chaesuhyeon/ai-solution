package sweetk.solution.test.chatbot.domain.model;

import lombok.*;
import sweetk.solution.test.chatbot.enums.ChatRoomStatus;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatRoom {
    private Long id;
    private Long userId;
    private String title;
    private ChatRoomStatus status;
    private boolean titleGenerated;

    public static ChatRoom createChatRoom(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User는 null일 수 없습니다.");
        }
        return ChatRoom.builder()
                .userId(userId)
                .title("새로운 대화")
                .status(ChatRoomStatus.TEMP)
                .build();
    }

    public void updateTitle(String newTitle) {
        if (newTitle == null || newTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("채팅방 제목은 비워둘 수 없습니다.");
        }
        this.title = newTitle;
    }

    public void updateChatRoomStatus(ChatRoomStatus newStatus) {
        this.status = newStatus;
    }

    public void markTitleGenerated() {
        this.titleGenerated = true;
    }

}

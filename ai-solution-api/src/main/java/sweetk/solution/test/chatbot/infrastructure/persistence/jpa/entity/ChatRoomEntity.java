package sweetk.solution.test.chatbot.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import sweetk.solution.test.chatbot.enums.ChatRoomStatus;
import sweetk.solution.test.global.model.BaseEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "chat_rooms")
@Builder
@Entity
@Getter
public class ChatRoomEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "title", nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChatRoomStatus status;

    @Column(name = "title_generated")
    private boolean titleGenerated;
}

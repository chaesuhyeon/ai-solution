package sweetk.solution.test.chatbot.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import sweetk.solution.test.chatbot.enums.SenderType;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Document(collection = "chat_messages")
@Builder
@Getter
public class ChatMessageEntity {

    @Id
    private String id;

    private Long chatRoomId;

    private SenderType senderType;

    private String content; // 텍스트 메세지

    private String modelUsed; // 봇 메시지일 경우 사용된 모델, user 메시지일 경우 null

    private List<String> imageUrls;

    @CreatedDate
    @Field("created_at")
    private LocalDateTime createdAt;
}

package sweetk.solution.test.chatbot.domain.event;

public record ChatRoomTitleCreatedEvent(
        Long chatRoomId,
        String content
) {}

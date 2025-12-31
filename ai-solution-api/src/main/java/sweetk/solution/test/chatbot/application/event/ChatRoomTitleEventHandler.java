package sweetk.solution.test.chatbot.application.event;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import sweetk.solution.test.chatbot.application.summarizer.TitleSummarizer;
import sweetk.solution.test.chatbot.domain.event.ChatRoomTitleCreatedEvent;
import sweetk.solution.test.chatbot.domain.model.ChatRoom;
import sweetk.solution.test.chatbot.domain.repository.ChatRoomRepository;

@RequiredArgsConstructor
@Component
public class ChatRoomTitleEventHandler {

    private final ChatRoomRepository chatRoomRepository;
    private final TitleSummarizer summarizer;

    @Async
    @Transactional
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(ChatRoomTitleCreatedEvent event) {

        ChatRoom chatRoom = chatRoomRepository
                .findById(event.chatRoomId());

        if (chatRoom.isTitleGenerated()) return;

        String title = summarizer.summarize(event.content());
        chatRoom.updateTitle(title);

        chatRoom.markTitleGenerated();
    }
}

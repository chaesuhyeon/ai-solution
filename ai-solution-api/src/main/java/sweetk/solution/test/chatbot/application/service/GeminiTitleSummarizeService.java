package sweetk.solution.test.chatbot.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sweetk.solution.test.chatbot.application.summarizer.TitleSummarizer;
import sweetk.solution.test.chatbot.domain.model.ChatMessage;
import sweetk.solution.test.chatbot.domain.model.ChatRoom;
import sweetk.solution.test.chatbot.domain.repository.ChatMessageRepository;
import sweetk.solution.test.chatbot.domain.repository.ChatRoomRepository;
import sweetk.solution.test.chatbot.enums.ChatRoomStatus;
import sweetk.solution.test.chatbot.enums.SenderType;

@Service
@RequiredArgsConstructor
public class GeminiTitleSummarizeService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final TitleSummarizer titleSummarizer;


    @Transactional
    public void generate(Long chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId);

        if (chatRoom.isTitleGenerated()) return;

        ChatMessage message = chatMessageRepository.findFirstByChatRoomIdAndSenderTypeOrderByCreatedAtDesc(chatRoomId, SenderType.BOT);
        String title = titleSummarizer.summarize(message.getContent());

        chatRoom.updateTitle(title);
        chatRoom.updateChatRoomStatus(ChatRoomStatus.ACTIVE);
        chatRoom.markTitleGenerated();

        chatRoomRepository.save(chatRoom);
    }
}

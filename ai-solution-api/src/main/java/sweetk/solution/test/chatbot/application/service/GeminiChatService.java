package sweetk.solution.test.chatbot.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sweetk.solution.test.chatbot.domain.model.ChatMessage;
import sweetk.solution.test.chatbot.domain.model.ChatRoom;
import sweetk.solution.test.chatbot.domain.repository.ChatMessageRepository;
import sweetk.solution.test.chatbot.dto.request.ChatRequest;
import sweetk.solution.test.chatbot.dto.response.ChatResponse;
import sweetk.solution.test.chatbot.dto.response.ChatRoomResponse;
import sweetk.solution.test.chatbot.dto.response.MessageResponse;
import sweetk.solution.test.chatbot.domain.repository.ChatRoomRepository;
import sweetk.solution.test.chatbot.application.usecase.ChatUseCase;
import sweetk.solution.test.chatbot.infrastructure.client.gemini.GeminiGenerator;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GeminiChatService implements ChatUseCase {

    private final GeminiTitleDelayExecutor titleDelayExecutor;

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final GeminiGenerator geminiGenerator;

    @Value("${spring.ai.gemini.chat.options.model}")
    private String modelUsed;

    @Transactional
    @Override
    public ChatResponse getChatResponseAndSave(ChatRequest chatRequest, Long userId) {

        Long chatRoomId = chatRequest.getChatRoomId();

        if (chatRequest.getChatRoomId() == null) {
            ChatRoomResponse chatRoom = createChatRoom(userId);
            chatRoomId = chatRoom.getChatRoomId();
        }

        String userPrompt = chatRequest.getPromptParts().getFirst().getValue();

        // 사용자 질문 저장
        ChatMessage userChatMessage = ChatMessage.createUserMessage(chatRoomId, userPrompt);
        chatMessageRepository.save(userChatMessage);

        // gemini api 호출
        String response = geminiGenerator.textGenTextOnlyPrompt(userPrompt);

        // 챗봇 메세지 저장
        ChatMessage botChatMessage = ChatMessage.createBotMessage(chatRoomId, response, modelUsed);
        ChatMessage savedMessage = chatMessageRepository.save(botChatMessage);

        // 채팅방 제목 (챗봇 응답 요약) 생성 요청
        titleDelayExecutor.delay(chatRoomId);

        return ChatResponse.from(savedMessage);
    }

    @Override
    public List<ChatRoomResponse> getUserChatRooms(Long userId) {
        return List.of();
    }

    @Override
    public List<MessageResponse> getChatRoomMessages(Long userId, Long chatRoomId) {
        return List.of();
    }

    private ChatRoomResponse createChatRoom(Long userId) {
        ChatRoom chatRoom = ChatRoom.createChatRoom(userId);
        ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);
        return ChatRoomResponse.from(savedChatRoom);
    }

}

package sweetk.solution.test.chatbot.application.usecase;

import sweetk.solution.test.chatbot.dto.request.ChatRequest;
import sweetk.solution.test.chatbot.dto.response.ChatResponse;
import sweetk.solution.test.chatbot.dto.response.ChatRoomResponse;
import sweetk.solution.test.chatbot.dto.response.MessageResponse;

import java.util.List;

public interface ChatUseCase {

    ChatResponse getChatResponseAndSave(ChatRequest chatRequest, Long userId);

    List<ChatRoomResponse> getUserChatRooms(Long userId);

    List<MessageResponse> getChatRoomMessages(Long userId, Long chatRoomId);
}

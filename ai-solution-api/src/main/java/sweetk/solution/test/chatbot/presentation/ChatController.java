package sweetk.solution.test.chatbot.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sweetk.solution.test.chatbot.dto.request.ChatRequest;
import sweetk.solution.test.chatbot.dto.response.ChatResponse;
import sweetk.solution.test.chatbot.dto.response.ChatRoomResponse;
import sweetk.solution.test.chatbot.dto.response.MessageResponse;
import sweetk.solution.test.chatbot.application.usecase.ChatUseCase;
import sweetk.solution.test.global.model.ResponseDto;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/chat")
@RestController
public class ChatController {

    private final ChatUseCase chatService;

    /**
     * 메세지 전송 api
     */
    @PostMapping("/rooms/messages")
    public ResponseEntity<ResponseDto<ChatResponse>> sendMessageToChatRoom(
            @RequestBody ChatRequest chatRequest) {
        Long userId = 1L;
        ChatResponse response = chatService.getChatResponseAndSave(chatRequest, userId);
        return ResponseDto.createSuccess(response);
    }

    /**
     * 채팅방 목록 조회 api
     */
    @GetMapping("/rooms")
    public ResponseEntity<ResponseDto<List<ChatRoomResponse>>> getUserChatRooms() {
        Long userId = 1L;
        List<ChatRoomResponse> chatRooms = chatService.getUserChatRooms(userId);
        return ResponseDto.createSuccess(chatRooms);
    }

    /**
     * 대화 내용 조회 api
     */
    @GetMapping("/rooms/{chatRoomId}/messages")
    public ResponseEntity<ResponseDto<List<MessageResponse>>> getChatRoomMessages(
            @PathVariable Long chatRoomId) {
        Long userId = 1L;
        List<MessageResponse> messages = chatService.getChatRoomMessages(userId, chatRoomId);
        return ResponseDto.createSuccess(messages);
    }
}

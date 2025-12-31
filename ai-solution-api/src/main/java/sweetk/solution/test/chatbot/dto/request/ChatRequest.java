package sweetk.solution.test.chatbot.dto.request;

import lombok.Builder;
import lombok.Getter;
import sweetk.solution.test.chatbot.dto.PromptPart;

import java.util.List;

@Getter
@Builder
public class ChatRequest {
    private Long chatRoomId;
    private String model;
    private List<PromptPart> promptParts;
}

// JSON 요청 본문 예시 (프론트엔드 -> 백엔드)
/*
    {
        "model": "gemini",
        "promptParts": [
            {
                "type": "TEXT",
                "value": "이 고양이에 대해 설명해줘."
            },
            {
                "type": "IMAGE_BASE64",
                "value": "data:image/jpeg;base64,BASE64_ENCODED_CAT_IMAGE_DATA...",
                "mimeType": "image/jpeg"
            }
        ]
    }
*/

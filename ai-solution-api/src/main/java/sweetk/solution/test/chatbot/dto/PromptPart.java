package sweetk.solution.test.chatbot.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PromptPart {
    private PartType type; // TEXT, IMAGE_BASE64
    private String value;  // 텍스트 내용, Base64 문자열 등 해당 타입의 값
    private String mimeType; // 이미지인 경우 image/jpeg, image/png 등

    public enum PartType {
        TEXT, IMAGE_BASE64
    }
}

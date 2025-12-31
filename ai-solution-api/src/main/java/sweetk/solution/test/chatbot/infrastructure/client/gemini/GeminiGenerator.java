package sweetk.solution.test.chatbot.infrastructure.client.gemini;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class GeminiGenerator {

    private final GenerateContentConfig generationConfig;
    private final Client geminiClient;

    public @Nullable String textGenTextOnlyPrompt(String text) {

        GenerateContentResponse response =
                geminiClient.models.generateContent(
                        "gemini-2.5-flash",
                        text,
                        generationConfig);

        log.info(" ============ gemini response ============ \n {} \n ==================================== ", response.text());
        return response.text();
    }

    public String summarizeTitle(String content) {

        String prompt = """
        다음 대화를 한 줄 제목으로 요약해줘.
        조건:
        - 15자 이내
        - 따옴표 없이
        - 명사형

        대화:
        %s
        """.formatted(content);

        GenerateContentResponse response =
                geminiClient.models.generateContent(
                        "gemini-2.5-flash",
                        prompt,
                        generationConfig);

        return Optional.ofNullable(response.text())
                .map(String::trim)
                .map(this::sanitizeTitle)
                .orElse("새 대화");
    }

    private String sanitizeTitle(String title) {
        // 줄바꿈 제거
        title = title.replaceAll("[\\r\\n]", " ");

        // 따옴표 제거
        title = title.replace("\"", "");

        // 길이 제한
        if (title.length() > 15) {
            title = title.substring(0, 15);
        }

        return title.isBlank() ? "새 대화" : title;
    }
}

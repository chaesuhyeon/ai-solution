package sweetk.solution.test.chatbot.infrastructure.client.gemini;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sweetk.solution.test.chatbot.application.summarizer.TitleSummarizer;

@RequiredArgsConstructor
@Component
public class GeminiTitleSummarizer implements TitleSummarizer {

    private final GeminiGenerator geminiGenerator;

    @Override
    public String summarize(String content) {
        return geminiGenerator.summarizeTitle(content);
    }
}

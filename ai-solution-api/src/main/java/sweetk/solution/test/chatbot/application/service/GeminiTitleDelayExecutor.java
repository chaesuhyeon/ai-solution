package sweetk.solution.test.chatbot.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class GeminiTitleDelayExecutor {

    private final TaskScheduler taskScheduler;
    private final GeminiTitleSummarizeService titleSummarizeService;

    public void delay(Long chatRoomId) {
        taskScheduler.schedule(
                () -> titleSummarizeService.generate(chatRoomId),
                Instant.now().plusSeconds(8)
        );
    }
}

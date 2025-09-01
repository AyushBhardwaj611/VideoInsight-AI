
package com.videoinsight.backend.service;


import com.videoinsight.backend.model.VideoSummaryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatMessage;

import java.util.Arrays;

@Service
public class VideoService {


    private final TranscriptService transcriptService;
    private final OpenAiService openAiService;

    public VideoService(TranscriptService transcriptService, @Value("${OPENAI_API_KEY}") String openAiApiKey) {
        this.transcriptService = transcriptService;
        this.openAiService = new OpenAiService(openAiApiKey);
    }


    public VideoSummaryResponse summarizeVideo(String url, String title, String transcript) {
        try {
            String finalTranscript = transcript;
            if (finalTranscript == null || finalTranscript.isEmpty()) {
                String videoId = extractVideoId(url);
                finalTranscript = transcriptService.getTranscript(videoId);
            }
            String summary = generateSummary(finalTranscript);
            return new VideoSummaryResponse(title, url, finalTranscript, summary);
        } catch (Exception e) {
            return new VideoSummaryResponse(title, url, null, "Error: " + e.getMessage());
        }
    }

    private String extractVideoId(String url) {
        // Simple extraction for YouTube URLs (MVP, not robust)
        String[] parts = url.split("v=");
        if (parts.length > 1) {
            String id = parts[1];
            int amp = id.indexOf('&');
            return amp > -1 ? id.substring(0, amp) : id;
        }
        throw new IllegalArgumentException("Invalid YouTube URL");
    }


    // Calls OpenAI API to generate a summary from transcript
    public String generateSummary(String transcript) {
        String prompt = "Summarize the following transcript in 3 sentences:";
        ChatMessage userMessage = new ChatMessage("user", prompt + "\n" + transcript);
        ChatCompletionRequest request = ChatCompletionRequest.builder()
            .model("gpt-3.5-turbo")
            .messages(Arrays.asList(userMessage))
            .build();
        return openAiService.createChatCompletion(request)
            .getChoices().get(0).getMessage().getContent();
    }
}

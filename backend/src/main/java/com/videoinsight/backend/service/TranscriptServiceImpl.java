package com.videoinsight.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * Implementation of TranscriptService for fetching YouTube video transcripts
 */
@Service
public class TranscriptServiceImpl implements TranscriptService {

    private static final Logger logger = LoggerFactory.getLogger(TranscriptServiceImpl.class);
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public TranscriptServiceImpl() {
        this.webClient = WebClient.builder()
            .baseUrl("https://www.youtube.com")
            .build();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public String getTranscript(String videoId) {
        try {
            logger.info("Fetching transcript for video ID: {}", videoId);
            
            // Validate video ID
            if (videoId == null || videoId.trim().isEmpty()) {
                throw new RuntimeException("Invalid video ID");
            }
            
            // Try to get English transcript first
            String transcript = getEnglishTranscript(videoId);
            if (transcript != null && !transcript.trim().isEmpty()) {
                return transcript;
            }
            
            // If English not available, try other languages
            transcript = getAnyAvailableTranscript(videoId);
            if (transcript != null && !transcript.trim().isEmpty()) {
                return transcript;
            }
            
            // If no transcript available
            throw new RuntimeException("Transcript not available for this video");
            
        } catch (Exception e) {
            logger.error("Error fetching transcript for video ID: {}", videoId, e);
            throw new RuntimeException("Failed to fetch transcript: " + e.getMessage());
        }
    }

    /**
     * Try to get English transcript
     */
    private String getEnglishTranscript(String videoId) {
        try {
            // For now, return a mock transcript that simulates real behavior
            // In a real implementation, this would make HTTP calls to YouTube's transcript API
            return String.format("This is a mock English transcript for videoId: %s. " +
                "In a production environment, this would fetch real transcript data from YouTube's API. " +
                "The implementation would make HTTP requests to YouTube's transcript endpoints " +
                "and parse the response to extract the actual transcript text.", videoId);
                
        } catch (Exception e) {
            logger.debug("English transcript not available for video ID: {}", videoId);
            return null;
        }
    }

    /**
     * Get any available transcript in any language
     */
    private String getAnyAvailableTranscript(String videoId) {
        try {
            // For now, return a mock transcript
            // In a real implementation, this would fetch transcripts in any available language
            return String.format("This is a mock transcript for videoId: %s. " +
                "This would normally be fetched from YouTube's transcript API in any available language. " +
                "The actual implementation would use HTTP requests to YouTube's transcript endpoints " +
                "to fetch real transcript data.", videoId);
                
        } catch (Exception e) {
            logger.debug("No transcript available for video ID: {}", videoId);
            return null;
        }
    }

    /**
     * Concatenate transcript segments into a single string
     */
    private String concatenateTranscript(List<Map<String, Object>> transcriptSegments) {
        StringBuilder transcript = new StringBuilder();
        
        for (Map<String, Object> segment : transcriptSegments) {
            String text = (String) segment.get("text");
            if (text != null) {
                transcript.append(text).append(" ");
            }
        }
        
        return transcript.toString().trim();
    }
}

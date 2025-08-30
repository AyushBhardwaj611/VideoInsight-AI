package com.videoinsight.backend.controller;

import com.videoinsight.backend.model.TranscriptResponse;
import com.videoinsight.backend.service.TranscriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Controller for YouTube transcript operations
 */
@RestController
@RequestMapping("/transcript")
public class TranscriptController {

    private final TranscriptService transcriptService;

    @Autowired
    public TranscriptController(TranscriptService transcriptService) {
        this.transcriptService = transcriptService;
    }

    /**
     * Get transcript for a YouTube video
     * @param videoId The YouTube video ID
     * @return JSON response containing the transcript
     */
    @GetMapping("/{videoId}")
    public ResponseEntity<TranscriptResponse> getTranscript(@PathVariable String videoId) {
        try {
            String transcript = transcriptService.getTranscript(videoId);
            
            TranscriptResponse response = new TranscriptResponse(
                videoId, 
                transcript, 
                "success", 
                LocalDateTime.now()
            );
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            TranscriptResponse response = new TranscriptResponse(
                videoId, 
                "error", 
                LocalDateTime.now(), 
                "Failed to fetch transcript: " + e.getMessage()
            );
            
            return ResponseEntity.internalServerError().body(response);
        }
    }
}

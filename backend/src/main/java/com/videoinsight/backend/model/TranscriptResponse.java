package com.videoinsight.backend.model;

import java.time.LocalDateTime;

/**
 * DTO for transcript response
 */
public class TranscriptResponse {
    private String videoId;
    private String transcript;
    private String status;
    private LocalDateTime timestamp;
    private String error;

    // Default constructor
    public TranscriptResponse() {
    }

    // Constructor for success response
    public TranscriptResponse(String videoId, String transcript, String status, LocalDateTime timestamp) {
        this.videoId = videoId;
        this.transcript = transcript;
        this.status = status;
        this.timestamp = timestamp;
    }

    // Constructor for error response
    public TranscriptResponse(String videoId, String status, LocalDateTime timestamp, String error) {
        this.videoId = videoId;
        this.status = status;
        this.timestamp = timestamp;
        this.error = error;
    }

    // Getters and Setters
    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

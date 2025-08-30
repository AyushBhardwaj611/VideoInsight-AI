package com.videoinsight.backend.service;

/**
 * Service interface for fetching YouTube video transcripts
 */
public interface TranscriptService {

    /**
     * Get transcript for a YouTube video
     * @param videoId The YouTube video ID
     * @return The transcript text
     */
    String getTranscript(String videoId);
}

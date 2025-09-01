package com.videoinsight.backend.controller;

import com.videoinsight.backend.model.VideoRequest;
import com.videoinsight.backend.model.VideoSummaryResponse;
import com.videoinsight.backend.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/summarizeVideo")
    public ResponseEntity<VideoSummaryResponse> summarizeVideo(@RequestBody VideoRequest request) {
        try {
            VideoSummaryResponse response = videoService.summarizeVideo(request.getUrl(), request.getTitle(), request.getTranscript());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            VideoSummaryResponse errorResponse = new VideoSummaryResponse(request.getTitle(), request.getUrl(), null, "Error: " + e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}

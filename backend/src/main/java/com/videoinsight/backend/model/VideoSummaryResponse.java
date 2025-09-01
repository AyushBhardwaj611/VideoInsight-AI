package com.videoinsight.backend.model;

public class VideoSummaryResponse {
    private String title;
    private String url;
    private String transcript;
    private String summary;

    public VideoSummaryResponse() {}

    public VideoSummaryResponse(String title, String url, String transcript, String summary) {
        this.title = title;
        this.url = url;
        this.transcript = transcript;
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}

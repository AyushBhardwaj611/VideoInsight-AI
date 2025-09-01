package com.videoinsight.backend.model;

public class VideoRequest {
    private String url;
    private String title;
    private String transcript;

    public VideoRequest() {}

    public VideoRequest(String url, String title, String transcript) {
        this.url = url;
        this.title = title;
        this.transcript = transcript;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }
}

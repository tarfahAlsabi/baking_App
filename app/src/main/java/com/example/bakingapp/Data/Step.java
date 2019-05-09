package com.example.bakingapp.Data;

public class Step {
    int id;
    String shortDescription;
    String description;
    String videoURL;
    String thumbnailURL;

    public Step(int id, String shortDescription, String description, String videoURL, String thumbnailURL){
        this.id=id;
        this.shortDescription=shortDescription;
        this.description=description;
        this.videoURL=videoURL;
        this.thumbnailURL=thumbnailURL;
    }

    public int getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }
}

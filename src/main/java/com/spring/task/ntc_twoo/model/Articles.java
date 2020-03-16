package com.spring.task.ntc_twoo.model;

import java.util.Objects;

public class Articles {

    private String author;

    private String source;

    private String title;

    private String urlSource;

    private String description;

    private String imageUrl;

    private String publishedAt;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlSource() {
        return urlSource;
    }

    public void setUrlSource(String urlSource) {
        this.urlSource = urlSource;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Articles)) return false;
        Articles articles = (Articles) o;
        return Objects.equals(getAuthor(), articles.getAuthor()) &&
                Objects.equals(getSource(), articles.getSource()) &&
                Objects.equals(getTitle(), articles.getTitle()) &&
                Objects.equals(getUrlSource(), articles.getUrlSource()) &&
                Objects.equals(getDescription(), articles.getDescription()) &&
                Objects.equals(getImageUrl(), articles.getImageUrl()) &&
                Objects.equals(getPublishedAt(), articles.getPublishedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthor(), getSource(), getTitle(), getUrlSource(), getDescription(), getImageUrl(), getPublishedAt());
    }

    @Override
    public String toString() {
        return "Articles{" +
                "author='" + author + '\'' +
                ", source='" + source + '\'' +
                ", title='" + title + '\'' +
                ", urlSource='" + urlSource + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                '}';
    }
}

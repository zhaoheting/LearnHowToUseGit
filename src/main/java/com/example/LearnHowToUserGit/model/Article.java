package com.example.LearnHowToUserGit.model;

public class Article {
    private String articleId;
    private String title;
    private String link;
    private String userId;
    private Double time;
    private Integer votes;

    public Article(String articleId, String title, String link, String userId, Double time, Integer votes) {
        this.articleId = articleId;
        this.title = title;
        this.link = link;
        this.userId = userId;
        this.time = time;
        this.votes = votes;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}

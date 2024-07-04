
package com.code_cafe.Database;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Post {

    @JsonProperty("post_id")
    private String postId;

    @JsonProperty("sender_user_id")
    private String senderUserId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("content_text")
    private String contentText;

    @JsonProperty("content_images")
    private String contentImages;

    @JsonProperty("likes")
    private String likes;

    @JsonProperty("comments")
    private String comments;

    @JsonProperty("visibility")
    private String visibility;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("hashtag")
    private String hashtag;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("job_title")
    private String jobTitle;

    @JsonProperty("user_type")
    private String userType;

    @JsonProperty("profile_img")
    private String profile_img;

    // Getters and Setters

    public String getPostId() {
        return postId;
    }

    public String getProfileImage(){
        return profile_img;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getContentImages() {
        return contentImages;
    }

    public void setContentImages(String contentImages) {
        this.contentImages = contentImages;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}

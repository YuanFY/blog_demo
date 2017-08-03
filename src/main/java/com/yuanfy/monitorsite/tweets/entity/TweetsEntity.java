package com.yuanfy.monitorsite.tweets.entity;

import java.util.Date;
/**
 * @Description: TODO
 * @author yuanfy
 * @date 2017年8月3日 上午11:11:28 
 * @version 1.0
 */
public class TweetsEntity {
    private String id;

    private String tweetsContent;

    private String tweetsImages;

    private Date tweetsTime;

    private Long likeNum;

    private Long commentNum;

    private Integer userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTweetsContent() {
        return tweetsContent;
    }

    public void setTweetsContent(String tweetsContent) {
        this.tweetsContent = tweetsContent == null ? null : tweetsContent.trim();
    }

    public String getTweetsImages() {
        return tweetsImages;
    }

    public void setTweetsImages(String tweetsImages) {
        this.tweetsImages = tweetsImages == null ? null : tweetsImages.trim();
    }

    public Date getTweetsTime() {
        return tweetsTime;
    }

    public void setTweetsTime(Date tweetsTime) {
        this.tweetsTime = tweetsTime;
    }

    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }

    public Long getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Long commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
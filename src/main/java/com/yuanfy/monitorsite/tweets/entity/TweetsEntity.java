package com.yuanfy.monitorsite.tweets.entity;

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

    private Long tweetsTime;

    private int likeNum;

    private int commentNum;

    private Long userId;
    
    //用于页面展示
    private String beforeTime;
    
    private String userName;

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

    public Long getTweetsTime() {
        return tweetsTime;
    }

    public void setTweetsTime(Long tweetsTime) {
        this.tweetsTime = tweetsTime;
        this.setBeforeTime(tweetsTime);
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

	public String getBeforeTime() {
		return beforeTime;
	}

	public void setBeforeTime(Long tweetsTime) {
		long currentTimes = System.currentTimeMillis();
		long time = (currentTimes-tweetsTime.longValue())/1000;
		if (time < 60) {
			this.beforeTime = "刚刚";//小于一分钟
		} else if (time < 60 * 60) {
			this.beforeTime = time/60 + "分钟前";//小于一个小时用分钟表示
		} else if (time < 60 * 60 * 24) {
			this.beforeTime = time/(60 * 60) + "小时前";//小于一个天用小时表示
		} else if (time < 60 * 60 * 24 * 7) {
			this.beforeTime = time/(60 * 60 * 24) + "天前";//小于一个星期用天表示
		} else if (time < 60 * 60 * 24 * 30) {
			this.beforeTime = time/(60 * 60 * 24 * 7) + "周前";//小于一个月用星期表示
		} else if (time < 60 * 60 * 24 * 365) {
			this.beforeTime = time/(60 * 60 * 24 * 30) + "月前";//小于一个年用月表示
		} else if (time >= 60 * 60 * 24 * 365) {
			this.beforeTime = time/(60 * 60 * 24 * 365) + "年前";//大于一个年用年表示
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
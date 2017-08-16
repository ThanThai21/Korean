package com.esp.korean.Home.Forum;

public class ForumItem {

    private String subjectName;
    private int like;
    private int comment;

    public ForumItem(String subjectName) {
        this(subjectName, 0, 0);
    }

    public ForumItem(String subjectName, int like, int comment) {
        this.subjectName = subjectName;
        this.like = like;
        this.comment = comment;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getLike() {
        return like;
    }

    public int getComment() {
        return comment;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }
}

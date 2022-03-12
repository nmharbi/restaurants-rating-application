package com.example.swe483restruntproject.placeholder;

public class CommentHolder {
    public String comment;
    public double rate;
    public String user;
    public String getComment() {
        return comment;
    }

    public CommentHolder(String comment, double rate, String user) {
        this.comment = comment;
        this.rate = rate;
        this.user = user;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

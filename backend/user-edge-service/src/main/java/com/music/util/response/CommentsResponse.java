package com.music.util.response;

import com.music.domain.CommentInfo;

import java.util.List;

public class CommentsResponse extends Response {

    private List<CommentInfo> commentInfos;

    public CommentsResponse(List<CommentInfo> commentInfos) {
        this.commentInfos = commentInfos;
    }

    public List<CommentInfo> getCommentInfos() {
        return commentInfos;
    }

    public void setCommentInfos(List<CommentInfo> commentInfos) {
        this.commentInfos = commentInfos;
    }
}

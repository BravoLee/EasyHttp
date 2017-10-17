package com.bravo.easyhttpdemo.bean;

/**
 * Created by Administrator on 2017/10/17.
 */

public class User {

    /**
     * startLine : 5
     * startColumn : 4
     * startOffset : 390
     * endColumn : 99
     * endOffset : 485
     */

    private int startLine;
    private int startColumn;
    private int startOffset;
    private int endColumn;
    private int endOffset;

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public void setStartColumn(int startColumn) {
        this.startColumn = startColumn;
    }

    public int getStartOffset() {
        return startOffset;
    }

    public void setStartOffset(int startOffset) {
        this.startOffset = startOffset;
    }

    public int getEndColumn() {
        return endColumn;
    }

    public void setEndColumn(int endColumn) {
        this.endColumn = endColumn;
    }

    public int getEndOffset() {
        return endOffset;
    }

    public void setEndOffset(int endOffset) {
        this.endOffset = endOffset;
    }

    @Override
    public String toString() {
        return "User{" +
                "startLine=" + startLine +
                ", startColumn=" + startColumn +
                ", startOffset=" + startOffset +
                ", endColumn=" + endColumn +
                ", endOffset=" + endOffset +
                '}';
    }
}


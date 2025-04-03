package com.baeldung.spring.data.dynamodb.response.dto;

public class FinesDto {

    private String memberName;
    private String bookName;
    private Integer fineAmount;
    private String issueDate;
    private boolean paid;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(Integer fineAmount) {
        this.fineAmount = fineAmount;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}

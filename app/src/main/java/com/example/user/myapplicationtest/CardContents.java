package com.example.user.myapplicationtest;

public class CardContents {

    private int card_id;
    private String card_title;
    private byte[] card_image;
    private int card_index;
    private String createdate;

    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public String getCard_title() {
        return card_title;
    }

    public void setCard_title(String card_title) {
        this.card_title = card_title;
    }

    public byte[] getCard_image() {
        return card_image;
    }

    public void setCard_image(byte[] card_image) {
        this.card_image = card_image;
    }

    public int getCard_index() {
        return card_index;
    }

    public void setCard_index(int card_index) {
        this.card_index = card_index;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
}

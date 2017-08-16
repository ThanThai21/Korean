package com.esp.korean.Model;

public class Subject {

    private String title;
    private byte[] bitmap;
    private int imageResId;

    public Subject() {

    }

    public Subject(String title, int imageResId) {
        this.title = title;
        this.imageResId = imageResId;
    }

    public Subject(String title, byte[] bitmap) {
        this.title = title;
        this.bitmap = bitmap;
    }
}

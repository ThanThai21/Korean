package com.esp.korean.Model;

public class Item {

    private String korean;
    private String pronunciation;
    private String vietnamese;
    private byte[] audio;

    public Item(String korean, String pronunciation, String vietnamese) {
        this.korean = korean;
        this.pronunciation = pronunciation;
        this.vietnamese = vietnamese;
    }

    public Item(String korean, String pronunciation, String vietnamese, byte[] audio) {
        this.korean = korean;
        this.pronunciation = pronunciation;
        this.vietnamese = vietnamese;
        this.audio = audio;
    }

    public String getKorean() {
        return korean;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public String getVietnamese() {
        return vietnamese;
    }

    public byte[] getAudio() {
        return audio;
    }
}

package com.esp.korean.SubjectDetail;

public class SubjectDetailItem {

    private String korean;
    private String pronunciation;
    private String  vietnamese;
    private boolean isFavorite;

    public SubjectDetailItem(String korean, String pronunciation, String vietnamese) {
        this(korean, pronunciation, vietnamese, false);
    }

    public SubjectDetailItem(String korean, String pronunciation, String vietnamese, boolean isFavorite) {
        this.korean = korean;
        this.pronunciation = pronunciation;
        this.vietnamese = vietnamese;
        this.isFavorite = isFavorite;
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

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}

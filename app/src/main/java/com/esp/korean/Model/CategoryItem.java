package com.esp.korean.Model;

import java.util.List;

public class CategoryItem {

    private String title;
    private List<Subject> subjectList;

    public CategoryItem(String title, List<Subject> subjectList) {
        this.title = title;
        this.subjectList = subjectList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }
}

package com.example.convert;

public class Text {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Text() {
    }

    @Override
    public String toString() {
        return "Text{" +
            "text='" + text + '\'' +
            '}';
    }
}

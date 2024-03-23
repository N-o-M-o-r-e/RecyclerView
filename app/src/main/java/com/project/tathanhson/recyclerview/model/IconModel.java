package com.project.tathanhson.recyclerview.model;

import android.graphics.Bitmap;

public class IconModel {
    private final Bitmap bitmapImg;
    private final String content;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected){
        isSelected = selected;
    }

    public IconModel(Bitmap bitmapImg, String content) {
        this.bitmapImg = bitmapImg;
        this.content = content;
    }

    public Bitmap getBitmapImg() {
        return bitmapImg;
    }

    public String getContent() {
        return content;
    }



}

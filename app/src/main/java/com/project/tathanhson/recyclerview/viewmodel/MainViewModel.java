package com.project.tathanhson.recyclerview.viewmodel;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.project.tathanhson.recyclerview.model.IconModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends BaseViewModel {
    private List<IconModel> listStory;

    public List<IconModel> getListStory() {
        return listStory;
    }

    public void readStoryFile(AssetManager assetManager) {
        listStory = new ArrayList<>();
        try {
            String[] listFileName = assetManager.list("photo/");
            for (String fileName : listFileName) {
                String imagePath = "photo" + "/" + fileName;
                Bitmap bitmap = getBitmapFromAsset(assetManager, imagePath);
                String name = fileName.replace(".png", "");
                IconModel model = new IconModel(bitmap, name);
                listStory.add(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Bitmap getBitmapFromAsset(AssetManager assetManager, String filePath) {
        InputStream inputStream = null;
        Bitmap bitmap = null;
        try {
            inputStream = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;


    }

}

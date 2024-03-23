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

    private String topicName;


    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public List<IconModel> getListStory() {
        return listStory;
    }

    public void readStoryFile(AssetManager assetManager) {
        listStory = new ArrayList<>();
        try {
            String[] listFileName = assetManager.list("photo/");
            for (String fileName : listFileName) {
                // Đường dẫn đầy đủ của tệp ảnh
                String imagePath = "photo" + "/" + fileName;
                // Đọc bitmap từ tệp ảnh
                Bitmap bitmap = getBitmapFromAsset(assetManager, imagePath);
                // Loại bỏ phần mở rộng tên tệp để có tên
                String name = fileName.replace(".png", "");
                // Tạo một đối tượng IconModel từ bitmap và tên
                IconModel model = new IconModel(bitmap, name);
                // Thêm IconModel vào danh sách
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
            // Đọc dữ liệu ảnh và tạo một bitmap từ dữ liệu này
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

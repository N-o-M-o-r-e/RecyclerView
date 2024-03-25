package com.project.tathanhson.recyclerview;

import android.app.Application;

public class MyApplication extends Application {
    private static MyApplication instance;
    private Storage storage;

    @Override
    public void onCreate() {
        super.onCreate();
        storage = new Storage();
        instance = this;
    }

    public static MyApplication getInstance(){
        return instance;
    }

    public Storage getStorage() {
        return storage;
    }
}

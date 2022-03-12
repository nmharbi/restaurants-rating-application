package com.example.swe483restruntproject.placeholder;

import android.graphics.Bitmap;
import android.net.Uri;

import java.net.URI;

public class photoModel {

    Bitmap photo;

    public photoModel(Bitmap photo) {
        this.photo = photo;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}

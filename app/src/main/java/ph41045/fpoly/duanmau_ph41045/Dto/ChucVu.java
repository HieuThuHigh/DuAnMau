package com.example.sampleandroidproject.dto;

import androidx.annotation.NonNull;

public class ChucVu {
    private int chucVuCode;
    private String chucVuName;

    public ChucVu(int chucVuCode, String chucVuName) {
        this.chucVuCode = chucVuCode;
        this.chucVuName = chucVuName;
    }

    @NonNull
    @Override
    public String toString() {
        return chucVuName;
    }

    public int getChucVuCode() {
        return chucVuCode;
    }
}

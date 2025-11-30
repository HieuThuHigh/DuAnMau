package com.example.sampleandroidproject.model;

public class TopSanPham {
    private String maSanPham;
    private String tenSanPham;
    private int tongSoLuong;

    public TopSanPham() {
    }

    public TopSanPham(String maSanPham, String tenSanPham, int tongSoLuong) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.tongSoLuong = tongSoLuong;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public int getTongSoLuong() {
        return tongSoLuong;
    }
}


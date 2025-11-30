package com.example.sampleandroidproject.model;

public class TopKhachHang {
    private String maKhachHang;
    private String tenKhachHang;
    private int soLanMua;
    private double tongChiTieu;

    public TopKhachHang() {
    }

    public TopKhachHang(String maKhachHang, String tenKhachHang, int soLanMua, double tongChiTieu) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.soLanMua = soLanMua;
        this.tongChiTieu = tongChiTieu;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public int getSoLanMua() {
        return soLanMua;
    }

    public double getTongChiTieu() {
        return tongChiTieu;
    }
}

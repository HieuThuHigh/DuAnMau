package com.example.sampleandroidproject.model;

public class HoaDonChiTiet {
    private String maCTHD;
    private String maHoaDon;
    private String maSanPham;
    private int soLuong;
    private double donGia;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String maCTHD, String maHoaDon, String maSanPham, int soLuong, double donGia) {
        this.maCTHD = maCTHD;
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    // getter và setter
    public String getMaCTHD() {
        return maCTHD;
    }

    public void setMaCTHD(String maCTHD) {
        this.maCTHD = maCTHD;
    }

    // Getter và Setter cho maHoaDon
    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    // Getter và Setter cho maSanPham
    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    // Getter và Setter cho soLuong
    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    // Getter và Setter cho donGia
    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
}
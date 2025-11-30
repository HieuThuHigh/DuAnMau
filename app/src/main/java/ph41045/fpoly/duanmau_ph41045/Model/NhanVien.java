package com.example.sampleandroidproject.model;

import android.os.Parcel;
import android.os.Parcelable;

public class NhanVien implements Parcelable {
    private String maNhanVien;
    private String tenNhanVien;
    private String diaChi;
    private int chucVu;     // 1 là Quản lý; 0 là Nhân viên
    private double luong;
    private String matKhau;

    public NhanVien() {
    }

    public NhanVien(String maNhanVien, String tenNhanVien, String diaChi, int chucVu, double luong, String matKhau) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.diaChi = diaChi;
        this.chucVu = chucVu;
        this.luong = luong;
        this.matKhau = matKhau;
    }

    protected NhanVien(Parcel in) {
        maNhanVien = in.readString();
        tenNhanVien = in.readString();
        diaChi = in.readString();
        chucVu = in.readInt();
        luong = in.readDouble();
        matKhau = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(maNhanVien);
        dest.writeString(tenNhanVien);
        dest.writeString(diaChi);
        dest.writeInt(chucVu);
        dest.writeDouble(luong);
        dest.writeString(matKhau);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NhanVien> CREATOR = new Creator<NhanVien>() {
        @Override
        public NhanVien createFromParcel(Parcel in) {
            return new NhanVien(in);
        }

        @Override
        public NhanVien[] newArray(int size) {
            return new NhanVien[size];
        }
    };

    // Getter và Setter cho maNhanVien
    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    // Getter và Setter cho tenNhanVien
    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    // Getter và Setter cho diaChi
    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    // Getter và Setter cho chucVu
    public int getChucVu() {
        return chucVu;
    }

    public void setChucVu(int chucVu) {
        this.chucVu = chucVu;
    }

    // Getter và Setter cho luong
    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }

    // Getter và Setter cho matKhau
    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}

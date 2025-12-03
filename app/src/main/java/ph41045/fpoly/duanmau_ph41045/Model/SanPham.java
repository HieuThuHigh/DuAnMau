package ph41045.fpoly.duanmau_ph41045.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

public class SanPham implements Parcelable {
    private String maSanPham;
    private String tenSanPham;
    private int giaSanPham;
    private int soLuong;
    private String donViTinh;
    private String ngayNhap;
    private String maDanhMuc;

    public SanPham() {
    }

    protected SanPham(Parcel in) {
        maSanPham = in.readString();
        tenSanPham = in.readString();
        giaSanPham = in.readInt();
        soLuong = in.readInt();
        donViTinh = in.readString();
        ngayNhap = in.readString();
        maDanhMuc = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(maSanPham);
        dest.writeString(tenSanPham);
        dest.writeInt(giaSanPham);
        dest.writeInt(soLuong);
        dest.writeString(donViTinh);
        dest.writeString(ngayNhap);
        dest.writeString(maDanhMuc);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SanPham> CREATOR = new Creator<SanPham>() {
        @Override
        public SanPham createFromParcel(Parcel in) {
            return new SanPham(in);
        }

        @Override
        public SanPham[] newArray(int size) {
            return new SanPham[size];
        }
    };

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof SanPham)) throw new IllegalArgumentException();
        SanPham sp = (SanPham) obj;
        return sp.getMaSanPham().equals(this.getMaSanPham());
    }

    public SanPham(String maSanPham, String tenSanPham, int giaSanPham, int soLuong, String donViTinh, String ngayNhap, String maDanhMuc) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.soLuong = soLuong;
        this.donViTinh = donViTinh;
        this.ngayNhap = ngayNhap;
        this.maDanhMuc = maDanhMuc;
    }

    // getter and setter
    public String getMaSanPham() {
        return maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public String getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public void setMaDanhMuc(String maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }
}

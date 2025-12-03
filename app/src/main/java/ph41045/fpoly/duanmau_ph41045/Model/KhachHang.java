package ph41045.fpoly.duanmau_ph41045.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class KhachHang implements Parcelable {
    private String maKhachHang;
    private String tenKhachHang;
    private String diaChi;
    private String soDienThoai;
    private String email;
    private double tongChiTieu;
    private int avatar; // Lưu ID ảnh trong drawable

    public KhachHang() {
    }

    public KhachHang(String maKhachHang, String tenKhachHang, String diaChi, String soDienThoai, String email) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
    }

    protected KhachHang(Parcel in) {
        maKhachHang = in.readString();
        tenKhachHang = in.readString();
        diaChi = in.readString();
        soDienThoai = in.readString();
        email = in.readString();
        tongChiTieu = in.readDouble();
        avatar = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(maKhachHang);
        dest.writeString(tenKhachHang);
        dest.writeString(diaChi);
        dest.writeString(soDienThoai);
        dest.writeString(email);
        dest.writeDouble(tongChiTieu);
        dest.writeInt(avatar);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<KhachHang> CREATOR = new Creator<KhachHang>() {
        @Override
        public KhachHang createFromParcel(Parcel in) {
            return new KhachHang(in);
        }

        @Override
        public KhachHang[] newArray(int size) {
            return new KhachHang[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return tenKhachHang + " - " + soDienThoai;
    }

    //getter and setter
    public double getTongChiTieu() {
        return tongChiTieu;
    }

    public void setTongChiTieu(double tongChiTieu) {
        this.tongChiTieu = tongChiTieu;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    // Getter và Setter cho maKhachHang
    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    // Getter và Setter cho tenKhachHang
    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    // Getter và Setter cho diaChi
    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    // Getter và Setter cho soDienThoai
    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    // Getter và Setter cho email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}


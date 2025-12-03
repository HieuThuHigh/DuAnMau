package ph41045.fpoly.duanmau_ph41045.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class DanhMuc implements Parcelable {
    private String maDanhMuc;
    private String tenDanhMuc;

    public DanhMuc() {
    }

    public DanhMuc(String maDanhMuc, String tenDanhMuc) {
        this.maDanhMuc = maDanhMuc;
        this.tenDanhMuc = tenDanhMuc;
    }

    protected DanhMuc(Parcel in) {
        maDanhMuc = in.readString();
        tenDanhMuc = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(maDanhMuc);
        dest.writeString(tenDanhMuc);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DanhMuc> CREATOR = new Creator<DanhMuc>() {
        @Override
        public DanhMuc createFromParcel(Parcel in) {
            return new DanhMuc(in);
        }

        @Override
        public DanhMuc[] newArray(int size) {
            return new DanhMuc[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return tenDanhMuc;
    }

    public String getMaDanhMuc() {
        return maDanhMuc;
    }
    public void setMaDanhMuc(String maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }
    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }
}


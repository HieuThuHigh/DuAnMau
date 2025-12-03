package ph41045.fpoly.duanmau_ph41045.Common;

import androidx.annotation.Nullable;

import ph41045.fpoly.duanmau_ph41045.Model.SanPham;


public class GioHangItem {
    private SanPham sp;
    private int soLuong;

    public GioHangItem(SanPham sp, int soLuong) {
        this.sp = sp;
        this.soLuong = soLuong;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof GioHangItem)) throw new IllegalArgumentException();
        GioHangItem sp = (GioHangItem) obj;
        return sp.getSanPham().getMaSanPham().equals(this.sp.getMaSanPham());
    }

    public SanPham getSanPham() {
        return sp;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}

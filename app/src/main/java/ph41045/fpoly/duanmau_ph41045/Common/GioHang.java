package ph41045.fpoly.duanmau_ph41045.Common;


import java.util.ArrayList;
import java.util.List;

import ph41045.fpoly.duanmau_ph41045.Model.SanPham;

public class GioHang {
    private List<GioHangItem> danhSachGioHang;

    public GioHang() {
        danhSachGioHang = new ArrayList<>();
    }

    public void addSanPham(SanPham sp) {
        for (GioHangItem item : danhSachGioHang) {
            if (item.getSanPham().getMaSanPham().equals(sp.getMaSanPham())) {
                item.setSoLuong(item.getSoLuong() + 1);
                return;
            }
        }
        GioHangItem gioHangItem = new GioHangItem(sp, 1);
        danhSachGioHang.add(gioHangItem);
    }

    public List<GioHangItem> getDanhSachGioHang() {
        return danhSachGioHang;
    }
}

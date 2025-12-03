package ph41045.fpoly.duanmau_ph41045.Common;

public class Common {
    private static GioHang gioHang;
    public static String maNhanVien;

    public static GioHang getGioHang() {
        if (gioHang == null) gioHang = new GioHang();
        return gioHang;
    }
}

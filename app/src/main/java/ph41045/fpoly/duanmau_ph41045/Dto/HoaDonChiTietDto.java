package ph41045.fpoly.duanmau_ph41045.Dto;

public class HoaDonChiTietDto {
    private String maCTHD;
    private String maHoaDon;
    private String maSanPham;
    private String tenSanPham;
    private int soLuong;
    private double donGia;

    public HoaDonChiTietDto(String maCTHD, String maHoaDon, String maSanPham, String tenSanPham, int soLuong, double donGia) {
        this.maCTHD = maCTHD;
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getTenSanPham() {
        return tenSanPham;
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
}
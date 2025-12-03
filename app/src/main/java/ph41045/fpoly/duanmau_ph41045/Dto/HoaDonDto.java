package ph41045.fpoly.duanmau_ph41045.Dto;

public class HoaDonDto {
    private String maHoaDon;
    private String maNhanVien;
    private String tenNhanVien;
    private String maKhachHang;
    private String tenKhachHang;
    private String ngayLap;
    private double tongTien;

    public HoaDonDto(String maHoaDon, String maNhanVien, String tenNhanVien, String maKhachHang, String tenKhachHang, String ngayLap, double tongTien) {
        this.maHoaDon = maHoaDon;
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public double getTongTien() {
        return tongTien;
    }
}

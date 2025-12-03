package ph41045.fpoly.duanmau_ph41045.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;



import java.util.ArrayList;
import java.util.List;

import ph41045.fpoly.duanmau_ph41045.Dto.HoaDonChiTietDto;
import ph41045.fpoly.duanmau_ph41045.Dto.HoaDonDto;
import ph41045.fpoly.duanmau_ph41045.Model.DanhMuc;
import ph41045.fpoly.duanmau_ph41045.Model.HoaDon;
import ph41045.fpoly.duanmau_ph41045.Model.HoaDonChiTiet;
import ph41045.fpoly.duanmau_ph41045.Model.KhachHang;
import ph41045.fpoly.duanmau_ph41045.Model.NhanVien;
import ph41045.fpoly.duanmau_ph41045.Model.SanPham;
import ph41045.fpoly.duanmau_ph41045.Model.TopKhachHang;
import ph41045.fpoly.duanmau_ph41045.Model.TopSanPham;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TEN_CSDL = "JPMart.db";
    private static final int PHIEN_BAN_CSDL = 25;
    public DatabaseHelper(Context context) {
        super(context, TEN_CSDL, null, PHIEN_BAN_CSDL);
    }

    // Bảng Danh Mục
    public static final String BANG_DANHMUC = "DanhMuc";
    public static final String COT_TEN_DANH_MUC = "ten_danh_muc";
    private static final String COT_MA_DANH_MUC = "maDanhMuc";

    // Tên bảng và các cột
    private static final String BANG_HOADON = "HoaDon";
    private static final String COLUMN_MA_HOADON = "maHoaDon";
    private static final String COLUMN_MA_NHANVIEN = "maNhanVien";
    private static final String COLUMN_MA_KHACHHANG = "maKhachHang";
    private static final String COLUMN_NGAY_LAP = "ngayLap";
    private static final String COLUMN_TONG_TIEN = "tongTien";

    private static final String BANG_SANPHAM = "SanPham";
    private static final String COT_MA_SP = "maSanPham";
    private static final String COT_TEN_SAN_PHAM = "tenSanPham";
    private static final String COT_GIA = "giaSanPham";
    private static final String COT_DON_VI_TINH = "donViTinh";
    private static final String COT_NGAY_NHAP = "ngayNhap";

    // Tên bảng và các cột của bảng NhanVien
    public static final String BANG_NHANVIEN = "NhanVien";
    public static final String COT_MA_NHANVIEN = "maNhanVien";
    public static final String COT_TEN_NHANVIEN = "tenNhanVien";
    public static final String COT_DIA_CHI = "diaChi";
    public static final String COT_CHUC_VU = "chucVu";
    public static final String COT_LUONG = "luong";
    public static final String COT_MAT_KHAU = "matKhau";

    // Tên bảng và các cột của bảng KhachHang
    public static final String BANG_KHACHHANG = "KhachHang";
    public static final String COT_MA_KHACHHANG = "maKhachHang";
    public static final String COT_TEN_KHACHHANG = "tenKhachHang";
    public static final String COT_DIA_CHI_KH = "diaChi";
    public static final String COT_SO_DIEN_THOAI = "soDienThoai";
    public static final String COT_EMAIL = "email";

    // Tên bảng và các cột của bảng HoaDonChiTiet
    public static final String BANG_HOADONCHITIET = "HoaDonChiTiet";
    public static final String COT_MA_HDCT = "maHDCT";
    public static final String COT_MA_HOADON = "maHoaDon";
    public static final String COT_MA_SANPHAM = "maSanPham";
    public static final String COT_SO_LUONG = "soLuong";
    public static final String COT_DON_GIA = "donGia";

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng Danh Mục
        String TAO_BANG_DANH_MUC = "CREATE TABLE IF NOT EXISTS " + BANG_DANHMUC + " ("
                + COT_MA_DANH_MUC + " TEXT PRIMARY KEY, "
                + COT_TEN_DANH_MUC + " TEXT)";

        // Tạo bảng Sản phẩm
        String TAO_BANG_SAN_PHAM = "CREATE TABLE IF NOT EXISTS " + BANG_SANPHAM + " ("
                + COT_MA_SP + " TEXT PRIMARY KEY, "
                + COT_TEN_SAN_PHAM + " TEXT, "
                + COT_GIA + " INTEGER, "
                + COT_SO_LUONG + " INTEGER, "
                + COT_DON_VI_TINH + " TEXT, "
                + COT_NGAY_NHAP + " TEXT, "
                + COT_MA_DANH_MUC + " TEXT, "
                + "FOREIGN KEY (" + COT_MA_DANH_MUC + ") REFERENCES " + BANG_DANHMUC + "(" + COT_MA_DANH_MUC + ")"
                + ")";

        // Câu lệnh tạo bảng NhanVien
        String TAO_BANG_NHAN_VIEN =
                "CREATE TABLE " + BANG_NHANVIEN + " (" +
                        COT_MA_NHANVIEN + " TEXT PRIMARY KEY, " +  // Mã nhân viên là khóa chính
                        COT_TEN_NHANVIEN + " TEXT NOT NULL, " +    // Tên nhân viên, không được null
                        COT_DIA_CHI + " TEXT, " +            // Địa chỉ có thể null
                        COT_CHUC_VU + " INT, " +            // 1 là Quản lý, 0 là nhân viên
                        COT_LUONG + " REAL NOT NULL, " +    // Lương kiểu REAL (số thực)
                        COT_MAT_KHAU + " TEXT NOT NULL)";    // Mật khẩu

        // Câu lệnh tạo bảng KhachHang
        String TAO_BANG_KHACH_HANG =
                "CREATE TABLE " + BANG_KHACHHANG + " (" +
                        COT_MA_KHACHHANG + " TEXT PRIMARY KEY, " +  // Mã khách hàng là khóa chính
                        COT_TEN_KHACHHANG + " TEXT NOT NULL, " +    // Tên khách hàng, không được null
                        COT_DIA_CHI_KH + " TEXT, " +         // Địa chỉ có thể null
                        COT_SO_DIEN_THOAI + " TEXT NOT NULL, " + // Số điện thoại bắt buộc nhập
                        COT_EMAIL + " TEXT NOT NULL)";        // Email bắt buộc nhập

        // Tạo bảng Hóa đơn
        String TAO_BANG_HOA_DON = "CREATE TABLE IF NOT EXISTS " + BANG_HOADON + " (" +
                COLUMN_MA_HOADON + " TEXT PRIMARY KEY, " +
                COLUMN_MA_NHANVIEN + " TEXT, " +
                COLUMN_MA_KHACHHANG + " TEXT, " +
                COLUMN_NGAY_LAP + " TEXT, " +
                COLUMN_TONG_TIEN + " INTEGER, "
                + "FOREIGN KEY (" + COLUMN_MA_NHANVIEN + ") REFERENCES " + BANG_NHANVIEN + "(" + COLUMN_MA_NHANVIEN + "), "
                + "FOREIGN KEY (" + COLUMN_MA_KHACHHANG + ") REFERENCES " + BANG_KHACHHANG + "(" + COLUMN_MA_KHACHHANG + ")"
                + ")";

        // Câu lệnh tạo bảng HoaDonChiTiet
        String TAO_BANG_HDCT =
                "CREATE TABLE " + BANG_HOADONCHITIET + " (" +
                        COT_MA_HDCT + " TEXT PRIMARY KEY, " +
                        COT_MA_HOADON + " TEXT NOT NULL, " +
                        COT_MA_SANPHAM + " TEXT NOT NULL, " +
                        COT_SO_LUONG + " INTEGER NOT NULL, " +
                        COT_DON_GIA + " REAL NOT NULL, "
                        + "FOREIGN KEY (" + COT_MA_HOADON + ") REFERENCES " + BANG_HOADON + "(" + COLUMN_MA_HOADON + "), "
                        + "FOREIGN KEY (" + COT_MA_SANPHAM + ") REFERENCES " + BANG_SANPHAM + "(" + COT_MA_SP + ")"
                        + ")";

        db.execSQL(TAO_BANG_DANH_MUC);
        db.execSQL(TAO_BANG_SAN_PHAM);
        db.execSQL(TAO_BANG_NHAN_VIEN);
        db.execSQL(TAO_BANG_KHACH_HANG);
        db.execSQL(TAO_BANG_HOA_DON);
        db.execSQL(TAO_BANG_HDCT);
        db.execSQL("INSERT INTO NhanVien VALUES ('admin01', 'Admin', 'HN', 1, 10000000, '123456')");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + BANG_SANPHAM);
        db.execSQL("DROP TABLE IF EXISTS " + BANG_HOADON);
        db.execSQL("DROP TABLE IF EXISTS " + BANG_DANHMUC);
        db.execSQL("DROP TABLE IF EXISTS " + BANG_NHANVIEN);
        db.execSQL("DROP TABLE IF EXISTS " + BANG_KHACHHANG);
        db.execSQL("DROP TABLE IF EXISTS " + BANG_HOADONCHITIET);
        onCreate(db);
    }
    public boolean themSanPham(SanPham sanPham) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(COT_MA_SP, sanPham.getMaSanPham());
        value.put(COT_TEN_SAN_PHAM, sanPham.getTenSanPham());
        value.put(COT_GIA, sanPham.getGiaSanPham());
        value.put(COT_SO_LUONG, sanPham.getSoLuong());
        value.put(COT_DON_VI_TINH, sanPham.getDonViTinh());
        value.put(COT_NGAY_NHAP, sanPham.getNgayNhap());
        value.put(COT_MA_DANH_MUC, sanPham.getMaDanhMuc());

        long rows = db.insert(BANG_SANPHAM, null, value);
        db.close();
        return rows > 0;
    }
    public List<SanPham> timKiemSanPham(String tenSP) {
        List<SanPham> danhSachSanPham = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM SanPham WHERE tenSanPham LIKE ?", new String[]{"%" + tenSP + "%"});
        if (cursor.moveToFirst()) {
            do {
                SanPham sp = new SanPham(
                        cursor.getString(0), // Mã sản phẩm
                        cursor.getString(1), // Tên sản phẩm
                        cursor.getInt(2),    // Giá
                        cursor.getInt(3),    // Số lượng
                        cursor.getString(4), // Đơn vị tính
                        cursor.getString(5), // Ngày nhập
                        cursor.getString(6)  // Mã danh mục
                );
                danhSachSanPham.add(sp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return danhSachSanPham;
    }
    public boolean xoaSanPham(String maSanPham) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(BANG_SANPHAM, COT_MA_SP + " = ?", new String[]{maSanPham});
        db.close();
        return rows > 0;
    }
    public boolean suaSanPham(SanPham sanPham) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(COT_TEN_SAN_PHAM, sanPham.getTenSanPham());
        value.put(COT_GIA, sanPham.getGiaSanPham());
        value.put(COT_SO_LUONG, sanPham.getSoLuong());
        value.put(COT_DON_VI_TINH, sanPham.getDonViTinh());
        value.put(COT_NGAY_NHAP, sanPham.getNgayNhap());
        value.put(COT_MA_DANH_MUC, sanPham.getMaDanhMuc());

        int rows = db.update(BANG_SANPHAM, value, COT_MA_SP + " = ?", new String[]{sanPham.getMaSanPham()});
        db.close();
        return rows > 0;
    }

    // Lấy danh sách tất cả sản phẩm Không cần dùng hàm này, khi nào cần lấy tất cả sản phẩm thì
    // gọi hàm timKiemSanPham với tham số truyền vào là xâu rỗng

//    public List<SanPham> layTatCaSanPham() {
//        List<SanPham> danhSachSanPham = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.rawQuery("SELECT * FROM " + BANG_SANPHAM, null);
//        if (cursor.moveToFirst()) {
//            do {
//                SanPham sp = new SanPham(
//                        cursor.getString(0), // Mã sản phẩm
//                        cursor.getString(1), // Tên sản phẩm
//                        cursor.getInt(2),    // Giá
//                        cursor.getInt(3),    // Số lượng
//                        cursor.getString(4), // Đơn vị tính
//                        cursor.getString(5), // Ngày nhập
//                        cursor.getString(6)  // Mã danh mục
//                );
//                danhSachSanPham.add(sp);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return danhSachSanPham;
//    }

    public void themHoaDon(HoaDon hoaDon) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues giaTri = new ContentValues();

        giaTri.put(COLUMN_MA_HOADON, hoaDon.getMaHoaDon());
        giaTri.put(COLUMN_MA_NHANVIEN, hoaDon.getMaNhanVien());
        giaTri.put(COLUMN_MA_KHACHHANG, hoaDon.getMaKhachHang());
        giaTri.put(COLUMN_NGAY_LAP, hoaDon.getNgayLap());
        giaTri.put(COLUMN_TONG_TIEN, hoaDon.getTongTien());

        long rows = db.insert(BANG_HOADON, null, giaTri);
        db.close();
    }

    public List<HoaDonDto> layDanhSachHoaDon() {
        List<HoaDonDto> danhSachHoaDon = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Truy vấn lấy dữ liệu từ bảng HoaDon, kết hợp với bảng KhachHang và NhanVien để lấy tên
        String query = "SELECT h." + COLUMN_MA_HOADON + ", " +
                "h." + COLUMN_MA_NHANVIEN + ", " +
                "nv." + COT_TEN_NHANVIEN + ", " + // Tên nhân viên
                "h." + COLUMN_MA_KHACHHANG + ", " +
                "kh." + COT_TEN_KHACHHANG + ", " + // Tên khách hàng
                "h." + COLUMN_NGAY_LAP + ", " +
                "h." + COLUMN_TONG_TIEN + " " +
                "FROM " + BANG_HOADON + " h " +
                "INNER JOIN " + BANG_NHANVIEN + " nv ON h." + COLUMN_MA_NHANVIEN + " = nv." + COT_MA_NHANVIEN + " " +
                "INNER JOIN " + BANG_KHACHHANG + " kh ON h." + COLUMN_MA_KHACHHANG + " = kh." + COT_MA_KHACHHANG;

        // Thực thi câu truy vấn
        Cursor cursor = db.rawQuery(query, null);

        // Di chuyển con trỏ đến bản ghi đầu tiên
        if (cursor.moveToFirst()) {
            do {
                // Tạo đối tượng HoaDon từ dữ liệu truy vấn
                HoaDonDto hoaDon = new HoaDonDto(
                        cursor.getString(0), // Mã hóa đơn
                        cursor.getString(1), // Mã nhân viên
                        cursor.getString(2), // Tên nhân viên
                        cursor.getString(3), // Mã khách hàng
                        cursor.getString(4), // Tên khách hàng
                        cursor.getString(5), // Ngày lập
                        cursor.getInt(6) // Tổng tiền
                );
                // Thêm hóa đơn vào danh sách
                danhSachHoaDon.add(hoaDon);
            } while (cursor.moveToNext());
        }

        // Đóng cursor và database
        cursor.close();
        db.close();

        return danhSachHoaDon;
    }

    // xoá hoá đơn
    public boolean xoaHoaDon(String maHoaDon) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(BANG_HOADON, COLUMN_MA_HOADON + " = ?", new String[]{maHoaDon});
        db.close();
        return rows > 0;
    }

    // Lấy tất cả danh mục
    public List<DanhMuc> layTatCaDanhMuc() {
        List<DanhMuc> danhSachDanhMuc = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + BANG_DANHMUC, null);

        if (cursor.moveToFirst()) {
            do {
                DanhMuc dm = new DanhMuc(
                        cursor.getString(0), // Mã danh mục
                        cursor.getString(1) // Tên danh mục
                );
                danhSachDanhMuc.add(dm);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return danhSachDanhMuc;
    }

    // xoá danh mục
    public boolean xoaDanhMuc(String maDanhMuc) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(BANG_DANHMUC, COT_MA_DANH_MUC + " = ?", new String[]{maDanhMuc});
        db.close();
        return result > 0;
    }

    // sửa danh mục
    public boolean suaDanhMuc(DanhMuc danhMuc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COT_TEN_DANH_MUC, danhMuc.getTenDanhMuc());

        int result = db.update(BANG_DANHMUC, values, COT_MA_DANH_MUC + " = ?", new String[]{danhMuc.getMaDanhMuc()});
        db.close();
        return result > 0;
    }

    public boolean themDanhMuc(DanhMuc danhMuc) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COT_MA_DANH_MUC, danhMuc.getMaDanhMuc());
        values.put(COT_TEN_DANH_MUC, danhMuc.getTenDanhMuc());

        long result = db.insert(BANG_DANHMUC, null, values);
        db.close();
        return result > 0;
    }
    public boolean themNhanVien(NhanVien nhanVien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COT_MA_NHANVIEN, nhanVien.getMaNhanVien());
        values.put(COT_TEN_NHANVIEN, nhanVien.getTenNhanVien());
        values.put(COT_DIA_CHI, nhanVien.getDiaChi());
        values.put(COT_CHUC_VU, nhanVien.getChucVu()); // 1 = Quản lý, 0 = Nhân viên
        values.put(COT_LUONG, nhanVien.getLuong());
        values.put(COT_MAT_KHAU, nhanVien.getMatKhau());

        long result = db.insert(BANG_NHANVIEN, null, values);
        db.close();
        return result > 0;
    }

    // lấy danh sách nhân viên
    public List<NhanVien> layTatCaNhanVien() {
        List<NhanVien> danhSachNhanVien = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + BANG_NHANVIEN, null);

        if (cursor.moveToFirst()) {
            do {
                NhanVien nv = new NhanVien(
                        cursor.getString(0), // Mã nhân viên
                        cursor.getString(1), // Tên nhân viên
                        cursor.getString(2), // Địa chỉ
                        cursor.getInt(3), // Chức vụ
                        cursor.getDouble(4), // Lương
                        cursor.getString(5)  // Mật khẩu
                );
                danhSachNhanVien.add(nv);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return danhSachNhanVien;
    }

    // xoá nhân viên
    public boolean xoaNhanVien(String maNV) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(BANG_NHANVIEN, COT_MA_NHANVIEN + " = ?", new String[]{maNV});
        db.close();
        return result > 0;
    }

    public boolean suaNhanVien(NhanVien nhanVien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COT_TEN_NHANVIEN, nhanVien.getTenNhanVien());
        values.put(COT_DIA_CHI, nhanVien.getDiaChi());
        values.put(COT_CHUC_VU, nhanVien.getChucVu()); // Lưu 0 hoặc 1
        values.put(COT_LUONG, nhanVien.getLuong());
        values.put(COT_MAT_KHAU, nhanVien.getMatKhau());

        int result = db.update(BANG_NHANVIEN, values, COT_MA_NHANVIEN + " = ?", new String[]{nhanVien.getMaNhanVien()});
        db.close();
        return result > 0;
    }

    public List<KhachHang> layTatCaKhachHang() {
        List<KhachHang> danhSachKhachHang = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + BANG_KHACHHANG, null);

        if (cursor.moveToFirst()) {
            do {
                KhachHang khachHang = new KhachHang(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                danhSachKhachHang.add(khachHang);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return danhSachKhachHang;
    }

    public boolean themKhachHang(KhachHang khachHang) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COT_MA_KHACHHANG, khachHang.getMaKhachHang());
        values.put(COT_TEN_KHACHHANG, khachHang.getTenKhachHang());
        values.put(COT_DIA_CHI_KH, khachHang.getDiaChi());
        values.put(COT_SO_DIEN_THOAI, khachHang.getSoDienThoai());
        values.put(COT_EMAIL, khachHang.getEmail());

        long result = db.insert(BANG_KHACHHANG, null, values);
        db.close();
        return result > 0;
    }

    // xoá khách hàng
    public boolean xoaKhachHang(String maKH) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(BANG_KHACHHANG, COT_MA_KHACHHANG + " = ?", new String[]{maKH});
        db.close();
        return result > 0;
    }

    public boolean suaKhachHang(KhachHang khachHang) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COT_TEN_KHACHHANG, khachHang.getTenKhachHang());
        values.put(COT_DIA_CHI_KH, khachHang.getDiaChi());
        values.put(COT_SO_DIEN_THOAI, khachHang.getSoDienThoai());
        values.put(COT_EMAIL, khachHang.getEmail());

        int result = db.update(BANG_KHACHHANG, values, COT_MA_KHACHHANG + " = ?", new String[]{khachHang.getMaKhachHang()});
        db.close();
        return result > 0; // Trả về true nếu cập nhật thành công
    }

    // Thêm hoá đơn chi tiêt
    public void themHDCT(HoaDonChiTiet hoaDonChiTiet) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COT_MA_HDCT, hoaDonChiTiet.getMaCTHD());
        values.put(COT_MA_HOADON, hoaDonChiTiet.getMaHoaDon());
        values.put(COT_MA_SANPHAM, hoaDonChiTiet.getMaSanPham());
        values.put(COT_SO_LUONG, hoaDonChiTiet.getSoLuong());
        values.put(COT_DON_GIA, hoaDonChiTiet.getDonGia());

        db.insert(BANG_HOADONCHITIET, null, values);
        db.close();
    }

    public List<HoaDonChiTietDto> layTatCaHDCT(String maHoaDon) {
        List<HoaDonChiTietDto> danhSachHDCT = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Truy vấn để lấy tất cả chi tiết hóa đơn cho một mã hóa đơn
        String query = "SELECT hdct." + COT_MA_HDCT + ", " +
                "hdct." + COT_MA_HOADON + ", " +
                "hdct." + COT_MA_SANPHAM + ", " +
                "sp." + COT_TEN_SAN_PHAM + ", " +  // Lấy tên sản phẩm từ bảng SanPham
                "hdct." + COT_SO_LUONG + ", " +
                "hdct." + COT_DON_GIA +
                " FROM " + BANG_HOADONCHITIET + " hdct " +
                "INNER JOIN " + BANG_SANPHAM + " sp ON hdct." + COT_MA_SANPHAM + " = sp." + COT_MA_SP + " " +
                "WHERE hdct." + COT_MA_HOADON + " = ?";  // Điều kiện theo mã hóa đơn

        // Thực thi truy vấn với tham số mã hóa đơn
        Cursor cursor = db.rawQuery(query, new String[]{maHoaDon});

        // Kiểm tra kết quả trả về
        if (cursor.moveToFirst()) {
            do {
                HoaDonChiTietDto hdct = new HoaDonChiTietDto(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getDouble(5)
                );
                danhSachHDCT.add(hdct);
            } while (cursor.moveToNext());
        }

        // Đóng cursor và db sau khi xử lý xong
        cursor.close();
        db.close();

        return danhSachHDCT;
    }

    public String taoMaKhachHangMoi() {
        SQLiteDatabase db = this.getReadableDatabase();
        String maKhachHangMoi = "KH1";

        String query = "SELECT " + COT_MA_KHACHHANG + " FROM " + BANG_KHACHHANG +
                " ORDER BY " + COT_MA_KHACHHANG + " DESC LIMIT 1";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String lastMaKhachHang = cursor.getString(0);
            int lastNumber = Integer.parseInt(lastMaKhachHang.replace("KH", ""));
            maKhachHangMoi = "KH" + (lastNumber + 1);
        }

        cursor.close();
        db.close();
        return maKhachHangMoi;
    }

    public String taoMaNhanVienMoi() {
        SQLiteDatabase db = this.getReadableDatabase();
        String maNhanVienMoi = "NV1";

        String query = "SELECT " + COT_MA_NHANVIEN + " FROM " + BANG_NHANVIEN +
                " ORDER BY " + COT_MA_NHANVIEN + " DESC LIMIT 1";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String lastMaNhanVien = cursor.getString(0);
            int lastNumber = Integer.parseInt(lastMaNhanVien.replace("NV", ""));
            maNhanVienMoi = "NV" + (lastNumber + 1);
        }

        cursor.close();
        db.close();
        return maNhanVienMoi;
    }

    public String taoMaDanhMucMoi() {
        SQLiteDatabase db = this.getReadableDatabase();
        String maDanhMucMoi = "DM1";

        String query = "SELECT " + COT_MA_DANH_MUC + " FROM " + BANG_DANHMUC +
                " ORDER BY " + COT_MA_DANH_MUC + " DESC LIMIT 1";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String lastMaDanhMuc = cursor.getString(0);
            int lastNumber = Integer.parseInt(lastMaDanhMuc.replace("DM", ""));
            maDanhMucMoi = "DM" + (lastNumber + 1);
        }

        cursor.close();
        db.close();
        return maDanhMucMoi;
    }

    public String taoMaSanPhamMoi() {
        SQLiteDatabase db = this.getReadableDatabase();
        String maHDMoi = "SP1"; // Mã mặc định nếu bảng rỗng

        // Câu lệnh SQL lấy mã HDCT lớn nhất (bản ghi cuối cùng)
        String query = "SELECT " + COT_MA_SANPHAM + " FROM " + BANG_SANPHAM +
                " ORDER BY " + COT_MA_SANPHAM + " DESC LIMIT 1";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String lastMaSP = cursor.getString(0); // Lấy giá trị mã HDCT cuối cùng
            int lastNumber = Integer.parseInt(lastMaSP.replace("SP", "")); // Lấy số từ mã
            maHDMoi = "SP" + (lastNumber + 1); // Tạo mã mới
        }

        cursor.close();
        db.close();
        return maHDMoi;
    }

    public String taoMaHoaDonMoi() {
        SQLiteDatabase db = this.getReadableDatabase();
        String maHDMoi = "HD1"; // Mã mặc định nếu bảng rỗng

        // Câu lệnh SQL lấy mã HDCT lớn nhất (bản ghi cuối cùng)
        String query = "SELECT " + COT_MA_HOADON + " FROM " + BANG_HOADON +
                " ORDER BY " + COT_MA_HOADON + " DESC LIMIT 1";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String lastMaHD = cursor.getString(0); // Lấy giá trị mã HDCT cuối cùng
            int lastNumber = Integer.parseInt(lastMaHD.replace("HD", "")); // Lấy số từ mã
            maHDMoi = "HD" + (lastNumber + 1); // Tạo mã mới
        }

        cursor.close();
        db.close();
        return maHDMoi;
    }

    public String taoMaHDCTMoi() {
        SQLiteDatabase db = this.getReadableDatabase();
        String maHDCTMoi = "HDCT1"; // Mã mặc định nếu bảng rỗng

        // Câu lệnh SQL lấy mã HDCT lớn nhất (bản ghi cuối cùng)
        String query = "SELECT " + COT_MA_HDCT + " FROM " + BANG_HOADONCHITIET +
                " ORDER BY " + COT_MA_HDCT + " DESC LIMIT 1";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String lastMaHDCT = cursor.getString(0); // Lấy giá trị mã HDCT cuối cùng
            int lastNumber = Integer.parseInt(lastMaHDCT.replace("HDCT", "")); // Lấy số từ mã
            maHDCTMoi = "HDCT" + (lastNumber + 1); // Tạo mã mới
        }

        cursor.close();
        db.close();
        return maHDCTMoi;
    }

    // Lấy tổng doanh thu từ ngày X đến ngày Y
    public int layDoanhThu(String ngayBatDau, String ngayKetThuc) {
        SQLiteDatabase db = this.getReadableDatabase();
        int doanhThu = 0;

        String lenhTruyVan = "SELECT SUM(tongTien) FROM HoaDon WHERE ngayLap BETWEEN ? AND ?";
        try {
            SQLiteStatement statement = db.compileStatement(lenhTruyVan);
            statement.bindString(1, ngayBatDau);
            statement.bindString(2, ngayKetThuc);
            doanhThu = (int) statement.simpleQueryForLong();
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            db.close();
        }
        return doanhThu;
    }

    public List<TopSanPham> thongKeTopSanPham(int n) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT sp.maSanPham, sp.tenSanPham, SUM(hdct.soLuong) as tongSoLuong FROM HoaDonChiTiet hdct " +
                "JOIN SanPham sp ON hdct.maSanPham = sp.maSanPham " +
                "GROUP BY sp.maSanPham, sp.tenSanPham " +
                "ORDER BY tongSoLuong DESC " +
                "LIMIT ?";

        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(n)});
        List<TopSanPham> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            String maSanPham = cursor.getString(cursor.getColumnIndexOrThrow("maSanPham"));
            String tenSanPham = cursor.getString(cursor.getColumnIndexOrThrow("tenSanPham"));
            int tongSoLuong = cursor.getInt(cursor.getColumnIndexOrThrow("tongSoLuong"));

            TopSanPham thongKeSanPham = new TopSanPham(maSanPham, tenSanPham, tongSoLuong);
            list.add(thongKeSanPham);
        }
        cursor.close();
        db.close();
        return list;
    }

    public List<TopKhachHang> thongKeTopKhachHang(int m) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT kh.maKhachHang, kh.tenKhachHang, COUNT(hd.maHoaDon) as soLanMua, SUM(hd.tongTien) as tongChiTieu " +
                "FROM HoaDon hd " +
                "JOIN KhachHang kh ON hd.maKhachHang = kh.maKhachHang " +
                "GROUP BY kh.maKhachHang, kh.tenKhachHang " +
                "ORDER BY tongChiTieu DESC " +
                "LIMIT ?";

        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(m)});
        List<TopKhachHang> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            String maKhachHang = cursor.getString(cursor.getColumnIndexOrThrow("maKhachHang"));
            String tenKhachHang = cursor.getString(cursor.getColumnIndexOrThrow("tenKhachHang"));
            int soLanMua = cursor.getInt(cursor.getColumnIndexOrThrow("soLanMua"));
            double tongChiTieu = cursor.getDouble(cursor.getColumnIndexOrThrow("tongChiTieu"));

            TopKhachHang topKhachHang = new TopKhachHang(maKhachHang, tenKhachHang, soLanMua, tongChiTieu);
            list.add(topKhachHang);
        }
        cursor.close();
        db.close();
        return list;
    }

    public double layDonGiaSanPham(String maSanPham) {
        SQLiteDatabase db = this.getReadableDatabase();
        double donGia = 0;
        Cursor cursor = db.rawQuery("SELECT giaSanPham FROM SanPham WHERE maSanPham = ?", new String[]{maSanPham});
        if (cursor.moveToFirst()) {
            donGia = cursor.getDouble(0);
        }
        cursor.close();
        db.close();
        return donGia;
    }
    public void capNhatTongTienHoaDon(String maHoaDon, double tongTien) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE HoaDon SET tongTien = tongTien + ? WHERE maHoaDon = ?", new Object[]{tongTien, maHoaDon});
        db.close();
    }

    //xoá hoá đơn chi tiết
    public boolean xoaHDCT(String maHDCT) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(BANG_HOADONCHITIET, COT_MA_HDCT + " = ?", new String[]{maHDCT});
        db.close();
        return result > 0;
    }

    // sửa hoá đơn chi tiết
    public boolean suaHDCT(String maHDCT, String maHoaDon, String maSanPham, int soLuong, double donGia) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COT_MA_HOADON, maHoaDon);
        values.put(COT_MA_SANPHAM, maSanPham);
        values.put(COT_SO_LUONG, soLuong);
        values.put(COT_DON_GIA, donGia);

        int result = db.update(BANG_HOADONCHITIET, values, COT_MA_HDCT + " = ?", new String[]{maHDCT});
        db.close();
        return result > 0;
    }

    public NhanVien layNhanVienBangMaNV(String maNhanVien) {
        SQLiteDatabase db = this.getReadableDatabase();
        NhanVien nhanVien = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + BANG_NHANVIEN + " WHERE " + COT_MA_NHANVIEN + " = ?", new String[]{maNhanVien});
        if (cursor.moveToFirst()) {
            String tenNhanVien = cursor.getString(cursor.getColumnIndexOrThrow(COT_TEN_NHANVIEN));
            String diaChi = cursor.getString(cursor.getColumnIndexOrThrow(COT_DIA_CHI));
            int chucVu = cursor.getInt(cursor.getColumnIndexOrThrow(COT_CHUC_VU));
            double luong = cursor.getDouble(cursor.getColumnIndexOrThrow(COT_LUONG));
            String matKhau = cursor.getString(cursor.getColumnIndexOrThrow(COT_MAT_KHAU));

            nhanVien = new NhanVien(maNhanVien, tenNhanVien, diaChi, chucVu, luong, matKhau);
        }
        cursor.close();
        db.close();
        return nhanVien;
    }

    // kiểm tra chức năng đổi mật khẩu
    public boolean kiemTraMatKhauCu(String maNhanVien, String matKhauCu) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT matKhau FROM NhanVien WHERE maNhanVien = ?", new String[]{maNhanVien});

        if (cursor.moveToFirst()) {
            String matKhauHienTai = cursor.getString(0);
            cursor.close();
            db.close();
            return matKhauHienTai.equals(matKhauCu);
        }

        cursor.close();
        db.close();
        return false;
    }

    public boolean capNhatMatKhauMoi(String maNhanVien, String matKhauMoi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("matKhau", matKhauMoi); // Cập nhật cột mật khẩu mới

        int rowsAffected = db.update("NhanVien", values, "maNhanVien = ?", new String[]{maNhanVien});
        db.close();
        return rowsAffected > 0;
    }
}

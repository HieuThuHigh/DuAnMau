package com.example.sampleandroidproject.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleandroidproject.R;
import com.example.sampleandroidproject.common.Common;
import com.example.sampleandroidproject.database.DatabaseHelper;
import com.example.sampleandroidproject.model.DanhMuc;
import com.example.sampleandroidproject.model.HoaDon;
import com.example.sampleandroidproject.model.HoaDonChiTiet;
import com.example.sampleandroidproject.model.KhachHang;
import com.example.sampleandroidproject.model.NhanVien;
import com.example.sampleandroidproject.model.SanPham;

public class LoginActivity extends AppCompatActivity {
    private EditText edtUsername, edtPassword;
    private CheckBox chkRemember;
    private SharedPreferences sharedPreferences;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        chkRemember = findViewById(R.id.chkRemember);
        Button btnLogin = findViewById(R.id.btnLogin);

        db = new DatabaseHelper(this);
        SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
        boolean isInit = prefs.getBoolean("init", false);
        if (!isInit) {
            taoDuLieuNhanVien();
            taoDuLieuDanhMuc();
            taoDuLieuSanPham();
            taoDuLieuKhachHang();
            taoDuLieuHoaDon();
            taoDuLieuHoaDonChiTiet();
            prefs.edit().putBoolean("init", true).apply();
        }

       // Khi mở ứng dụng, kiểm tra và tự động điền username/password
        sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        ghiNhoThongTinNguoiDung();      //tự động lấy và điền username/password
        btnLogin.setOnClickListener(view -> checkDangNhap());
    }

    private void ghiNhoThongTinNguoiDung() {
        String savedUsername = sharedPreferences.getString("username", "");
        String savedPassword = sharedPreferences.getString("password", "");
        boolean isRemembered = sharedPreferences.getBoolean("remember", false);

        edtUsername.setText(savedUsername);
        edtPassword.setText(savedPassword);
        chkRemember.setChecked(isRemembered);
    }

    private void checkDangNhap() {
        String maNhanVien = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        NhanVien nhanVien = db.layNhanVienBangMaNV(maNhanVien);

        if (password.equals(nhanVien.getMatKhau())) {
            if (chkRemember.isChecked()) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", maNhanVien);
                editor.putString("password", password);
                editor.putBoolean("remember", true);
                editor.apply();
            } else {
                sharedPreferences.edit().clear().apply();
            }

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("CHUC_VU", nhanVien.getChucVu()); // truyền chức vụ
            startActivity(intent);
            Common.maNhanVien = maNhanVien;
            finish();
        } else {
            Toast.makeText(this, "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
        }
    }

    public void taoDuLieuSanPham() {
        DatabaseHelper db = new DatabaseHelper(this);

        SanPham sp1 = new SanPham("SP001", "Nước ngọt Calpis", 12000, 50, "Lon", "2024-02-08", "DM01");
        db.themSanPham(sp1);

        SanPham sp2 = new SanPham("SP002", "Trà xanh Ito En", 10000, 40, "Lon", "2024-02-08", "DM01");
        db.themSanPham(sp2);

        SanPham sp3 = new SanPham("SP003", "Bánh Pocky", 25000, 30, "Hộp", "2024-02-07", "DM02");
        db.themSanPham(sp3);

        SanPham sp4 = new SanPham("SP004", "Sữa Meiji", 15000, 20, "Hộp", "2024-02-06", "DM03");
        db.themSanPham(sp4);

        SanPham sp5 = new SanPham("SP005", "Mì Udon", 5000, 100, "Gói", "2024-02-05", "DM04");
        db.themSanPham(sp5);

        SanPham sp6 = new SanPham("SP006", "Nước khoáng Suntory", 8000, 60, "Chai", "2024-02-04", "DM01");
        db.themSanPham(sp6);

        SanPham sp7 = new SanPham("SP007", "Snack Jagabee", 12000, 45, "Gói", "2024-02-08", "DM05");
        db.themSanPham(sp7);

        SanPham sp8 = new SanPham("SP008", "Trà gạo lứt Genmaicha", 30000, 25, "Hộp", "2024-02-08", "DM06");
        db.themSanPham(sp8);

        SanPham sp9 = new SanPham("SP009", "Dầu ăn Ajinomoto", 45000, 15, "Chai", "2024-02-03", "DM07");
        db.themSanPham(sp9);

        SanPham sp10 = new SanPham("SP010", "Đường nâu Okinawa", 20000, 50, "Bao", "2024-02-02", "DM08");
        db.themSanPham(sp10);

        SanPham sp11 = new SanPham("SP011", "Nước tương Kikkoman", 15000, 40, "Chai", "2024-02-09", "DM09");
        db.themSanPham(sp11);

        SanPham sp12 = new SanPham("SP012", "Bánh mochi Nhật Bản", 32000, 35, "Hộp", "2024-02-10", "DM02");
        db.themSanPham(sp12);

        SanPham sp13 = new SanPham("SP013", "Bia Asahi", 45000, 25, "Lon", "2024-02-11", "DM10");
        db.themSanPham(sp13);

        SanPham sp14 = new SanPham("SP014", "Dầu gội Tsubaki", 90000, 20, "Chai", "2024-02-12", "DM11");
        db.themSanPham(sp14);

        SanPham sp15 = new SanPham("SP015", "Sữa tươi Morinaga", 12000, 30, "Hộp", "2024-02-13", "DM03");
        db.themSanPham(sp15);

        SanPham sp16 = new SanPham("SP016", "Mì Ramen", 4000, 150, "Gói", "2024-02-14", "DM04");
        db.themSanPham(sp16);

        SanPham sp17 = new SanPham("SP017", "Bột giặt Attack", 125000, 20, "Bao", "2024-02-15", "DM12");
        db.themSanPham(sp17);

        SanPham sp18 = new SanPham("SP018", "Dưa lưới Nhật", 18000, 50, "Kg", "2024-02-16", "DM13");
        db.themSanPham(sp18);

        SanPham sp19 = new SanPham("SP019", "Táo Aomori", 50000, 40, "Kg", "2024-02-17", "DM13");
        db.themSanPham(sp19);

        SanPham sp20 = new SanPham("SP020", "Rau cải Mizuna", 25000, 60, "Kg", "2024-02-18", "DM14");
        db.themSanPham(sp20);

    }

    public void taoDuLieuKhachHang() {
        DatabaseHelper db = new DatabaseHelper(this);

        db.themKhachHang(new KhachHang("KH001", "Nặc danh", "", "0000", ""));
        db.themKhachHang(new KhachHang("KH002", "Trần Thị Bích", "456 Đường XYZ, Hà Nội", "0988777666", "tranthibich@example.com"));
        db.themKhachHang(new KhachHang("KH003", "Lê Hoàng Nam", "789 Đường DEF, Đà Nẵng", "0912333444", "lehoangnam@example.com"));
        db.themKhachHang(new KhachHang("KH004", "Phạm Minh Khang", "101 Đường GHI, Cần Thơ", "0922112233", "phamminhkhang@example.com"));
        db.themKhachHang(new KhachHang("KH005", "Võ Hồng Phúc", "202 Đường JKL, Hải Phòng", "0933445566", "vohongphuc@example.com"));
        db.themKhachHang(new KhachHang("KH006", "Bùi Thị Lan", "303 Đường MNO, Nha Trang", "0944556677", "buithilan@example.com"));
        db.themKhachHang(new KhachHang("KH007", "Đặng Quốc Duy", "404 Đường PQR, Huế", "0955667788", "dangquocduy@example.com"));
        db.themKhachHang(new KhachHang("KH008", "Ngô Văn Hùng", "505 Đường STU, Vũng Tàu", "0966778899", "ngovanhung@example.com"));
        db.themKhachHang(new KhachHang("KH009", "Hoàng Thị Hạnh", "606 Đường VWX, Biên Hòa", "0977889900", "hoangthihanh@example.com"));
        db.themKhachHang(new KhachHang("KH010", "Trương Minh Tú", "707 Đường YZA, Bình Dương", "0988990011", "truongminhtu@example.com"));
        db.themKhachHang(new KhachHang("KH011", "Đoàn Văn Thành", "808 Đường BCD, Long An", "0999001122", "doanvanthanh@example.com"));
        db.themKhachHang(new KhachHang("KH012", "Mai Thị Ngọc", "909 Đường CDE, Tây Ninh", "0910012233", "maithingoc@example.com"));
        db.themKhachHang(new KhachHang("KH013", "Phan Văn Mạnh", "111 Đường EFG, Vĩnh Long", "0921123344", "phanvanmanh@example.com"));
        db.themKhachHang(new KhachHang("KH014", "Trần Hoàng Lộc", "222 Đường GHI, Sóc Trăng", "0932234455", "tranhoangloc@example.com"));
        db.themKhachHang(new KhachHang("KH015", "Nguyễn Thị Thanh", "333 Đường IJK, Bạc Liêu", "0943345566", "nguyenthithanh@example.com"));
        db.themKhachHang(new KhachHang("KH016", "Lý Minh Đức", "444 Đường KLM, Cà Mau", "0954456677", "lyminhduc@example.com"));
        db.themKhachHang(new KhachHang("KH017", "Vương Hồng Sơn", "555 Đường MNO, Đắk Lắk", "0965567788", "vuonghongson@example.com"));
        db.themKhachHang(new KhachHang("KH018", "Hà Thị Nhung", "666 Đường OPQ, Gia Lai", "0976678899", "hathinhung@example.com"));
        db.themKhachHang(new KhachHang("KH019", "Trịnh Văn Dũng", "777 Đường QRS, Kon Tum", "0987789900", "trinhvandung@example.com"));
        db.themKhachHang(new KhachHang("KH020", "Tống Hoàng Anh", "888 Đường STU, Phú Yên", "0998890011", "tonghoanganh@example.com"));
    }

    public void taoDuLieuDanhMuc() {
        DatabaseHelper db = new DatabaseHelper(this);

        db.themDanhMuc(new DanhMuc("DM001", "Đồ uống"));
        db.themDanhMuc(new DanhMuc("DM002", "Bánh kẹo"));
        db.themDanhMuc(new DanhMuc("DM003", "Sữa và chế phẩm từ sữa"));
        db.themDanhMuc(new DanhMuc("DM004", "Mì - Cháo - Phở"));
        db.themDanhMuc(new DanhMuc("DM005", "Thực phẩm khô"));
        db.themDanhMuc(new DanhMuc("DM006", "Gia vị"));
        db.themDanhMuc(new DanhMuc("DM007", "Đồ hộp"));
        db.themDanhMuc(new DanhMuc("DM008", "Rau củ quả"));
        db.themDanhMuc(new DanhMuc("DM009", "Thịt - Hải sản"));
        db.themDanhMuc(new DanhMuc("DM010", "Sản phẩm đông lạnh"));
        db.themDanhMuc(new DanhMuc("DM011", "Đồ ăn nhanh"));
        db.themDanhMuc(new DanhMuc("DM012", "Thực phẩm chức năng"));
        db.themDanhMuc(new DanhMuc("DM013", "Hóa mỹ phẩm"));
        db.themDanhMuc(new DanhMuc("DM014", "Đồ dùng gia đình"));
        db.themDanhMuc(new DanhMuc("DM015", "Sản phẩm cho bé"));
        db.themDanhMuc(new DanhMuc("DM016", "Đồ dùng văn phòng"));
        db.themDanhMuc(new DanhMuc("DM017", "Thiết bị điện tử"));
        db.themDanhMuc(new DanhMuc("DM018", "Sản phẩm thời trang"));
        db.themDanhMuc(new DanhMuc("DM019", "Đồ thể thao"));
        db.themDanhMuc(new DanhMuc("DM020", "Sách - Văn hóa phẩm"));
    }

    public void taoDuLieuNhanVien() {
        DatabaseHelper db = new DatabaseHelper(this);

        db.themNhanVien(new NhanVien("NV001", "Nguyễn Văn An", "12 Lý Tự Trọng, Q1, TP.HCM", 1, 25000000, "admin123"));
        db.themNhanVien(new NhanVien("NV002", "Trần Thị Cúc", "34 Lê Lợi, Q3, TP.HCM", 0, 12000000, "sales456"));
        db.themNhanVien(new NhanVien("NV003", "Lê Minh Hùng", "56 Trần Hưng Đạo, Hà Nội", 0, 20000000, "accounting789"));
        db.themNhanVien(new NhanVien("NV004", "Phạm Đức Thịnh", "78 Nguyễn Du, Đà Nẵng", 0, 9000000, "warehouse101"));
        db.themNhanVien(new NhanVien("NV005", "Võ Hoàng Yến", "90 Pasteur, Hải Phòng", 0, 11000000, "marketing202"));
        db.themNhanVien(new NhanVien("NV006", "Bùi Văn Hòa", "21 Phan Đình Phùng, Nha Trang", 0, 18000000, "hr303"));
        db.themNhanVien(new NhanVien("NV007", "Đặng Ngọc Hiếu", "43 Hoàng Hoa Thám, Huế", 0, 7000000, "security404"));
        db.themNhanVien(new NhanVien("NV008", "Ngô Thanh Bình", "65 Cách Mạng Tháng 8, Vũng Tàu", 0, 15000000, "it505"));
        db.themNhanVien(new NhanVien("NV009", "Hoàng Thị Dung", "87 Hai Bà Trưng, Biên Hòa", 0, 17000000, "assistant606"));
        db.themNhanVien(new NhanVien("NV010", "Trương Đình Khoa", "109 Nguyễn Văn Trỗi, Bình Dương", 0, 8000000, "delivery707"));
        db.themNhanVien(new NhanVien("NV011", "Đoàn Thanh Tùng", "121 Điện Biên Phủ, Long An", 0, 10000000, "cashier808"));
        db.themNhanVien(new NhanVien("NV012", "Mai Thị Lan", "143 Võ Văn Kiệt, Tây Ninh", 0, 9500000, "support909"));
        db.themNhanVien(new NhanVien("NV013", "Phan Ngọc Quỳnh", "165 Tôn Đức Thắng, Vĩnh Long", 0, 11000000, "consultant111"));
        db.themNhanVien(new NhanVien("NV014", "Trần Hoàng Nam", "187 Nguyễn Tri Phương, Sóc Trăng", 0, 22000000, "sales_manager222"));
        db.themNhanVien(new NhanVien("NV015", "Nguyễn Thị Bích", "209 Trần Quang Khải, Bạc Liêu", 0, 24000000, "finance333"));
        db.themNhanVien(new NhanVien("NV016", "Lý Mạnh Phát", "231 Nguyễn Đình Chiểu, Cà Mau", 0, 10500000, "inventory444"));
        db.themNhanVien(new NhanVien("NV017", "Vương Hữu Đạt", "253 Võ Thị Sáu, Đắk Lắk", 0, 9800000, "maintenance555"));
        db.themNhanVien(new NhanVien("NV018", "Hà Thanh Hải", "275 Bạch Đằng, Gia Lai", 0, 13000000, "designer666"));
        db.themNhanVien(new NhanVien("NV019", "Trịnh Minh Lộc", "297 Đống Đa, Kon Tum", 0, 12000000, "market777"));
        db.themNhanVien(new NhanVien("NV020", "Tống Hoàng Tú", "319 Hùng Vương, Phú Yên", 0, 16000000, "developer888"));
    }

    public void taoDuLieuHoaDon() {

// Thêm một số hóa đơn mẫu
        DatabaseHelper db = new DatabaseHelper(this);

// Thêm một số hóa đơn mẫu
// Tháng 1
        db.themHoaDon(new HoaDon("HD001", "NV001", "KH001", "2024-01-05", 180000));
        db.themHoaDon(new HoaDon("HD002", "NV002", "KH002", "2024-01-15", 220000));
// Tháng 2
        db.themHoaDon(new HoaDon("HD003", "NV003", "KH003", "2024-02-03", 300000));
        db.themHoaDon(new HoaDon("HD004", "NV001", "KH004", "2024-02-05", 250000));
// Tháng 3
        db.themHoaDon(new HoaDon("HD005", "NV002", "KH005", "2024-03-08", 180000));
        db.themHoaDon(new HoaDon("HD006", "NV003", "KH006", "2024-03-12", 270000));
// Tháng 4
        db.themHoaDon(new HoaDon("HD007", "NV004", "KH007", "2024-04-11", 275000));
        db.themHoaDon(new HoaDon("HD008", "NV005", "KH008", "2024-04-18", 195000));
// Tháng 5
        db.themHoaDon(new HoaDon("HD009", "NV006", "KH009", "2024-05-05", 320000));
        db.themHoaDon(new HoaDon("HD010", "NV007", "KH010", "2024-05-21", 290000));
// Tháng 6
        db.themHoaDon(new HoaDon("HD011", "NV008", "KH011", "2024-06-02", 310000));
        db.themHoaDon(new HoaDon("HD012", "NV009", "KH012", "2024-06-25", 350000));
// Tháng 7
        db.themHoaDon(new HoaDon("HD013", "NV010", "KH013", "2024-07-17", 400000));
        db.themHoaDon(new HoaDon("HD014", "NV011", "KH014", "2024-07-22", 375000));
// Tháng 8
        db.themHoaDon(new HoaDon("HD015", "NV012", "KH015", "2024-08-09", 280000));
        db.themHoaDon(new HoaDon("HD016", "NV001", "KH016", "2024-08-30", 390000));
// Tháng 9
        db.themHoaDon(new HoaDon("HD017", "NV002", "KH017", "2024-09-11", 410000));
        db.themHoaDon(new HoaDon("HD018", "NV003", "KH018", "2024-09-27", 225000));
// Tháng 10
        db.themHoaDon(new HoaDon("HD019", "NV004", "KH019", "2024-10-15", 450000));
        db.themHoaDon(new HoaDon("HD020", "NV005", "KH020", "2024-10-31", 395000));
// Tháng 11
        db.themHoaDon(new HoaDon("HD021", "NV006", "KH011", "2024-11-10", 460000));
        db.themHoaDon(new HoaDon("HD022", "NV007", "KH012", "2024-11-25", 310000));
// Tháng 12
        db.themHoaDon(new HoaDon("HD023", "NV008", "KH013", "2024-12-05", 500000));
        db.themHoaDon(new HoaDon("HD024", "NV009", "KH014", "2024-12-20", 420000));
// Thêm hóa đơn cho năm 2025
// Tháng 1 - 12 của năm 2025
        db.themHoaDon(new HoaDon("HD025", "NV001", "KH001", "2025-01-10", 185000));
        db.themHoaDon(new HoaDon("HD026", "NV002", "KH002", "2025-01-22", 225000));
        db.themHoaDon(new HoaDon("HD027", "NV003", "KH003", "2025-02-07", 305000));
        db.themHoaDon(new HoaDon("HD028", "NV004", "KH004", "2025-02-20", 260000));
        db.themHoaDon(new HoaDon("HD029", "NV005", "KH005", "2025-03-15", 190000));
        db.themHoaDon(new HoaDon("HD030", "NV006", "KH006", "2025-03-28", 275000));
        db.themHoaDon(new HoaDon("HD031", "NV007", "KH007", "2025-04-09", 280000));
        db.themHoaDon(new HoaDon("HD032", "NV008", "KH008", "2025-04-25", 200000));
        db.themHoaDon(new HoaDon("HD033", "NV009", "KH009", "2025-05-10", 330000));
        db.themHoaDon(new HoaDon("HD034", "NV010", "KH010", "2025-05-29", 295000));
        db.themHoaDon(new HoaDon("HD035", "NV011", "KH011", "2025-06-14", 315000));
        db.themHoaDon(new HoaDon("HD036", "NV012", "KH012", "2025-06-30", 355000));
        db.themHoaDon(new HoaDon("HD037", "NV001", "KH013", "2025-07-19", 410000));
        db.themHoaDon(new HoaDon("HD038", "NV002", "KH014", "2025-07-27", 380000));
        db.themHoaDon(new HoaDon("HD039", "NV003", "KH015", "2025-08-12", 290000));
        db.themHoaDon(new HoaDon("HD040", "NV004", "KH016", "2025-08-31", 400000));
        db.themHoaDon(new HoaDon("HD041", "NV005", "KH017", "2025-09-14", 420000));
        db.themHoaDon(new HoaDon("HD042", "NV006", "KH018", "2025-09-29", 230000));
        db.themHoaDon(new HoaDon("HD043", "NV007", "KH019", "2025-10-18", 460000));
        db.themHoaDon(new HoaDon("HD044", "NV008", "KH020", "2025-10-30", 400000));
        db.themHoaDon(new HoaDon("HD045", "NV009", "KH001", "2025-11-12", 470000));
        db.themHoaDon(new HoaDon("HD046", "NV010", "KH020", "2025-11-28", 320000));
        db.themHoaDon(new HoaDon("HD047", "NV011", "KH020", "2025-12-07", 510000));
        db.themHoaDon(new HoaDon("HD048", "NV012", "KH012", "2025-12-22", 430000));
    }

    public void taoDuLieuHoaDonChiTiet() {
        DatabaseHelper db = new DatabaseHelper(this);
        int soLuongRecords = 150;
        int hdIndex = 1;

        for (int i = 1; i <= soLuongRecords; ) {
            String maHoaDon = "HD" + String.format("%03d", hdIndex);
            int soSanPham = (int) (Math.random() * 6) + 5; // Mỗi hóa đơn mua từ 5 đến 10 sản phẩm
            double tongTien = 0;

            for (int j = 0; j < soSanPham && i <= soLuongRecords; j++, i++) {
                String maHDCT = "HDCT" + String.format("%03d", i);
                String maSanPham = "SP" + String.format("%03d", ((i - 1) % 20) + 1);
                int soLuong = (int) (Math.random() * 10) + 1; // Số lượng ngẫu nhiên từ 1-10
                double donGia = db.layDonGiaSanPham(maSanPham); // Lấy đơn giá từ bảng sản phẩm

                db.themHDCT(new HoaDonChiTiet(maHDCT, maHoaDon, maSanPham, soLuong, donGia));
                tongTien += soLuong * donGia;
            }

            // Cập nhật tổng tiền trong hóa đơn
            db.capNhatTongTienHoaDon(maHoaDon, tongTien);

            hdIndex++;
            if (hdIndex > 48) {
                hdIndex = 1;
            }
        }
    }

}

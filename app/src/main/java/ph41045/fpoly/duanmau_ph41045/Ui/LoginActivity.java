package ph41045.fpoly.duanmau_ph41045.Ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ph41045.fpoly.duanmau_ph41045.Common.Common;
import ph41045.fpoly.duanmau_ph41045.Database.DatabaseHelper;
import ph41045.fpoly.duanmau_ph41045.Model.DanhMuc;
import ph41045.fpoly.duanmau_ph41045.Model.HoaDon;
import ph41045.fpoly.duanmau_ph41045.Model.HoaDonChiTiet;
import ph41045.fpoly.duanmau_ph41045.Model.KhachHang;
import ph41045.fpoly.duanmau_ph41045.Model.NhanVien;
import ph41045.fpoly.duanmau_ph41045.Model.SanPham;
import ph41045.fpoly.duanmau_ph41045.R;

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

        if (maNhanVien.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        NhanVien nhanVien = db.layNhanVienBangMaNV(maNhanVien);

        if (nhanVien == null) {
            Toast.makeText(this, "Tài khoản không tồn tại!", Toast.LENGTH_SHORT).show();
            return;   // <-- Quan trọng, tránh gọi getMatKhau()
        }

        if (!password.equals(nhanVien.getMatKhau())) {
            Toast.makeText(this, "Sai mật khẩu!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Lưu tài khoản nếu tick Remember
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
        intent.putExtra("CHUC_VU", nhanVien.getChucVu());
        startActivity(intent);
        Common.maNhanVien = maNhanVien;
        finish();
    }


    public void taoDuLieuSanPham() {
        DatabaseHelper db = new DatabaseHelper(this);

        db.themSanPham(new SanPham("SP001", "Nước ép Táo", 20000, 50, "Chai", "2025-10-01", "DM001"));
        db.themSanPham(new SanPham("SP002", "Trà Xanh Matcha", 25000, 30, "Ly", "2025-10-02", "DM001"));
        db.themSanPham(new SanPham("SP003", "Bánh Mochi Vani", 30000, 20, "Hộp", "2025-10-03", "DM002"));
        db.themSanPham(new SanPham("SP004", "Sữa Chua Hy Lạp", 18000, 40, "Chai", "2025-10-04", "DM003"));
        db.themSanPham(new SanPham("SP005", "Mì Udon Nhật", 12000, 70, "Gói", "2025-10-05", "DM004"));
        db.themSanPham(new SanPham("SP006", "Nước suối Sapporo", 8000, 60, "Chai", "2025-10-06", "DM001"));
        db.themSanPham(new SanPham("SP007", "Snack Khoai Tây", 15000, 25, "Gói", "2025-10-07", "DM005"));
        db.themSanPham(new SanPham("SP008", "Trà Lúa Mạch Vị Mật Ong", 27000, 15, "Hộp", "2025-10-08", "DM006"));
        db.themSanPham(new SanPham("SP009", "Dầu mè Hảo Hạng", 52000, 18, "Chai", "2025-10-09", "DM007"));
        db.themSanPham(new SanPham("SP010", "Muối Biển Premium", 19000, 35, "Bao", "2025-10-10", "DM008"));

        db.themSanPham(new SanPham("SP011", "Nước Tương Nhật", 22000, 25, "Chai", "2025-11-01", "DM009"));
        db.themSanPham(new SanPham("SP012", "Bánh Dorayaki Socola", 35000, 15, "Hộp", "2025-11-02", "DM002"));
        db.themSanPham(new SanPham("SP013", "Bia Kirin", 47000, 30, "Lon", "2025-11-03", "DM010"));
        db.themSanPham(new SanPham("SP014", "Dầu gội Nhật Bản", 98000, 20, "Chai", "2025-11-04", "DM011"));
        db.themSanPham(new SanPham("SP015", "Sữa Tươi Organic", 18000, 28, "Hộp", "2025-11-05", "DM003"));
        db.themSanPham(new SanPham("SP016", "Mì Ramen Chay", 13000, 80, "Gói", "2025-11-06", "DM004"));
        db.themSanPham(new SanPham("SP017", "Bột giặt Nhật", 140000, 18, "Bao", "2025-11-07", "DM012"));
        db.themSanPham(new SanPham("SP018", "Nho Đỏ Cao Cấp", 46000, 35, "Kg", "2025-11-08", "DM013"));
        db.themSanPham(new SanPham("SP019", "Lê Mỹ Ngon", 56000, 30, "Kg", "2025-11-09", "DM013"));
        db.themSanPham(new SanPham("SP020", "Rau Cải Bó Xôi", 23000, 45, "Kg", "2025-11-10", "DM014"));
    }

    public void taoDuLieuKhachHang() {
        DatabaseHelper db = new DatabaseHelper(this);

        db.themKhachHang(new KhachHang("KH001", "Khách lẻ A", "", "0000", ""));
        db.themKhachHang(new KhachHang("KH002", "Nguyễn Minh Anh", "12 Lý Thường Kiệt, Hà Nội", "0988123456", "minhanh@example.com"));
        db.themKhachHang(new KhachHang("KH003", "Trần Thu Hương", "45 CMT8, TP.HCM", "0912345678", "thuhuong@example.com"));
        db.themKhachHang(new KhachHang("KH004", "Phạm Văn Long", "33 Phan Đình Phùng, Huế", "0933224455", "vanlong@example.com"));
        db.themKhachHang(new KhachHang("KH005", "Võ Hoàng Nam", "55 Lê Lợi, Đà Nẵng", "0977554433", "hoangnam@example.com"));
        db.themKhachHang(new KhachHang("KH006", "Bùi Minh Tâm", "200 Nguyễn Thị Minh Khai, HCM", "0998877665", "minhtam@example.com"));
        db.themKhachHang(new KhachHang("KH007", "Đoàn Thế Dũng", "88 Trần Bình Trọng, Hải Phòng", "0944551122", "thedung@example.com"));
        db.themKhachHang(new KhachHang("KH008", "Ngô Văn Hậu", "55 Lê Lai, Vũng Tàu", "0922337788", "vanhau@example.com"));
        db.themKhachHang(new KhachHang("KH009", "Hoàng Thị Lan", "33 Nguyễn Huệ, Huế", "0933445566", "thilan@example.com"));
        db.themKhachHang(new KhachHang("KH010", "Trương Hữu Tâm", "145 Điện Biên Phủ, Đà Nẵng", "0966778899", "huutam@example.com"));

        db.themKhachHang(new KhachHang("KH011", "Đinh Hải Nam", "12 Quang Trung, Hà Nội", "0909123123", "hainam@example.com"));
        db.themKhachHang(new KhachHang("KH012", "Phan Nhật Linh", "34 Nguyễn Công Trứ, TP.HCM", "0977332211", "nhatlinh@example.com"));
        db.themKhachHang(new KhachHang("KH013", "Lê Tấn Duy", "89 Trần Quang Khải, Cần Thơ", "0911223344", "tanduy@example.com"));
        db.themKhachHang(new KhachHang("KH014", "Trần Khánh Minh", "66 Nguyễn Du, Hải Phòng", "0933556677", "khanhminh@example.com"));
        db.themKhachHang(new KhachHang("KH015", "Nguyễn Hoàng Bình", "199 Trường Chinh, Hà Nội", "0988223344", "hoangbinh@example.com"));
        db.themKhachHang(new KhachHang("KH016", "Lý Minh Quân", "77 Phạm Văn Đồng, HCM", "0966223344", "minhquan@example.com"));
        db.themKhachHang(new KhachHang("KH017", "Vương Khánh Huy", "11 Nguyễn Văn Trỗi, Đà Nẵng", "0944556677", "khanhhuy@example.com"));
        db.themKhachHang(new KhachHang("KH018", "Hà Gia Phúc", "22 Lê Văn Sỹ, Huế", "0923445566", "giaphuc@example.com"));
        db.themKhachHang(new KhachHang("KH019", "Trịnh Hoàng Nam", "14 Hai Bà Trưng, Đà Nẵng", "0933667788", "hoangnam@example.com"));
        db.themKhachHang(new KhachHang("KH020", "Tống Gia Bảo", "88 Nguyễn Thượng Hiền, TP.HCM", "0977889900", "giabao@example.com"));
    }



    public void taoDuLieuDanhMuc() {
        DatabaseHelper db = new DatabaseHelper(this);

        db.themDanhMuc(new DanhMuc("DM001", "Nước uống"));
        db.themDanhMuc(new DanhMuc("DM002", "Bánh kẹo"));
        db.themDanhMuc(new DanhMuc("DM003", "Sữa & chế phẩm"));
        db.themDanhMuc(new DanhMuc("DM004", "Mì & Ramen"));
        db.themDanhMuc(new DanhMuc("DM005", "Snack & Ăn vặt"));
        db.themDanhMuc(new DanhMuc("DM006", "Trà & Cà phê"));
        db.themDanhMuc(new DanhMuc("DM007", "Gia vị"));
        db.themDanhMuc(new DanhMuc("DM008", "Đường & Muối"));
        db.themDanhMuc(new DanhMuc("DM009", "Nước tương & Nước mắm"));
        db.themDanhMuc(new DanhMuc("DM010", "Đồ uống có cồn"));
        db.themDanhMuc(new DanhMuc("DM011", "Chăm sóc cá nhân"));
        db.themDanhMuc(new DanhMuc("DM012", "Chăm sóc nhà cửa"));
        db.themDanhMuc(new DanhMuc("DM013", "Trái cây"));
        db.themDanhMuc(new DanhMuc("DM014", "Rau củ"));
        db.themDanhMuc(new DanhMuc("DM015", "Đồ dùng gia đình"));
        db.themDanhMuc(new DanhMuc("DM016", "Văn phòng phẩm"));
        db.themDanhMuc(new DanhMuc("DM017", "Thiết bị điện tử"));
        db.themDanhMuc(new DanhMuc("DM018", "Quần áo & Thời trang"));
        db.themDanhMuc(new DanhMuc("DM019", "Đồ thể thao"));
        db.themDanhMuc(new DanhMuc("DM020", "Sách & Văn hóa phẩm"));
    }




    public void taoDuLieuNhanVien() {
        DatabaseHelper db = new DatabaseHelper(this);

        db.themNhanVien(new NhanVien("NV001", "Ngô Minh Tùng", "12 Phan Bội Châu, Hà Nội", 1, 25000000, "admin01"));
        db.themNhanVien(new NhanVien("NV002", "Trần Hải Linh", "45 Lê Lợi, Đà Nẵng", 0, 12000000, "sale01"));
        db.themNhanVien(new NhanVien("NV003", "Lê Đức Duy", "78 Hai Bà Trưng, TP.HCM", 0, 19500000, "acc01"));
        db.themNhanVien(new NhanVien("NV004", "Phạm Hoài Nam", "33 Trần Đại Nghĩa, Huế", 0, 9000000, "store01"));
        db.themNhanVien(new NhanVien("NV005", "Võ Thanh Tâm", "92 Nguyễn Huệ, Hà Nội", 0, 11000000, "mk01"));
        db.themNhanVien(new NhanVien("NV006", "Bùi Nhật Hậu", "11 Phan Chu Trinh, Hải Phòng", 0, 17000000, "hr01"));
        db.themNhanVien(new NhanVien("NV007", "Đặng Duy Minh", "59 Nguyễn Đình Chiểu, Huế", 0, 8000000, "sec01"));
        db.themNhanVien(new NhanVien("NV008", "Ngô Minh Hải", "66 CMT8, TP.HCM", 0, 15500000, "it01"));
        db.themNhanVien(new NhanVien("NV009", "Hoàng Thị Lan", "91 Lê Văn Sỹ, Đà Nẵng", 0, 16000000, "assist01"));
        db.themNhanVien(new NhanVien("NV010", "Trương Hữu Lộc", "10 Điện Biên Phủ, Đà Lạt", 0, 9500000, "ship01"));

        db.themNhanVien(new NhanVien("NV011", "Đoàn Quang Vinh", "11 Hùng Vương, Huế", 0, 10500000, "cash01"));
        db.themNhanVien(new NhanVien("NV012", "Mai Châu Uyên", "55 Hoàng Diệu, Hà Nội", 0, 9800000, "support01"));
        db.themNhanVien(new NhanVien("NV013", "Phan Bảo Long", "200 Quang Trung, TP.HCM", 0, 12200000, "tv01"));
        db.themNhanVien(new NhanVien("NV014", "Trần Nhật Minh", "70 Trần Hưng Đạo, Đà Nẵng", 0, 21000000, "sales01"));
        db.themNhanVien(new NhanVien("NV015", "Nguyễn Hoài Lan", "93 Nguyễn Văn Trỗi, Bình Dương", 0, 25000000, "finance01"));
        db.themNhanVien(new NhanVien("NV016", "Lý Đại Phú", "22 Võ Thị Sáu, Hải Phòng", 0, 10000000, "inv01"));
        db.themNhanVien(new NhanVien("NV017", "Vương Thành Đạt", "39 Bạch Đằng, Nha Trang", 0, 9200000, "repair01"));
        db.themNhanVien(new NhanVien("NV018", "Hà Quốc Việt", "77 Lê Lai, TP.HCM", 0, 13500000, "design01"));
        db.themNhanVien(new NhanVien("NV019", "Trịnh Minh Huy", "188 Cách Mạng Tháng 8, Hà Nội", 0, 12100000, "ads01"));
        db.themNhanVien(new NhanVien("NV020", "Tống Thiên Phúc", "56 Lê Quý Đôn, Đà Nẵng", 0, 16800000, "dev01"));
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

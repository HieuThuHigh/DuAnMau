package ph41045.fpoly.duanmau_ph41045.Ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import ph41045.fpoly.duanmau_ph41045.Adapter.GioHangAdapter;
import ph41045.fpoly.duanmau_ph41045.Common.Common;
import ph41045.fpoly.duanmau_ph41045.Common.GioHangItem;
import ph41045.fpoly.duanmau_ph41045.Database.DatabaseHelper;

import ph41045.fpoly.duanmau_ph41045.Model.HoaDon;
import ph41045.fpoly.duanmau_ph41045.Model.HoaDonChiTiet;
import ph41045.fpoly.duanmau_ph41045.Model.KhachHang;
import ph41045.fpoly.duanmau_ph41045.Model.SanPham;
import ph41045.fpoly.duanmau_ph41045.R;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class GioHangActivity extends AppCompatActivity implements GioHangAdapter.OnSanPhamClickListener{
    private GioHangAdapter gioHangAdapter;
    private DatabaseHelper db;
    TextView tvTongTien;
    NumberFormat currencyFormat;
    Button btnThanhToan;
    Spinner spKhachHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        db = new DatabaseHelper(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView lvSanPham = findViewById(R.id.lvSanPham);
        tvTongTien = findViewById(R.id.tvTongTien);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        spKhachHang = findViewById(R.id.spKhachHang);

        List<KhachHang> khacHangList = db.layTatCaKhachHang();
        ArrayAdapter<KhachHang> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, khacHangList);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spKhachHang.setAdapter(adapter);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Giỏ hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(this);

        gioHangAdapter = new GioHangAdapter(this, Common.getGioHang().getDanhSachGioHang());
        gioHangAdapter.setOnSanPhamClickListener(this);
        lvSanPham.setAdapter(gioHangAdapter);

        // Định dạng tiền tệ cho Việt Nam (VND)
        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        updateTongTien();

        btnThanhToan.setOnClickListener(v -> {
            String maHoaDon = db.taoMaHoaDonMoi();
            String maKhachHang = ((KhachHang)spKhachHang.getSelectedItem()).getMaKhachHang();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            int tongTien = 0;
            for (GioHangItem item : Common.getGioHang().getDanhSachGioHang()) {
                tongTien += item.getSanPham().getGiaSanPham() * item.getSoLuong();
            }
            db.themHoaDon(new HoaDon(maHoaDon, Common.maNhanVien, maKhachHang, dateFormat.format(new Date()), tongTien));

            for (GioHangItem item : Common.getGioHang().getDanhSachGioHang()) {
                String maHDCT = db.taoMaHDCTMoi();
                SanPham sanPham = item.getSanPham();
                db.themHDCT(new HoaDonChiTiet(maHDCT, maHoaDon, sanPham.getMaSanPham(), item.getSoLuong(), sanPham.getGiaSanPham()));
            }
            Toast.makeText(this, "Thanh toán thành công", Toast.LENGTH_LONG).show();
            Common.getGioHang().getDanhSachGioHang().clear();
            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void updateTongTien() {
        int tongTien = 0;
        for (GioHangItem item : Common.getGioHang().getDanhSachGioHang()) {
            tongTien += item.getSanPham().getGiaSanPham() * item.getSoLuong();
        }
        tvTongTien.setText("Tổng tiền: " + currencyFormat.format(tongTien));
    }

    @Override
    public void onDeleteSanPham(GioHangItem gioHangItem) {
        Common.getGioHang().getDanhSachGioHang().remove(gioHangItem);
        gioHangAdapter.notifyDataSetChanged();
        updateTongTien();
    }

    @Override
    public void onIncrease(GioHangItem gioHangItem) {
        gioHangItem.setSoLuong(gioHangItem.getSoLuong() + 1);
        gioHangAdapter.notifyDataSetChanged();
        updateTongTien();
    }

    @Override
    public void onDecrease(GioHangItem gioHangItem) {
        if (gioHangItem.getSoLuong() > 1) {
            gioHangItem.setSoLuong(gioHangItem.getSoLuong() - 1);
            gioHangAdapter.notifyDataSetChanged();
            updateTongTien();
        } else {
            Toast.makeText(this, "Số lượng đang là 1",
                    Toast.LENGTH_LONG).show();
        }
    }
}


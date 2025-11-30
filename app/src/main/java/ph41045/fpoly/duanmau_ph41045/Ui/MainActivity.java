package ph41045.fpoly.duanmau_ph41045;

import static android.view.View.GONE;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int chucVu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setPopupTheme(R.style.DarkPopupMenu);

        findViewById(R.id.lnDoanhThu).setOnClickListener(this);
        findViewById(R.id.lnKhachHang).setOnClickListener(this);
        findViewById(R.id.lnTopKhachHang).setOnClickListener(this);
        findViewById(R.id.lnDanhMuc).setOnClickListener(this);
        findViewById(R.id.lnSanPham).setOnClickListener(this);
        findViewById(R.id.lnNhanVien).setOnClickListener(this);
        findViewById(R.id.lnHoaDon).setOnClickListener(this);
        findViewById(R.id.lnTopSanPham).setOnClickListener(this);
        findViewById(R.id.lnDoiMatKhau).setOnClickListener(this);
        findViewById(R.id.lnDangXuat).setOnClickListener(this);

        // Nhận dữ liệu từ Intent
        chucVu = getIntent().getIntExtra("CHUC_VU", 0);
        if (chucVu == 0) {
            findViewById(R.id.lnThongKe).setVisibility(GONE);
            findViewById(R.id.lnNhanVien).setVisibility(GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        if (chucVu == 0) {
            MenuItem doanhThuItem = menu.findItem(R.id.menu_doanh_thu);
            if (doanhThuItem != null) {
                doanhThuItem.setVisible(false);
            }

            MenuItem topSanPhamItem = menu.findItem(R.id.menu_top_san_pham);
            if (topSanPhamItem != null) {
                topSanPhamItem.setVisible(false);
            }

            MenuItem topKhachHangItem = menu.findItem(R.id.menu_top_khach_hang);
            if (topKhachHangItem != null) {
                topKhachHangItem.setVisible(false);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_doanh_thu) {
            startActivity(new Intent(this, ThongKeDoanhThuActivity.class));
            return true;
        } else if (id == R.id.menu_top_san_pham) {
            startActivity(new Intent(this, ThongKeSanPhamActivity.class));
            return true;
        } else if (id == R.id.menu_top_khach_hang) {
            startActivity(new Intent(this, ThongKeSanPhamActivity.class));
            return true;
        }else if (id == R.id.menu_doi_mat_khau) {
            startActivity(new Intent(this, DoiMatKhauActivity.class));
            return true;
        }else if (id == R.id.menu_dang_xuat) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.lnDoanhThu) {
            startActivity(new Intent(MainActivity.this, ThongKeDoanhThuActivity.class));
        } else if (id == R.id.lnTopSanPham) {
            startActivity(new Intent(MainActivity.this, ThongKeSanPhamActivity.class));
        }else if (id == R.id.lnTopKhachHang) {
            startActivity(new Intent(MainActivity.this, ThongKeKhachHangActivity.class));
        }else if (id == R.id.lnSanPham) {
            startActivity(new Intent(MainActivity.this, QuanLySanPhamActivity.class));
        } else if (id == R.id.lnKhachHang) {
            startActivity(new Intent(MainActivity.this, QuanLyKhachHangActivity.class));
        } else if (id == R.id.lnHoaDon) {
            startActivity(new Intent(MainActivity.this, QuanLyHoaDonActivity.class));
        } else if (id == R.id.lnDanhMuc) {
            startActivity(new Intent(MainActivity.this, QuanLyDanhMucActivity.class));
        } else if (id == R.id.lnNhanVien){
            startActivity(new Intent(MainActivity.this, QuanLyNhanVienActivity.class));
        } else if (id == R.id.lnDoiMatKhau) {
            startActivity(new Intent(MainActivity.this, DoiMatKhauActivity.class));
        } else if (id == R.id.lnDangXuat) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
}

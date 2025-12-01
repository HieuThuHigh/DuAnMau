package ph41045.fpoly.duanmau_ph41045.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import ph41045.fpoly.duanmau_ph41045.Adapter.NhanVienAdapter;
import ph41045.fpoly.duanmau_ph41045.Database.DatabaseHelper;
import ph41045.fpoly.duanmau_ph41045.Model.NhanVien;
import ph41045.fpoly.duanmau_ph41045.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

public class QuanLyNhanVienActivity extends AppCompatActivity implements NhanVienAdapter.OnNhanVienClickListener {
    public static final String NHAN_VIEN = "NHAN_VIEN";
    private NhanVienAdapter nhanVienAdapter;
    private List<NhanVien> danhSachNhanVien;
    private DatabaseHelper db;

    //quay về màn hình trước
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nhan_vien);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView lvNhanVien = findViewById(R.id.lvNhanVien);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Quản lý nhân viên");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(this);
        FloatingActionButton fabThemNhanVien = findViewById(R.id.fabThemNhanVien);

        danhSachNhanVien = db.layTatCaNhanVien();
        nhanVienAdapter = new NhanVienAdapter(this, danhSachNhanVien);
        nhanVienAdapter.setOnNhanVienClickListener(this);
        lvNhanVien.setAdapter(nhanVienAdapter);

        fabThemNhanVien.setOnClickListener(view -> {
            Intent intent = new Intent(this, EditNhanVienActivity.class);
            intent.putExtra("Type", 1);
            this.startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        danhSachNhanVien.clear();
        danhSachNhanVien.addAll(db.layTatCaNhanVien());
        nhanVienAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEditNhanVien(NhanVien nhanVien) {
        Intent intent = new Intent(this, EditNhanVienActivity.class);
        intent.putExtra("Type", 0);
        intent.putExtra(NHAN_VIEN, nhanVien);

        startActivity(intent);
    }

    @Override
    public void onDeleteNhanVien(NhanVien nhanVien) {
        DatabaseHelper db = new DatabaseHelper(this);
        boolean isDeleted = db.xoaNhanVien(nhanVien.getMaNhanVien());
        if (isDeleted) {
            danhSachNhanVien.remove(nhanVien);
            nhanVienAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Xoá nhân viên thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Xoá nhân viên thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}


package com.example.sampleandroidproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sampleandroidproject.R;
import com.example.sampleandroidproject.adapter.DanhMucAdapter;
import com.example.sampleandroidproject.adapter.KhachHangAdapter;
import com.example.sampleandroidproject.database.DatabaseHelper;
import com.example.sampleandroidproject.model.DanhMuc;
import com.example.sampleandroidproject.model.KhachHang;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

public class QuanLyKhachHangActivity extends AppCompatActivity implements KhachHangAdapter.OnKhachHangClickListener {
    public static final String KHACH_HANG = "KHACH_HANG";
    private KhachHangAdapter khachHangAdapter;
    private List<KhachHang> danhSachKhachHang;
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
        setContentView(R.layout.activity_quan_ly_khach_hang);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView lvKhachHang = findViewById(R.id.lvKhachHang);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Quản lý khách hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(this);
        FloatingActionButton fabThemKhachHang = findViewById(R.id.fabThemKhachHang);

        danhSachKhachHang = db.layTatCaKhachHang();
        khachHangAdapter = new KhachHangAdapter(this, danhSachKhachHang);
        khachHangAdapter.setOnKhachHangClickListener(this);
        lvKhachHang.setAdapter(khachHangAdapter);

        fabThemKhachHang.setOnClickListener(view -> {
            Intent intent = new Intent(this, EditKhachHangActivity.class);
            intent.putExtra("Type", 1);
            this.startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        danhSachKhachHang.clear();
        danhSachKhachHang.addAll(db.layTatCaKhachHang());
        khachHangAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEditKhachHang(KhachHang khachHang) {
        Intent intent = new Intent(this, EditKhachHangActivity.class);
        intent.putExtra("Type", 0);
        intent.putExtra(KHACH_HANG, khachHang);

        startActivity(intent);
    }

    @Override
    public void onDeleteKhachHang(KhachHang khachHang) {
        DatabaseHelper db = new DatabaseHelper(this);
        boolean isDeleted = db.xoaKhachHang(khachHang.getMaKhachHang());
        if (isDeleted) {
            danhSachKhachHang.remove(khachHang);
            khachHangAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Xoá khách hàng thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Xoá khách hàng thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}


package com.example.sampleandroidproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sampleandroidproject.R;
import com.example.sampleandroidproject.adapter.DanhMucAdapter;
import com.example.sampleandroidproject.database.DatabaseHelper;
import com.example.sampleandroidproject.model.DanhMuc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

public class QuanLyDanhMucActivity extends AppCompatActivity implements DanhMucAdapter.OnDanhMucClickListener {
    public static final String DANH_MUC = "DANH_MUC";
    private DanhMucAdapter danhMucAdapter;
    private List<DanhMuc> danhSachDanhMuc;
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
        setContentView(R.layout.activity_quan_ly_danh_muc);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView lvDanhMuc = findViewById(R.id.lvSanPham);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Quản lý danh mục");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(this);
        FloatingActionButton fabThemDanhMuc = findViewById(R.id.fabThemDanhMuc);

        danhSachDanhMuc = db.layTatCaDanhMuc();
        danhMucAdapter = new DanhMucAdapter(this, danhSachDanhMuc);
        danhMucAdapter.setOnDanhMucClickListener(this);
        lvDanhMuc.setAdapter(danhMucAdapter);

        fabThemDanhMuc.setOnClickListener(view -> {
            Intent intent = new Intent(this, EditDanhMucActivity.class);
            intent.putExtra("Type", 1);
            this.startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        danhSachDanhMuc.clear();
        danhSachDanhMuc.addAll(db.layTatCaDanhMuc());
        danhMucAdapter.notifyDataSetChanged();
    }

    @Override
    public void onEditDanhMuc(DanhMuc danhMuc) {
        Intent intent = new Intent(this, EditDanhMucActivity.class);
        intent.putExtra("Type", 0);
        intent.putExtra(DANH_MUC, danhMuc);

        startActivity(intent);
    }

    @Override
    public void onDeleteDanhMuc(DanhMuc danhMuc) {
        DatabaseHelper db = new DatabaseHelper(this);
        boolean isDeleted = db.xoaDanhMuc(danhMuc.getMaDanhMuc());
        if (isDeleted) {
            danhSachDanhMuc.remove(danhMuc);
            danhMucAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Xoá danh mục thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Xoá danh mục thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}


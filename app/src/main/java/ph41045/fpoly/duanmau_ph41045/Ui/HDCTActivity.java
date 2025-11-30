package com.example.sampleandroidproject.ui;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sampleandroidproject.R;
import com.example.sampleandroidproject.adapter.HDCTAdapter;
import com.example.sampleandroidproject.database.DatabaseHelper;

import java.util.Objects;

public class HDCTActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView lvSanPham = findViewById(R.id.lvSanPham);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Hóa Đơn Chi Tiết");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DatabaseHelper db = new DatabaseHelper(this);
        String maHoaDon = getIntent().getStringExtra("maHoaDon");
        HDCTAdapter hdctAdapter = new HDCTAdapter(this, db.layTatCaHDCT(maHoaDon));
        lvSanPham.setAdapter(hdctAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}


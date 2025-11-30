package com.example.sampleandroidproject.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleandroidproject.R;
import com.example.sampleandroidproject.database.DatabaseHelper;
import com.example.sampleandroidproject.model.DanhMuc;

public class EditDanhMucActivity extends AppCompatActivity {
    private EditText edtMaDanhMuc, edtTenDanhMuc;
    private DatabaseHelper db;
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_danh_muc);

        db = new DatabaseHelper(this);
        edtMaDanhMuc = findViewById(R.id.edtMaDanhMuc);
        edtTenDanhMuc = findViewById(R.id.edtTenSanPham);
        findViewById(R.id.btnLuu).setOnClickListener(v -> luuDanhMuc());
        findViewById(R.id.btnHuy).setOnClickListener(v -> finish());
        LinearLayout layoutMaDanhMuc = findViewById(R.id.layoutMaDanhMuc);

        type = getIntent().getIntExtra("Type", -1);
        if (type == 0) { // Edit
            edtMaDanhMuc.setEnabled(false);
            DanhMuc danhMuc = getIntent().getParcelableExtra(QuanLyDanhMucActivity.DANH_MUC);
            edtMaDanhMuc.setText(danhMuc.getMaDanhMuc());
            edtTenDanhMuc.setText(danhMuc.getTenDanhMuc());
        } else if (type == 1) { // Add
            layoutMaDanhMuc.setVisibility(View.GONE);
        }
    }

    private void luuDanhMuc() {
        String maDanhMuc = edtMaDanhMuc.getText().toString().trim();
        String tenDanhMuc = edtTenDanhMuc.getText().toString().trim();

        boolean isOK;
        if (type == 0) { // Edit
            DanhMuc danhMuc = new DanhMuc(maDanhMuc, tenDanhMuc);
            isOK = db.suaDanhMuc(danhMuc);
        } else { // Add new
            maDanhMuc = db.taoMaDanhMucMoi();
            DanhMuc danhMuc = new DanhMuc(maDanhMuc, tenDanhMuc);
            isOK = db.themDanhMuc(danhMuc);
        }
        if (isOK) {
            Toast.makeText(this, (type == 0)? "Cập nhật": "Thêm" + " danh mục thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, (type == 0)? "Cập nhật": "Thêm" + " danh mục thất bại", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}

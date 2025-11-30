package com.example.sampleandroidproject.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sampleandroidproject.R;
import com.example.sampleandroidproject.common.Common;
import com.example.sampleandroidproject.database.DatabaseHelper;

import java.util.Objects;

public class DoiMatKhauActivity extends AppCompatActivity {
    private EditText edtMatKhauCu, edtMatKhauMoi, edtNhapLaiMatKhauMoi;
    private Button btnLuu, btnHuy;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        edtMatKhauCu = findViewById(R.id.edtOldPassword);
        edtMatKhauMoi = findViewById(R.id.edtNewPassword);
        edtNhapLaiMatKhauMoi = findViewById(R.id.edtConfirmPassword);
        btnLuu = findViewById(R.id.btnLuu);
        btnHuy = findViewById(R.id.btnHuy);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Đổi mật khẩu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(this);

        // Lấy mã nhân viên từ Intent để biết nhân viên nào đang đăng nhập

        btnLuu.setOnClickListener(v -> doiMatKhau());
        btnHuy.setOnClickListener(v -> {
            edtMatKhauCu.setText("");
            edtMatKhauMoi.setText("");
            edtNhapLaiMatKhauMoi.setText("");
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void doiMatKhau() {
        String matKhauCu = edtMatKhauCu.getText().toString().trim();
        String matKhauMoi = edtMatKhauMoi.getText().toString().trim();
        String nhapLaiMatKhauMoi = edtNhapLaiMatKhauMoi.getText().toString().trim();

        // Kiểm tra không được bỏ trống
        if (matKhauCu.isEmpty() || matKhauMoi.isEmpty() || nhapLaiMatKhauMoi.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        // Kiểm tra mật khẩu cũ có đúng không
        if (!db.kiemTraMatKhauCu(Common.maNhanVien, matKhauCu)) {
            Toast.makeText(this, "Mật khẩu cũ không chính xác!", Toast.LENGTH_SHORT).show();
            return;
        }
        // Kiểm tra mật khẩu mới và xác nhận phải giống nhau
        if (!matKhauMoi.equals(nhapLaiMatKhauMoi)) {
            Toast.makeText(this, "Mật khẩu mới không khớp!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Nếu đúng, tiến hành cập nhật mật khẩu mới vào SQLite
        if (db.capNhatMatKhauMoi(Common.maNhanVien, matKhauMoi)) {
            Toast.makeText(this, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
            finish(); // Đóng activity sau khi đổi mật khẩu
        } else {
            Toast.makeText(this, "Đổi mật khẩu thất bại, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
        }
    }
}

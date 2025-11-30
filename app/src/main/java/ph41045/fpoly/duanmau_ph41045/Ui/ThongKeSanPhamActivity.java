package com.example.sampleandroidproject.ui;

import static android.view.View.GONE;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sampleandroidproject.R;
import com.example.sampleandroidproject.adapter.TopSanPhamAdapter;
import com.example.sampleandroidproject.database.DatabaseHelper;
import com.example.sampleandroidproject.model.TopSanPham;

import java.util.Calendar;
import java.util.List;

public class ThongKeSanPhamActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private ListView listViewSanPham;
    private EditText edtNgayBatDau, edtNgayKetThuc, edtSoLuong;
    private TextView tvTopSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_san_pham);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thống kê sản phẩm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbHelper = new DatabaseHelper(this);

        listViewSanPham = findViewById(R.id.lvSanPham);
        edtNgayBatDau = findViewById(R.id.edtNgayBatDau);
        edtNgayKetThuc = findViewById(R.id.edtNgayKetThuc);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        tvTopSanPham = findViewById(R.id.tvTopSanPham);

        edtNgayBatDau.setOnClickListener(v -> showDatePickerDialog(edtNgayBatDau));
        edtNgayKetThuc.setOnClickListener(v -> showDatePickerDialog(edtNgayKetThuc));
        findViewById(R.id.btnTopSanPham).setOnClickListener(v -> {
            if (edtNgayBatDau.getText().toString().isEmpty()
                    || edtNgayKetThuc.getText().toString().isEmpty()
                    || edtSoLuong.getText().toString().isEmpty()) {
                tvTopSanPham.setText("Vui lòng nhập đầy đủ thông tin.");
                tvTopSanPham.setVisibility(View.VISIBLE);
                listViewSanPham.setVisibility(GONE);
                return;
            } else {
                listViewSanPham.setVisibility(View.VISIBLE);
                tvTopSanPham.setVisibility(GONE);
            }
            hienThiTopSanPham(Integer.parseInt(edtSoLuong.getText().toString().trim()));
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void hienThiTopSanPham(int n) {
        List<TopSanPham> topSanPhamList = dbHelper.thongKeTopSanPham(n);

        if (topSanPhamList != null && !topSanPhamList.isEmpty()) {
            TopSanPhamAdapter adapter = new TopSanPhamAdapter(this, topSanPhamList);
            listViewSanPham.setAdapter(adapter);
        } else {
            Toast.makeText(this, "Không có dữ liệu sản phẩm!", Toast.LENGTH_SHORT).show();
        }
    }


    private void showDatePickerDialog(EditText  editText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = selectedYear
                            + "-" + String.format("%02d", selectedMonth + 1)
                            + "-" + String.format("%02d", selectedDay);
                    editText.setText(selectedDate);
                },
                year, month, day
        );
        datePickerDialog.show();
    }
}

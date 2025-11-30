package com.example.sampleandroidproject.ui;

import static android.view.View.GONE;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sampleandroidproject.R;
import com.example.sampleandroidproject.adapter.TopKhachHangAdapter;
import com.example.sampleandroidproject.database.DatabaseHelper;
import com.example.sampleandroidproject.model.KhachHang;
import com.example.sampleandroidproject.model.TopKhachHang;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ThongKeKhachHangActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private ListView listViewKhachHang;
    private EditText edtNgayBatDau, edtNgayKetThuc, edtSoLuong;
    private Button btnThongKeTopKhachHang;
    private TextView tvTopKhachHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_khach_hang);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thống kê khách hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbHelper = new DatabaseHelper(this);

        edtNgayBatDau = findViewById(R.id.edtNgayBatDau);
        edtNgayKetThuc = findViewById(R.id.edtNgayKetThuc);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        btnThongKeTopKhachHang = findViewById(R.id.btnTopKhachHang);
        tvTopKhachHang = findViewById(R.id.tvTopKhachHang);
        listViewKhachHang = findViewById(R.id.listViewKhachHang);

        edtNgayBatDau.setOnClickListener(v -> showDatePickerDialog(edtNgayBatDau));
        edtNgayKetThuc.setOnClickListener(v -> showDatePickerDialog(edtNgayKetThuc));
        btnThongKeTopKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNgayBatDau.getText().toString().isEmpty()
                        || edtNgayKetThuc.getText().toString().isEmpty()
                        || edtSoLuong.getText().toString().isEmpty()) {
                    tvTopKhachHang.setText("Vui lòng nhập đầy đủ thông tin.");
                    tvTopKhachHang.setVisibility(View.VISIBLE);
                    listViewKhachHang.setVisibility(GONE);
                    return;
                } else {
                    listViewKhachHang.setVisibility(View.VISIBLE);
                    tvTopKhachHang.setVisibility(GONE);
                }
                hienThiTopKhachHang(Integer.parseInt(edtSoLuong.getText().toString().trim()));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void hienThiTopKhachHang(int m) {
        List<TopKhachHang> topKhachHangList = dbHelper.thongKeTopKhachHang(m);

        if (topKhachHangList != null && !topKhachHangList.isEmpty()) {
            TopKhachHangAdapter adapter = new TopKhachHangAdapter(this, topKhachHangList);
            listViewKhachHang.setAdapter(adapter);
        } else {
            Toast.makeText(this, "Không có dữ liệu khách hàng!", Toast.LENGTH_SHORT).show();
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

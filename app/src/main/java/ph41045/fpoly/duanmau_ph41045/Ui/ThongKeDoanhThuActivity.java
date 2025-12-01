package ph41045.fpoly.duanmau_ph41045.Ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import ph41045.fpoly.duanmau_ph41045.Database.DatabaseHelper;
import ph41045.fpoly.duanmau_ph41045.R;

import java.util.Calendar;

public class ThongKeDoanhThuActivity extends AppCompatActivity {
    private EditText edtNgayBatDau, edtNgayKetThuc;
    private TextView tvDoanhThu;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_doanh_thu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thống kê doanh thu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseHelper = new DatabaseHelper(this);

        edtNgayBatDau = findViewById(R.id.edtNgayBatDau);
        edtNgayKetThuc = findViewById(R.id.edtNgayKetThuc);
        tvDoanhThu = findViewById(R.id.tvDoanhThu);

        edtNgayBatDau.setOnClickListener(v -> showDatePickerDialog(edtNgayBatDau));
        edtNgayKetThuc.setOnClickListener(v -> showDatePickerDialog(edtNgayKetThuc));
        findViewById(R.id.btnThongKeDoanhThu).setOnClickListener(v -> {
            String ngayBatDau = edtNgayBatDau.getText().toString().trim();
            String ngayKetThuc = edtNgayKetThuc.getText().toString().trim();
            if (ngayBatDau.isEmpty() || ngayKetThuc.isEmpty()) {
                tvDoanhThu.setText("Vui lòng nhập đầy đủ ngày bắt đầu và ngày kết thúc.");
                return;
            }

            int doanhThu = databaseHelper.layDoanhThu(ngayBatDau, ngayKetThuc);
            tvDoanhThu.setText("Doanh thu: " + doanhThu + " VND");
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void showDatePickerDialog(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
//                    String selectedDate = selectedYear
//                            + "-" + String.format("%02d", selectedMonth + 1)
//                            + "-" + String.format("%02d", selectedDay);
                    String selectedDate = String.format("%02d/%02d/%04d", selectedDay, selectedMonth, selectedYear);
                    editText.setText(selectedDate);
                },
                year, month, day
        );
        datePickerDialog.show();
    }
}

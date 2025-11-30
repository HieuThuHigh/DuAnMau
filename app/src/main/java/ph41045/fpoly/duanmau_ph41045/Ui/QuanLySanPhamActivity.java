package com.example.sampleandroidproject.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sampleandroidproject.R;
import com.example.sampleandroidproject.adapter.SanPhamAdapter;
import com.example.sampleandroidproject.common.Common;
import com.example.sampleandroidproject.database.DatabaseHelper;
import com.example.sampleandroidproject.common.GioHang;
import com.example.sampleandroidproject.model.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

public class QuanLySanPhamActivity extends AppCompatActivity implements SanPhamAdapter.OnSanPhamClickListener {
    public static final String SAN_PHAM = "SAN_PHAM";
    private SanPhamAdapter sanPhamAdapter;
    private List<SanPham> danhSachSanPham;
    private DatabaseHelper db;
    TextView tvSoLuong;
    RelativeLayout itemCart;
    GioHang gioHang;

    //quay về màn hình trước
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_san_pham);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView lvSanPham = findViewById(R.id.lvSanPham);
        EditText edtSearch = findViewById(R.id.edtSearch);
        tvSoLuong = findViewById(R.id.tvSoLuong);
        itemCart = findViewById(R.id.itemCart);
        gioHang = Common.getGioHang();
        Objects.requireNonNull(getSupportActionBar()).setTitle("Quản lý sản phẩm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(this);
        FloatingActionButton fabAddProduct = findViewById(R.id.fabThemDanhMuc);

        // Lấy danh sách sản phẩm từ SQLite
        danhSachSanPham = db.timKiemSanPham("");
        sanPhamAdapter = new SanPhamAdapter(this, danhSachSanPham);
        sanPhamAdapter.setOnSanPhamClickListener(this);
        lvSanPham.setAdapter(sanPhamAdapter);

        fabAddProduct.setOnClickListener(view -> {
            Intent intent = new Intent(this, EditSanPhamActivity.class);
            intent.putExtra("Type", 1);
            this.startActivity(intent);
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                timKiemSanPham(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        itemCart.setOnClickListener(v -> {
            Intent intent = new Intent(this, GioHangActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        capNhatDanhSachSanPham();
        tvSoLuong.setText(String.valueOf(gioHang.getDanhSachGioHang().size()));
    }

    private void timKiemSanPham(String tuKhoa) {
        danhSachSanPham.clear();
        danhSachSanPham.addAll(db.timKiemSanPham(tuKhoa));
        sanPhamAdapter.notifyDataSetChanged();
    }

    private void capNhatDanhSachSanPham() {
        danhSachSanPham.clear();
        danhSachSanPham.addAll(db.timKiemSanPham("")); // Lấy dữ liệu mới từ SQLite
        sanPhamAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAddToCartSanPham(SanPham sanPham, View iconGioHangItem) {
        gioHang.addSanPham(sanPham);
        tvSoLuong.setText(String.valueOf(gioHang.getDanhSachGioHang().size()));

        // Gọi hàm thực hiện hiệu ứng animation
        animateAddToCart(iconGioHangItem, itemCart);
    }

    private void animateAddToCart(View startView, View cartIcon) {
        // Lấy vị trí của icon giỏ hàng và sản phẩm
        int[] startLoc = new int[2];
        int[] endLoc = new int[2];
        startView.getLocationOnScreen(startLoc);
        cartIcon.getLocationOnScreen(endLoc);

        // Tạo ImageView giả lập sản phẩm
        ImageView animView = new ImageView(this);
        animView.setImageDrawable(((ImageView) startView).getDrawable());

        // LayoutParams cho animation
        ViewGroup rootLayout = (ViewGroup) getWindow().getDecorView();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(startView.getWidth(), startView.getHeight());
        params.leftMargin = startLoc[0];
        params.topMargin = startLoc[1];
        rootLayout.addView(animView, params);

        // Tạo Animation phóng to ban đầu
        ObjectAnimator scaleUpX = ObjectAnimator.ofFloat(animView, "scaleX", 1f, 5f);
        ObjectAnimator scaleUpY = ObjectAnimator.ofFloat(animView, "scaleY", 1f, 5f);
        AnimatorSet scaleUpSet = new AnimatorSet();
        scaleUpSet.playTogether(scaleUpX, scaleUpY);
        scaleUpSet.setDuration(300);

        // Tạo Animation di chuyển vào giỏ hàng + thu nhỏ lại
        ObjectAnimator translateX = ObjectAnimator.ofFloat(animView, "translationX", endLoc[0] - startLoc[0]);
        ObjectAnimator translateY = ObjectAnimator.ofFloat(animView, "translationY", endLoc[1] - startLoc[1]);
        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(animView, "scaleX", 5f, 0.2f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(animView, "scaleY", 5f, 0.2f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(animView, "alpha", 1f, 0f);

        // Gom hiệu ứng phóng to + di chuyển thành chuỗi animation
        AnimatorSet moveToCartSet = new AnimatorSet();
        moveToCartSet.playTogether(translateX, translateY, scaleDownX, scaleDownY, alpha);
        moveToCartSet.setDuration(500);

        // Chạy phóng to trước, sau đó mới di chuyển
        AnimatorSet finalSet = new AnimatorSet();
        finalSet.playSequentially(scaleUpSet, moveToCartSet);
        finalSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                rootLayout.removeView(animView);
            }
        });

        finalSet.start();
    }

    @Override
    public void onEditSanPham(SanPham sanPham) {
        Intent intent = new Intent(this, EditSanPhamActivity.class);
        intent.putExtra("Type", 0);
        intent.putExtra(SAN_PHAM, sanPham);

        startActivity(intent);
    }

    @Override
    public void onDeleteSanPham(SanPham sanPham) {
        DatabaseHelper db = new DatabaseHelper(this);
        boolean isDeleted = db.xoaSanPham(sanPham.getMaSanPham());
        if (isDeleted) {
            danhSachSanPham.remove(sanPham);
            sanPhamAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Xoá sản phẩm thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Xoá sản phẩm thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}


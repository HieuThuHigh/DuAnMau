package ph41045.fpoly.duanmau_ph41045.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import ph41045.fpoly.duanmau_ph41045.R;


public class ChaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chao);

        // Chuyển sang màn hình đăng nhập sau 1.5 giây
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(ChaoActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, 1500);
    }
}
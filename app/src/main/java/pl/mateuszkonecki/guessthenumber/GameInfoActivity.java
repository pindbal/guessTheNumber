package pl.mateuszkonecki.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.guessthenumber.R;

public class GameInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_info);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }
}
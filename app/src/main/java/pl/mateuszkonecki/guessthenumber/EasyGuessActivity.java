package pl.mateuszkonecki.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.guessthenumber.R;
import com.example.guessthenumber.databinding.ActivityEasyGuessBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Random;

public class EasyGuessActivity extends AppCompatActivity {

    ActivityEasyGuessBinding binding;
    int wylosowana;
    private Random random = new Random();
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEasyGuessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        playAgain();

        binding.checkNumberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userInput = binding.numberET.getText().toString();

                if(userInput.isEmpty()) {
                    binding.numberET.setError(getResources().getString(R.string.empty));
                } else if(userInput.contains(".")) {
                    binding.numberET.setError(getResources().getString(R.string.incorrectFormat));
                } else {
                    int userInputInt = Integer.parseInt(userInput);
                    if(wylosowana == userInputInt) {
                        new MaterialAlertDialogBuilder(EasyGuessActivity.this)
                                .setTitle(getResources().getString(R.string.congratsTitle))
                                .setMessage(getResources().getString(R.string.congratsMessage))
                                .setCancelable(false)
                                .setBackground(getDrawable(R.drawable.material_background))
                                .setPositiveButton(getResources().getString(R.string.congratsPositive), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        playAgain();
                                    }
                                })
                                .setNeutralButton(getResources().getString(R.string.congratsNeutral), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        finish();
                                        overridePendingTransition(R.anim.left_in, R.anim.right_out);
                                    }
                                })
                                .show();
                    }

                    if(wylosowana != userInputInt) {
                        counter++;
                    }

                    if(counter == 1) {
                        binding.heartRight.setImageResource(R.drawable.ic_heart_broken_24);
                        binding.numberET.setText("");
                    } else if(counter == 2) {
                        binding.heartMiddle.setImageResource(R.drawable.ic_heart_broken_24);
                        binding.numberET.setText("");
                    } else if(counter == 3) {
                        binding.heartLeft.setImageResource(R.drawable.ic_heart_broken_24);
                        binding.numberET.setText("");
                        new MaterialAlertDialogBuilder(EasyGuessActivity.this)
                                .setTitle(getResources().getString(R.string.loseTitle))
                                .setMessage(getResources().getString(R.string.loseMessage))
                                .setCancelable(false)
                                .setBackground(getDrawable(R.drawable.material_background))
                                .setPositiveButton(getResources().getString(R.string.losePositive), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        playAgain();
                                    }
                                })
                                .setNeutralButton(getResources().getString(R.string.loseNegative), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        finish();
                                        overridePendingTransition(R.anim.left_in, R.anim.right_out);
                                    }
                                })
                                .show();
                    }
                }
            }
        });
    }

    private void playAgain() {
        counter = 0;
        binding.heartLeft.setImageResource(R.drawable.ic_favorite_24);
        binding.heartMiddle.setImageResource(R.drawable.ic_favorite_24);
        binding.heartRight.setImageResource(R.drawable.ic_favorite_24);
        wylosowana = random.nextInt(15)+1;
        binding.numbersRange.setText("1 --> 15");
        binding.numberET.setText("");
    }

    @Override
    public void onBackPressed() {
        new MaterialAlertDialogBuilder(EasyGuessActivity.this)
                .setTitle(getResources().getString(R.string.exitTitle))
                .setMessage(getResources().getString(R.string.exitMessage))
                .setCancelable(false).setIcon(getDrawable(R.drawable.ic_warning_24))
                .setBackground(getDrawable(R.drawable.material_background))
                .setPositiveButton(getResources().getString(R.string.exitPositive), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        overridePendingTransition(R.anim.left_in, R.anim.right_out);
                    }
                })
                .setNeutralButton(getResources().getString(R.string.exitNegative), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }
}
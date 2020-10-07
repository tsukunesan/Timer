package tsukunesan.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 宣言
        final String START_MESSAGE = "START";
        final String STOP_MESSAGE = "STOP";
        final String finish_message = "カウント終了";
        Button StartButton = findViewById(R.id.StartButton);
        Button StopButton = findViewById(R.id.StopButton);
        final EditText editTime = findViewById(R.id.editTime);
        final CountDownTimer[] cdt = new CountDownTimer[1];

        // スタートボタンイベントリスナー
        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // スタートトースト
                Toast toast = Toast.makeText(MainActivity.this, START_MESSAGE, Toast.LENGTH_SHORT);
                toast.show();

                // 入力時間取得
                int inputTime = Integer.parseInt(String.valueOf(editTime.getText()));

                // カウントダウンタイマー
                cdt[0] = new CountDownTimer( inputTime*1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        editTime.setText(Long.toString(millisUntilFinished/1000));
                    }

                    @Override
                    public void onFinish() {

                        //バイブレーション
                        Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                        v.vibrate(new long[]{1000, 1000, 1000, 1000, 1000, 1000}, -1);

                        // 終了トースト
                        Toast toast = Toast.makeText(MainActivity.this, finish_message, Toast.LENGTH_SHORT);
                        toast.show();

                        // カウントを0に設定
                        editTime.setText("0");
                    }
                }.start();
            }
        });

        // ストップボタンイベントリスナー
        StopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // ストップトースト
                Toast toast = Toast.makeText(MainActivity.this, STOP_MESSAGE, Toast.LENGTH_SHORT);
                toast.show();

                // カウントダウンタイマー停止
                cdt[0].cancel();
            }
        });
    }
}


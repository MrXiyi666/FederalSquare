package fun.android.federal_square;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import fun.android.federal_square.NetWork.NetWork_Main;
import fun.android.federal_square.system.Static;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new NetWork_Main(this).start(Static.url + "cd99ce234a39c120.png");
    }
}
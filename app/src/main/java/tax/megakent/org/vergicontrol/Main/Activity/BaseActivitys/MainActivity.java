package tax.megakent.org.vergicontrol.Main.Activity.BaseActivitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import tax.megakent.org.vergicontrol.R;

//@author mcanv & emre
public class MainActivity extends Activity {


    //65239212594 && 62242312440 - 0000064678 & admin , bld17ars
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.karsilama);

        Thread timerThread = new Thread(){
            @Override
            public void run() {try {
                    sleep(1000);

                }catch (InterruptedException e){

                }finally {
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);

                }
            }
        };
        timerThread.start();
    }
    protected void onPause(){
        super.onPause();
        finish();
    }
}

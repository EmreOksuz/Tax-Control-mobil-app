package tax.megakent.org.vergicontrol.Main.Activity.BaseActivitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import tax.megakent.org.vergicontrol.R;


public class HomeActivity extends AppCompatActivity {



    private Button b_borc, b_tahsilat,b_sicil,b_abonelik;
    private Bundle extras;//veri almak için

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(tax.megakent.org.vergicontrol.R.layout.activity_home);
        extras = getIntent().getExtras();//Veri alınacak bunun için setleme yap

        b_borc = (Button) findViewById(R.id.borcSorgu_btn);
        b_borc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent borcIntent = new Intent(HomeActivity.this,BorcActivity.class);
                borcIntent.putExtra("referansNo",extras.getString("referansNo"));//Gelen veriyi key'i ile birlikte çağırıyoruz
                startActivity(borcIntent);
            }
        });
        b_tahsilat = (Button) findViewById(R.id.tahsilatSorgu_btn);
        b_tahsilat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tahsilatIntent = new Intent(HomeActivity.this,TahsilatActivity.class);
                tahsilatIntent.putExtra("referansNo",extras.getString("referansNo"));//Gelen veriyi key'i ile birlikte çağırıyoruz
                startActivity(tahsilatIntent);
            }
        });
        b_sicil = (Button) findViewById(R.id.sicilSorgu_btn);
        b_sicil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sicilIntent = new Intent(HomeActivity.this,SicilActivity.class);
                sicilIntent.putExtra("referansNo",extras.getString("referansNo"));//Gelen veriyi key'i ile birlikte çağırıyoruz
                startActivity(sicilIntent);
            }
        });
        b_abonelik = (Button) findViewById(R.id.abonelikSorgu_btn);
        b_abonelik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abonelikIntent = new Intent(HomeActivity.this,AbonelikActivity.class);
                abonelikIntent.putExtra("referansNo",extras.getString("referansNo"));//Gelen veriyi key'i ile birlikte çağırıyoruz
                startActivity(abonelikIntent);
            }
        });

    }
}

package tax.megakent.org.vergicontrol.Main.Activity.BaseActivitys;

import android.content.Intent;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tax.megakent.org.vergicontrol.Main.DatabaseHelper.DBHelper;
import tax.megakent.org.vergicontrol.Main.SoapWebAccess.WebServiceAccess;
import tax.megakent.org.vergicontrol.R;



public class RegisterActivity extends AppCompatActivity {

    EditText tcKimlik,password,rePassword,sicilno;
    Button register,cancel;
    private DBHelper dbHelper;
    private String giden_tcKimk,giden_password,giden_Sicilno;
    private String chk_password1,chk_password2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHelper = new DBHelper(getApplicationContext());

        tcKimlik = (EditText) findViewById(R.id.register_tckimlik);
        password = (EditText) findViewById(R.id.register_password);
        rePassword = (EditText) findViewById(R.id.register_replaypassword);
        sicilno =(EditText) findViewById(R.id.register_sicilNo);
        register = (Button) findViewById(R.id.register_button);
        cancel = (Button) findViewById(R.id.register_cancel);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    chk_password1 = password.getText().toString();
                    chk_password2 = rePassword.getText().toString();


                    try {

                         giden_tcKimk = tcKimlik.getText().toString();
                         giden_password= password.getText().toString();
                         giden_Sicilno=sicilno.getText().toString();
                        try {
                            AsynCallWsSicil asynCallWsSicil = new AsynCallWsSicil();
                            asynCallWsSicil.execute();
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }catch (SQLException e){
                        e.printStackTrace();
                        Log.d("Catch","Veritabanına Eklenemedi");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(RegisterActivity.this,LoginActivity.class);
                RegisterActivity.this.startActivity(cancel);
                finish();

            }
        });


    }
    public boolean CheckPassword(String chk_password1, String chk_password2){

        if(isLegalPassword(chk_password1)){
            if (chk_password1.equals(chk_password2)){
               // System.out.println("P1 =" + chk_password1 + "P2 ="+chk_password2);
                //Log.d(chk_password1,chk_password2);
                //Toast.makeText(RegisterActivity.this, "Bilgiler Dogru", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
       // Toast.makeText(RegisterActivity.this, "Giris Bilgilerini Kontrol edin", Toast.LENGTH_SHORT).show();
        return false;
    }
    public boolean isLegalPassword(String pass) {

        if (!pass.matches(".*[A-Z].*")) return false;

        if (!pass.matches(".*[a-z].*")) return false;

        if (!pass.matches(".*\\d.*")) return false;

        return true;
    }
    private class AsynCallWsSicil extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {
            WebServiceAccess webServiceAccess = new WebServiceAccess();
            String result = webServiceAccess.SicilServiceSicilNo(giden_tcKimk,giden_Sicilno);

            return result;
        }
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            if (result!=null && result.equals(giden_Sicilno) && CheckPassword(chk_password1,chk_password2)) {
                dbHelper.insertPerson(giden_tcKimk, giden_password, giden_Sicilno);
                //Log.d("Try", "VeriTabanına veriler eklendi");
                Toast.makeText(RegisterActivity.this, "Kayıt Başarıyla Gerçekleşti", Toast.LENGTH_SHORT).show();
            }else  {
                Toast.makeText(RegisterActivity.this, "Lütfen Bilgilerinizi Kontrol Edin", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

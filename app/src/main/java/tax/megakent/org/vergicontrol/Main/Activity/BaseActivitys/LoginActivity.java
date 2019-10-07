package tax.megakent.org.vergicontrol.Main.Activity.BaseActivitys;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import tax.megakent.org.vergicontrol.Main.DatabaseHelper.DBHelper;
import tax.megakent.org.vergicontrol.R;


public class LoginActivity extends ActionBarActivity {

    EditText et_userTC, et_userPassword;
    Button b_login, b_register;
    private CheckBox cb_tick;

    private static String giris_tcKimlik;
    private static String giris_password;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_userTC = (EditText) findViewById(R.id.userTC);
        et_userPassword = (EditText) findViewById(R.id.userPassword);
        cb_tick = (CheckBox) findViewById(R.id.checkBox);

        pref = getSharedPreferences("login.conf",Context.MODE_PRIVATE);
        editor = pref.edit();
        if(pref.getBoolean("saveLogin",false))
             cb_tick.setChecked(true);
        else cb_tick.setChecked(false);
        et_userTC.setText(pref.getString("username",""));
        et_userPassword.setText(pref.getString("password",""));

        b_login = (Button) findViewById(R.id.login_button);
        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                giris_tcKimlik = et_userTC.getText().toString();
                giris_password = et_userPassword.getText().toString();
                boolean loginCheck = dbHelper.Login(giris_tcKimlik, giris_password);
               // webServiceAccess = new WebServiceAccess();
                if (loginCheck) {
                    loginRememberMe();
                    Intent homeAc = new Intent(LoginActivity.this, HomeActivity.class);
                    homeAc.putExtra("referansNo",giris_tcKimlik);//Basarili bir giris yaptıysa veriyi TcKimliği HomeActvity'e gönder
                    finish();
                    LoginActivity.this.startActivity(homeAc);

                } else {
                    Toast.makeText(LoginActivity.this, "Giris Basarisiz", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b_register = (Button) findViewById(R.id.login_RegisterButton);
        b_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

    }
    private void loginRememberMe(){
        if (cb_tick.isChecked()) {
            editor.putBoolean("saveLogin", true);
            editor.putString("username", giris_tcKimlik);
            editor.putString("password", giris_password);
            editor.commit();
        } else {
            editor.putBoolean("saveLogin",false);
            editor.remove("password");
            editor.remove("username");
            editor.apply();
        }
    }
}

package thinhtien.pntt.phannguyentruongthinh.sharedperferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnLogIn;
    EditText edtUserName, edtPassword;
    CheckBox cbRemember;

    SharedPreferences  sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        sharedPreferences = getSharedPreferences("dataLogin",MODE_PRIVATE);

        // lấy giá trị sharedPreferences
        edtUserName.setText(sharedPreferences.getString("account",""));
        edtPassword.setText(sharedPreferences.getString("password",""));
        cbRemember.setChecked(sharedPreferences.getBoolean("checked",false));

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUserName.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (username.equals("thinh") && password.equals("123")){
                    Toast.makeText(MainActivity.this, "Log in Complete", Toast.LENGTH_SHORT).show();
                    // kiểm tra người dùng có check vào remember không rồi mới lưu dữ liệu
                    if (cbRemember.isChecked()){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("account",username);
                        editor.putString("password",password);
                        editor.putBoolean("checked",true);
                        editor.commit();
                    } else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("account");
                        editor.remove("password");
                        editor.remove("checked");
                        editor.commit();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Log In Fail !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void AnhXa() {
        btnLogIn        = findViewById(R.id.buttonLogIn);
        edtUserName     = findViewById(R.id.editTextUserName);
        edtPassword     = findViewById(R.id.editTextPassword);
        cbRemember      = findViewById(R.id.checkBoxRemember);
    }
}

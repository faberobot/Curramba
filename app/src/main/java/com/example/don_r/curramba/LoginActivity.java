package com.example.don_r.curramba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText eUsuario, eContraseña;
    Button bIniciar;
    TextView tRegistro;
    String username, password, correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       /* Bundle extras = getIntent().getExtras();

        username= extras.getString("username");
        password= extras.getString("contraseña");
        correo = extras.getString("correo");*/

        eUsuario=(EditText) findViewById(R.id.eUsuario);
        eContraseña=(EditText) findViewById(R.id.eContraseña);
        bIniciar=(Button) findViewById(R.id.bIniciar);
        tRegistro=(TextView) findViewById(R.id.tRegistro);

        bIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //se compara con los datos obtenidos en registro y se va con el intent a otra actividad
                if(eUsuario.getText().toString().equals("") || eContraseña.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Favor ingresar la información requerida",Toast.LENGTH_SHORT).show();
                    return;
                }

                if ((eUsuario.getText().toString().equals(username))&&(eContraseña.getText().toString().equals(password)))
                {
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("correo", correo);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),"Usuario o Contraseña no válidos",Toast.LENGTH_SHORT).show();
                    eUsuario.setText("");
                    eContraseña.setText("");
                    return;
                }

            }
        });

        tRegistro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                Intent intent= new Intent(LoginActivity.this, RegistroActivity.class);
                startActivityForResult(intent, 1234);}

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //se comparan las cadenas con los valores de registro
        if(requestCode == 1234 && resultCode == RESULT_OK){
            username = data.getExtras().getString("username");
            password= data.getExtras().getString("password");
            correo= data.getExtras().getString("correo");
            Log.d("NombreUsuario",username);

        }else{
            if(requestCode == 1234 && resultCode == RESULT_CANCELED){
                Toast.makeText(this, "ERROR en Registro", Toast.LENGTH_SHORT).show();
            }
        }
    }
}



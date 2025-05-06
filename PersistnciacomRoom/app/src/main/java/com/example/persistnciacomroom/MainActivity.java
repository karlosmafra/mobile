package com.example.persistnciacomroom;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText login;
    private EditText senha;
    private Button cadastrar;
    private MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        login = findViewById(R.id.editTextLogin);
        senha = findViewById(R.id.editTextSenha);
        cadastrar = findViewById(R.id.buttonCadastrar);
        db = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "mydatabase.db").build();

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastraUsuario();
            }
        });

    }

    private void cadastraUsuario() {

        String loginInformado = login.getText().toString();
        String senhaInformada = senha.getText().toString();

        Usuario usuarioAtual = new Usuario();
        usuarioAtual.login = loginInformado;
        usuarioAtual.senha = senhaInformada;
        usuarioAtual.id = 0;

        new Thread(new Runnable() {
            @Override
            public void run() {
                db.usuarioDAO().insertUsuario(usuarioAtual);
                List<Usuario> listaUsuarios = db.usuarioDAO().selectAll();
                for (Usuario usuarioCorrente: listaUsuarios) {
                    Log.d("USUÁRIOS", "Login: " + usuarioCorrente.login + " Senha: " + usuarioCorrente.senha);
                }
            }
        }).start();

    }
}
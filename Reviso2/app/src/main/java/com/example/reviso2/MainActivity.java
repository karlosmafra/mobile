package com.example.reviso2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText descricao, valor;
    private Button salvar;
    private TextView informacoes;
    private MyDatabase db;
    private int valorTotal = 0;

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

        descricao = findViewById(R.id.editTextDescricao);
        valor = findViewById(R.id.editTextValor);
        salvar = findViewById(R.id.buttonSalvar);
        informacoes = findViewById(R.id.textViewInformacoes);
        db = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "meusgastos.db").build();

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarGasto();
            }
        });

    }

    private void salvarGasto() {

        String descricaoInformada = descricao.getText().toString();
        float valorinformado = Float.parseFloat(valor.getText().toString());
        Gasto gasto = new Gasto(0, descricaoInformada, valorinformado);

        new Thread(new Runnable() {
            @Override
            public void run() {

                db.gastoDAO().inserir(gasto);
                List<Gasto> listaGastos = db.gastoDAO().buscarTodosGastos();

                valorTotal = 0;
                for (Gasto gastoAtual: listaGastos) {
                    valorTotal += gastoAtual.getValor();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        informacoes.setText("Você gastou até o momento: " + valorTotal);
                    }
                });

            }
        }).start();

    }
}
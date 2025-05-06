// Aluno: Carlos Eduardo Mafra

package com.example.prova1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private RadioGroup pergunta1;
    private RadioGroup pergunta2;
    private RadioGroup pergunta3;
    private ImageButton botaoFinalizar;

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

        inicializaTela();

    }

    private void inicializaTela() {

        setTitle(R.string.tela1);

        pergunta1 = findViewById(R.id.mainPergunta1);
        pergunta2 = findViewById(R.id.mainPergunta2);
        pergunta3 = findViewById(R.id.mainPergunta3);
        botaoFinalizar = findViewById(R.id.mainButtonFinalizar);

        botaoFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviaProva();
            }
        });

    }

    private void enviaProva() {

        String resposta1 = verificaSelecao(pergunta1);
        String resposta2 = verificaSelecao(pergunta2);
        String resposta3 = verificaSelecao(pergunta3);

        if (resposta1.equals("") || resposta2.equals("") || resposta3.equals("")) {

            Toast.makeText(MainActivity.this, "Responda todas as questões", Toast.LENGTH_SHORT).show();

        }
        else {

            Intent telaResultado = new Intent(this, com.example.prova1.telaResultado.class);
            telaResultado.putExtra("chaveResposta1", resposta1);
            telaResultado.putExtra("chaveResposta2", resposta2);
            telaResultado.putExtra("chaveResposta3", resposta3);

            startActivity(telaResultado);

        }

    }

    private String verificaSelecao(RadioGroup pergunta) {

        int idOpcaoSelecionada = pergunta.getCheckedRadioButtonId();

        if (idOpcaoSelecionada == -1) {
            return "";
        }

        RadioButton radioButtonSelecionado = findViewById(idOpcaoSelecionada);
        String resposta = radioButtonSelecionado.getText().toString();

        return resposta;

    }
}
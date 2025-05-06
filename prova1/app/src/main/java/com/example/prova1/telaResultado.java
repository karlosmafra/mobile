package com.example.prova1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class telaResultado extends AppCompatActivity {

    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_resultado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inicializaTelaResultado();

    }

    private void inicializaTelaResultado() {

        setTitle(R.string.tela2);

        resultado = findViewById(R.id.resultadoTextResultado);

        int acertosTotais = calculaNota();

        switch (acertosTotais) {
            case 0:
                resultado.setText("A sua nota foi: 0");
            break;
            case 1:
                resultado.setText("A sua nota foi: 3");
            break;
            case 2:
                resultado.setText("A sua nota foi: 6");
            break;
            case 3:
                resultado.setText("A sua nota foi: 10");
            break;
        }

    }

    private int calculaNota() {

        int acertos = 0;

        String resposta1 = getIntent().getStringExtra("chaveResposta1");
        String resposta2 = getIntent().getStringExtra("chaveResposta2");
        String resposta3 = getIntent().getStringExtra("chaveResposta3");

        if (resposta1.equals("Verdadeiro"))
            acertos++;
        if (resposta2.equals("Verdadeiro"))
            acertos++;
        if (resposta3.equals("Falso"))
            acertos++;

        return acertos;

    }

}
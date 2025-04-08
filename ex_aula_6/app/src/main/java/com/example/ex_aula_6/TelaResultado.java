package com.example.ex_aula_6;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaResultado extends AppCompatActivity {

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

        resultado = findViewById(R.id.telaResultadoTextViewResultado);

        int notaFinal = calculaNota();

        if (notaFinal == 10)
            resultado.setText("Parabéns, você tirou nota 10!");
        else if (notaFinal == 5)
            resultado.setText("Você tirou nota 5");
        else
            resultado.setText("Você errou as duas questões, sua nota é 0");

    }

    private int calculaNota() {

        String resposta1 = getIntent().getStringExtra("chaveResposta1");
        String resposta2 = getIntent().getStringExtra("chaveResposta2");

        if (resposta1.equals("C - Washington") && resposta2.equals("D - Júpiter"))
            return 10;
        else if (resposta1.equals("C - Washington") || resposta2.equals("D - Júpiter"))
            return 5;

        return 0;


    }
}
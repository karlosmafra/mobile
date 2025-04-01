package com.example.ex_5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView nota1 = findViewById(R.id.editTextNota1);
        TextView nota2 = findViewById(R.id.editTextNota2);
        Button button_calc = findViewById(R.id.buttonCalcular);
        TextView resultado = findViewById(R.id.textResultado);

        button_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nota1_txt = nota1.getText().toString();
                String nota2_txt = nota2.getText().toString();

                float nota1_val = Float.parseFloat(nota1_txt);
                float nota2_val = Float.parseFloat(nota2_txt);

                double media_final = nota1_val*0.4 + nota2_val*0.6;

                resultado.setText("A nota final é: " + media_final);

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
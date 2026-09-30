package com.miguel.gradecalc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val txtResultado = findViewById<TextView>(R.id.txtResultado)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)

        val calculadora = CalcularNotas()

        btnCalcular.setOnClickListener {

            val resultado = calculadora.calcularMedia(
                p1 = 7.0,
                p2 = 6.0,
                atividade = 2.0
            )

            txtResultado.text = "Média: %.1f".format(resultado)
        }
    }
}
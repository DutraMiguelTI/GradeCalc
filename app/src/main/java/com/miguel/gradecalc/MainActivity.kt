package com.miguel.gradecalc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val edtP1 = findViewById<EditText>(R.id.edtP1)
        val edtP2 = findViewById<EditText>(R.id.edtP2)
        val edtAtividade = findViewById<EditText>(R.id.edtAtividade)

        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val txtResultado = findViewById<TextView>(R.id.txtResultado)

        val calculadora = CalcularNotas()

        btnCalcular.setOnClickListener {

            val textoP1 = edtP1.text.toString().trim()
            val textoP2 = edtP2.text.toString().trim()
            val textoAtividade = edtAtividade.text.toString().trim()

            if (textoP1.isEmpty() || textoP2.isEmpty() || textoAtividade.isEmpty()) {
                txtResultado.text = "Preencha todos os campos."
                return@setOnClickListener
            }

            val p1 = textoP1.replace(",", ".").toDoubleOrNull()
            val p2 = textoP2.replace(",", ".").toDoubleOrNull()
            val atividade = textoAtividade.replace(",", ".").toDoubleOrNull()

            if (p1 == null || p2 == null || atividade == null) {
                txtResultado.text = "Digite valores numéricos válidos."
                return@setOnClickListener
            }

            val erros = mutableListOf<String>()

            if (p1 !in 0.0..10.0) {
                erros.add("A P1 deve estar entre 0 e 10.")
            }

            if (p2 !in 0.0..10.0) {
                erros.add("A P2 deve estar entre 0 e 10.")
            }

            if (atividade !in 0.0..2.0) {
                erros.add("A atividade deve estar entre 0 e 2.")
            }

            if (erros.isNotEmpty()) {
                txtResultado.text = erros.joinToString("\n")
                return@setOnClickListener
            }

            val resultado = calculadora.calcularMedia(
                p1 = p1,
                p2 = p2,
                atividade = atividade
            )

            txtResultado.text = "Média: %.1f".format(resultado)
        }
    }
}
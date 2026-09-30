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

        val calculadora = CalculadoraNotas()

        btnCalcular.setOnClickListener {

            val textoP1 = edtP1.text.toString().trim()
            val textoP2 = edtP2.text.toString().trim()
            val textoAtividade = edtAtividade.text.toString().trim()

            if (textoP1.isEmpty() || textoAtividade.isEmpty()) {
                txtResultado.text = "Preencha P1 e Atividade."
                return@setOnClickListener
            }

            val p1 = textoP1.replace(",", ".").toDoubleOrNull()
            val atividade = textoAtividade.replace(",", ".").toDoubleOrNull()

            if (p1 == null || atividade == null) {
                txtResultado.text = "Digite valores numéricos válidos."
                return@setOnClickListener
            }

            val erros = mutableListOf<String>()

            if (p1 !in 0.0..10.0) {
                erros.add("A P1 deve estar entre 0 e 10.")
            }

            if (atividade !in 0.0..2.0) {
                erros.add("A atividade deve estar entre 0 e 2.")
            }

            if (erros.isNotEmpty()) {
                txtResultado.text = erros.joinToString("\n")
                return@setOnClickListener
            }

            val mediaAlvo = 5.0

            if (textoP2.isEmpty()) {

                val mediaParcial = (p1 * 0.4) + atividade

                if (mediaParcial >= mediaAlvo) {
                    txtResultado.text = "Você já atingiu a média necessária!"
                    return@setOnClickListener
                }

                val notaNecessaria = calculadora.calcularNotaNecessariaP2(
                    p1 = p1,
                    atividade = atividade,
                    mediaAlvo = mediaAlvo
                )

                if (notaNecessaria > 10.0) {
                    txtResultado.text = "Não é possível atingir a média necessária."
                } else {
                    txtResultado.text = "Você precisa de %.1f na P2".format(notaNecessaria)
                }

            } else {

                val p2 = textoP2.replace(",", ".").toDoubleOrNull()

                if (p2 == null) {
                    txtResultado.text = "Digite uma P2 válida."
                    return@setOnClickListener
                }

                if (p2 !in 0.0..10.0) {
                    txtResultado.text = "A P2 deve estar entre 0 e 10."
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
}
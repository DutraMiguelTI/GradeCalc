package com.miguel.gradecalc

class CalcularNotas {

    fun calcularMedia(
        p1: Double,
        p2: Double,
        atividade: Double
    ): Double {

        return (p1 * 0.4) + (p2 * 0.4) + atividade
    }
}

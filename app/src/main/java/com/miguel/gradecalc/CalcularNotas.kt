package com.miguel.gradecalc

class CalculadoraNotas {

    fun calcularMedia(
        p1: Double,
        p2: Double,
        atividade: Double
    ): Double {
        return (p1 * 0.4) + (p2 * 0.4) + atividade
    }

    fun calcularNotaNecessariaP2(
        p1: Double,
        atividade: Double,
        mediaAlvo: Double
    ): Double {
        return (mediaAlvo - (p1 * 0.4) - atividade) / 0.4
    }
}

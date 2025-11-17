package com.neotestdev.textexpert.engine

class InferenceEngine(private val rules: List<Rule>) {

    fun infer(text: String): List<String> {
        val results = rules
            .filter { it.condition(text) }
            .map { it.conclusion(text) }

        return if (results.isEmpty()) {
            // Mensaje de felicitación si no se cumple ninguna regla
            listOf("¡Excelente! No se detectaron problemas en tu texto.")
        } else {
            results
        }
    }
}

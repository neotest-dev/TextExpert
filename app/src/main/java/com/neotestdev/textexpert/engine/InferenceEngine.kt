package com.neotestdev.textexpert.engine

// Motor de inferencia del sistema experto
class InferenceEngine(private val rules: List<Rule>) {

    // Aplica las reglas al texto de entrada y devuelve las conclusiones
    fun infer(text: String): List<String> {
        val results = rules
            .filter { it.condition(text) }       // Filtra reglas que cumplen la condición (IF)
            .map { it.conclusion(text) }        // Aplica la conclusión (THEN) de cada regla
        return results.ifEmpty {
            listOf("¡Excelente! No se detectaron problemas en tu texto.") // Mensaje si no hay reglas aplicables
        }
    }
}

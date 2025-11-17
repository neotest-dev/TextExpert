package com.neotestdev.textexpert.engine

class InferenceEngine(private val rules: List<Rule>) {

    fun infer(text: String): List<String> {
        return rules
            .filter { it.condition(text) }
            .map { it.conclusion }
    }
}
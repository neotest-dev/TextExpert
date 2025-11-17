package com.neotestdev.textexpert.engine

// Representa una regla del sistema experto
data class Rule(
    val condition: (String) -> Boolean,  // IF: cuándo se aplica la regla
    val conclusion: (String) -> String   // THEN: resultado si se cumple
)

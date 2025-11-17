package com.neotestdev.textexpert.engine

data class Rule(
    val condition: (String) -> Boolean,
    val conclusion: String
)
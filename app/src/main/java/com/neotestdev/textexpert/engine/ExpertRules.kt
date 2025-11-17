package com.neotestdev.textexpert.engine

val expertRules = listOf(

    Rule(
        condition = { it.split(" ").size > 120 },
        conclusion = "El texto es muy largo. Considera dividirlo en párrafos."
    ),

    Rule(
        condition = { !Regex("(además|por tanto|sin embargo)", RegexOption.IGNORE_CASE).containsMatchIn(it) },
        conclusion = "Faltan conectores lógicos que mejoren la coherencia."
    ),

    Rule(
        condition = { Regex("(jaja|xd|bro)", RegexOption.IGNORE_CASE).containsMatchIn(it) },
        conclusion = "El tono es informal. Considera usar un lenguaje más académico."
    ),

    Rule(
        condition = { Regex("[áéíóú]").findAll(it).count() < 3 },
        conclusion = "Hay pocas tildes. Podrían faltar acentos ortográficos."
    ),

    Rule(
        condition = { text ->
            val words = text.lowercase().split(" ")
            words.groupingBy { it }.eachCount().any { it.value > 8 }
        },
        conclusion = "Hay palabras muy repetidas. Usa sinónimos."
    ),

    )
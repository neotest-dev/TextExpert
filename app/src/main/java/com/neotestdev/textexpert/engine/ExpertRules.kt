package com.neotestdev.textexpert.engine

// Lista de reglas del sistema experto
val expertRules = listOf(

    // Detecta si el texto es demasiado largo
    Rule(
        condition = { it.split(" ").size > 120 },
        conclusion = { text ->
            val count = text.split(" ").size
            "El texto tiene $count palabras, lo cual es extenso. " +
                    "Sugerencia: divide las ideas en varios párrafos para facilitar la lectura."
        }
    ),

    // Verifica la ausencia de conectores lógicos
    Rule(
        condition = { !Regex("(además|por tanto|sin embargo)", RegexOption.IGNORE_CASE).containsMatchIn(it) },
        conclusion = {
            "No se encontraron conectores lógicos como 'además', 'por tanto' o 'sin embargo'. " +
                    "Sugerencia: añade conectores para mejorar la coherencia global del texto."
        }
    ),

    // Detecta expresiones informales
    Rule(
        condition = { Regex("(jaja|xd|bro)", RegexOption.IGNORE_CASE).containsMatchIn(it) },
        conclusion = { text ->
            val found = Regex("(jaja|xd|bro)", RegexOption.IGNORE_CASE)
                .findAll(text)
                .joinToString { it.value }

            "Se detectó un tono informal mediante estas expresiones: $found. " +
                    "Sugerencia: reemplaza estas palabras por un lenguaje más formal o académico."
        }
    ),

    // Evalúa la cantidad de tildes para detectar posibles errores ortográficos
    Rule(
        condition = { Regex("[áéíóú]").findAll(it).count() < 3 },
        conclusion = {
            "El texto presenta pocas tildes, lo que sugiere posibles errores ortográficos. " +
                    "Sugerencia: revisa los acentos para asegurar una correcta escritura."
        }
    ),

    // Identifica repetición excesiva de palabras
    Rule(
        condition = { text ->
            val words = text.lowercase().split(Regex("\\s+"))
            words.groupingBy { it }.eachCount().any { it.value > 8 }
        },
        conclusion = { text ->
            val words = text.lowercase().split(Regex("\\s+"))
            val repeated = words.groupingBy { it }.eachCount().filter { it.value > 8 }
            val list = repeated.entries.joinToString { "\"${it.key}\" aparece ${it.value} veces" }

            "Se identificó repetición excesiva de palabras: $list. " +
                    "Sugerencia: utiliza sinónimos para evitar redundancia."
        }
    ),

    // Detecta oraciones demasiado largas
    Rule(
        condition = { it.split(".").any { sentence -> sentence.split(" ").size > 25 } },
        conclusion = { text ->
            val longSentences = text.split(".")
                .filter { it.split(" ").size > 25 }
            val count = longSentences.size

            "Hay $count oraciones que superan las 25 palabras. " +
                    "Sugerencia: divide estas oraciones en unidades más breves para mejorar la claridad."
        }
    ),

    // Identifica el uso de la voz pasiva
    Rule(
        condition = {
            Regex("\\b(es|son|fue|fueron|ha sido|han sido)\\s+\\w+ado\\b", RegexOption.IGNORE_CASE)
                .containsMatchIn(it)
        },
        conclusion = { text ->
            val matches = Regex("\\b(es|son|fue|fueron|ha sido|han sido)\\s+\\w+ado\\b",
                RegexOption.IGNORE_CASE)
                .findAll(text)
                .joinToString { it.value }

            "Se detectó el uso de la voz pasiva en las expresiones: $matches. " +
                    "Sugerencia: transforma estas estructuras en voz activa para lograr un estilo más directo."
        }
    ),

    // Detecta abuso de gerundios
    Rule(
        condition = { Regex("\\b\\w+(ando|iendo)\\b").findAll(it).count() > 5 },
        conclusion = { text ->
            val count = Regex("\\b\\w+(ando|iendo)\\b").findAll(text).count()

            "Se encontró un uso elevado de gerundios ($count). " +
                    "Sugerencia: reduce su presencia para evitar un estilo pesado o monótono."
        }
    ),

    // Detecta muletillas o palabras poco específicas
    Rule(
        condition = { text ->
            val commonWords = listOf("cosa", "algo", "bueno", "entonces")
            val words = text.lowercase().split(Regex("\\s+"))
            words.any { it in commonWords }
        },
        conclusion = { text ->
            val commonWords = listOf("cosa", "algo", "bueno", "entonces")
            val found = text.lowercase()
                .split(Regex("\\s+"))
                .filter { it in commonWords }
                .distinct()

            "Se identificaron palabras poco precisas: $found. " +
                    "Sugerencia: sustituye estas muletillas por expresiones más específicas."
        }
    )
)

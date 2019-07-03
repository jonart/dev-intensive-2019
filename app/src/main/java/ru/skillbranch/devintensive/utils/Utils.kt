package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        return if (fullName.isNullOrBlank()) {
            null to null
        } else {
            val parts: List<String>? = fullName.trim().split(" ")

            val firstName = parts?.getOrNull(0)
            val lastName = parts?.getOrNull(1)
            firstName to lastName
        }
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var word = ""
        val translationMap = mapOf(
            'а' to "a",
            'b' to "б",
            'в' to "v",
            'г' to "g",
            'д' to "d",
            'е' to "e",
            'ё' to "e",
            'ж' to "zh",
            'з' to "z",
            'и' to "i",
            'й' to "i",
            'к' to "k",
            'л' to "l",
            'м' to "m",
            'н' to "n",
            'о' to "o",
            'п' to "p",
            'р' to "r",
            'с' to "s",
            'т' to "t",
            'у' to "u",
            'ф' to "f",
            'х' to "h",
            'ц' to "c",
            'ч' to "ch",
            'ш' to "sh",
            'щ' to "sh",
            'ъ' to "",
            'ы' to "i",
            'ь' to "",
            'э' to "e",
            'ю' to "yu",
            'я' to "ya"
        )

        for (x in 0 until payload.length) {
            if (payload[x] == ' ') {
                word += divider
            } else {
                if (payload[x].isUpperCase()) {
                    if (payload[x].toLowerCase() in translationMap) {
                        val letter = translationMap[payload[x].toLowerCase()].toString()
                        if (letter.length > 1) {
                            word = letter[0].toUpperCase().toString() + letter[1]
                        } else {
                            word += translationMap[payload[x].toLowerCase()].toString().toUpperCase()
                        }
                    } else {
                        word += payload[x].toString()
                    }
                } else if (payload[x] in translationMap) word += translationMap[payload[x]].toString()
                else {
                    word += payload[x].toString()
                }
            }

        }

        return word.replace("  ", " ")
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val name = firstName?.trim()
        val surname = lastName?.trim()
        return when {
            !name.isNullOrEmpty() && !surname.isNullOrEmpty() -> name[0].toUpperCase().toString() + surname[0].toUpperCase().toString()
            !name.isNullOrEmpty() && surname.isNullOrEmpty() -> name[0].toUpperCase().toString()
            name.isNullOrEmpty() && !surname.isNullOrEmpty() -> surname[0].toUpperCase().toString()
            name == "" || surname == "" -> null
            else -> null
        }
    }
}
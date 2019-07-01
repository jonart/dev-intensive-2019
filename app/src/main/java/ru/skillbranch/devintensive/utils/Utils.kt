package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        return if (fullName.isNullOrBlank()) {
            "" to ""
        } else {
            val parts: List<String>? = fullName.split(" ")

            val firstName = parts?.getOrNull(0)
            val lastName = parts?.getOrNull(1)
            firstName to lastName
        }
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var word = ""
        val translationMap = mapOf(
            'а' to "a",'b' to "б",'в' to "v",'г' to "g",'д' to "d",'е' to "e",'ё' to "e",'ж' to "zh",'з' to "z",
            'и' to "i",'й' to "i",'к' to "k",'л' to "l",'м' to "m",'н' to "n",'о' to "o",'п' to "p",'р' to "r",
            'с' to "s",'т' to "t",'у' to "u",'ф' to "f",'х' to "h",'ц' to "c",'ч' to "ch",'ш' to "sh",'щ' to "sh",
            'ъ' to "",'ы' to "i",'ь' to "",'э' to "e",'ю' to "yu",'я' to "ya"
        )

        for (x in 0 until payload.length) {
            if (payload[x] == ' ') {
                word += divider
            }
            if (payload[x].isUpperCase()) {
                if (payload[x].toLowerCase() in translationMap)
                    word += translationMap[payload[x].toLowerCase()].toString().toUpperCase()
            } else {
                if (payload[x] in translationMap)
                    word += translationMap[payload[x]].toString()
            }
        }

        return word
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val name = firstName?.trim()
        val surname = lastName?.trim()
        return if (!name.isNullOrEmpty() && !surname.isNullOrEmpty()) {
            name[0].toUpperCase().toString() + surname[0].toUpperCase().toString()
        } else if (!name.isNullOrEmpty() && surname.isNullOrEmpty()) {
            name[0].toUpperCase().toString()
        } else if (name.isNullOrEmpty() && !surname.isNullOrEmpty()) {
            surname[0].toUpperCase().toString()
        } else if (name == "" || surname == "") {
            "null null"
        } else {
            "null"
        }
    }
}
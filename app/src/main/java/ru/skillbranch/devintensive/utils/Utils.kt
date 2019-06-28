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
        return payload.replace(" ", divider)
            .replace("А", "A")
            .replace("Б", "B")
            .replace("В", "V")
            .replace("Г", "G")
            .replace("Д", "D")
            .replace("Е", "E")
            .replace("Ё", "E")
            .replace("Ж", "ZH")
            .replace("З", "Z")
            .replace("И", "I")
            .replace("Й", "I")
            .replace("К", "K")
            .replace("Л", "L")
            .replace("М", "M")
            .replace("Н", "N")
            .replace("О", "O")
            .replace("П", "P")
            .replace("Р", "R")
            .replace("С", "S")
            .replace("Т", "T")
            .replace("У", "U")
            .replace("Ф", "F")
            .replace("Х", "H")
            .replace("Ц", "C")
            .replace("Ч", "CH")
            .replace("Ш", "SH")
            .replace("Щ", "SH")
            .replace("Ъ", "")
            .replace("Ы", "I")
            .replace("Ь", "")
            .replace("Э", "E")
            .replace("Ю", "YU")
            .replace("Я", "YA")
            .replace("а", "a")
            .replace("б", "b")
            .replace("в", "v")
            .replace("г", "g")
            .replace("д", "d")
            .replace("е", "e")
            .replace("ё", "e")
            .replace("ж", "zh")
            .replace("з", "z")
            .replace("и", "i")
            .replace("й", "i")
            .replace("к", "k")
            .replace("л", "l")
            .replace("м", "m")
            .replace("н", "n")
            .replace("о", "o")
            .replace("п", "p")
            .replace("р", "r")
            .replace("с", "s")
            .replace("т", "t")
            .replace("у", "u")
            .replace("ф", "f")
            .replace("х", "h")
            .replace("ц", "c")
            .replace("ч", "ch")
            .replace("ш", "sh")
            .replace("щ", "sh")
            .replace("ъ", "")
            .replace("ы", "i")
            .replace("ь", "")
            .replace("э", "e")
            .replace("ю", "yu")
            .replace("я", "ya")
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
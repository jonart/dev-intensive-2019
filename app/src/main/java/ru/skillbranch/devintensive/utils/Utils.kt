package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?): Pair<String?, String?> {
        //TODO FIX ME!!! Исправить ошибку вывода null
        val parts: List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider:String = " "): String {
        //todo перевести ник в кириллицу
return payload
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        //todo сделать инициалы
   return firstName
    }
}
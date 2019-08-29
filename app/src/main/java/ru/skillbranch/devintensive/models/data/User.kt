package ru.skillbranch.devintensive.models.data


import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = null,
    val isOnline: Boolean = false
) {

    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String) : this(id, "John", "Doe")

    init {
        println(
            "It's ALive!!! \n" +
                    "${if (lastName === "Doe") "His name is $firstName $lastName" else "And his name is $firstName $lastName"}\n"
        )
    }

    fun printMe() = println(
        """
        id: $id
        firstName: $firstName
        lastName: $lastName
        avatar: $avatar
        rating: $rating
        respect: $respect
        lastVisit: $lastVisit
        isOnline: $isOnline
    """.trimIndent()
    )
class Builder{
    private var id: String = "-1"
    private var firstName: String? = null
    private var lastName: String? = null
    private var avatar: String? = null
    private var rating: Int = 0
    private var respect: Int = 0
    private var lastVisit: Date? = null
    private var isOnline: Boolean = false

    fun id(id:String) = apply {
        if (id.isEmpty()){
            id.toInt()
        }
        this.id = id }
    fun firstName(firstName:String?) = apply { this.firstName = firstName }
    fun lastName(lastName:String?) = apply { this.lastName = lastName }
    fun avatar(avatar:String?) = apply { this.avatar = avatar }
    fun rating(rating:Int = 0) = apply { this.rating = rating }
    fun respect(respect:Int = 0) = apply { this.respect = respect }
    fun lastVisit(lastVisit:Date? = Date()) = apply { this.lastVisit = lastVisit }
    fun isOnline(isOnline:Boolean = false) = apply { this.isOnline = isOnline }
    fun build() = User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar,
        rating = rating,
        respect = respect,
        lastVisit = lastVisit,
        isOnline = isOnline
    )
}
    companion object Factory {
        var lastId = -1
        fun makeUser(fullName: String?): User {
            lastId++

            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(
                id = "$lastId",
                firstName = firstName,
                lastName = lastName
            )
        }
    }
}
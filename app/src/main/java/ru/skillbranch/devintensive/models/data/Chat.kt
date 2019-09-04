package ru.skillbranch.devintensive.models.data

import androidx.annotation.VisibleForTesting
import ru.skillbranch.devintensive.extensions.shortFormat
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.repositories.ChatRepository
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class Chat(
    val id: String,
    val title: String,
    val members: List<User> = listOf(),
    var messages: MutableList<BaseMessage> = mutableListOf(),
    var isArchived: Boolean = false
) {
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun unreadableMessageCount(): Int {
        var count = 0
        messages.forEach { if (!it.isReaded) count++ }
        return count
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun lastMessageDate(): Date? {
        return messages.lastOrNull()?.date
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun lastMessageShort(): Pair<String, String?> = when (val lastMessage = messages.lastOrNull()) {
        null -> "Сообщений ещё нет" to null
        else -> lastMessage.getShortMessage() to lastMessage.getAuthorName()
    }

    private fun isSingle(): Boolean = members.size == 1

    fun toChatItem(): ChatItem {
        return when {
            id == ChatRepository.ARCHIVE_ITEM_ID -> ChatItem(
                id,
                null,
                "??",
                "title",
                "shortDesc",
                100,
                "yesterday",
                false,
                ChatType.ARCHIVE,
                "author"
            )
            isSingle() -> {
                val user = members.first()
                ChatItem(
                    id,
                    user.avatar,
                    Utils.toInitials(user.firstName, user.lastName) ?: "??",
                    "${user.firstName.orEmpty()} ${user.lastName.orEmpty()}",
                    lastMessageShort().first,
                    unreadableMessageCount(),
                    lastMessageDate()?.shortFormat(),
                    user.isOnline
                )
            }
            else -> ChatItem(
                id,
                null,
                "",
                title,
                lastMessageShort().first,
                unreadableMessageCount(),
                lastMessageDate()?.shortFormat(),
                false,
                ChatType.GROUP,
                lastMessageShort().second
            )
        }
    }
}

enum class ChatType {
    SINGLE,
    GROUP,
    ARCHIVE
}




package ru.skillbranch.devintensive.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.models.data.ChatItem
import ru.skillbranch.devintensive.repositories.ChatRepository

class MainViewModel: ViewModel() {
    fun getChatData():LiveData<List<ChatItem>> {
        return
    }

    private val chatRepository = ChatRepository
}
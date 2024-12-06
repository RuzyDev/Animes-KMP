package br.com.arcom.autoriza.presentation.util

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.random.Random

class UiMessageManager {
    private val mutex = Mutex()

    private val _messages = MutableStateFlow(emptyList<UiMessage>())

    suspend fun emitMessage(message: UiMessage) {
        mutex.withLock {
            _messages.value = listOf(message)
        }
    }

    suspend fun emitMessage(e: Throwable, confirmMsg: Boolean = false) {
        mutex.withLock {
            _messages.value =
                listOf(UiMessage(e.message ?: "Ocorreu um erro!", confirmMsg = confirmMsg))
        }
    }

    fun setMessage(message: UiMessage) {
        _messages.value = listOf(message)
    }

    suspend fun clearMessage(id: Long) {
        mutex.withLock {
            _messages.value = _messages.value.filterNot { it.id == id }
        }
    }

    suspend fun clearAll() {
        mutex.withLock {
            _messages.value = emptyList()
        }
    }

    val observable = _messages.map { it.distinct().firstOrNull() }
}

enum class MessageType {
    INFO, WARN, ERROR
}

data class UiMessage(
    val message: String = "Ocorreu um erro!",
    val id: Long = Random.nextLong(),
    val type: MessageType = MessageType.INFO,
    val confirmMsg: Boolean = false
)
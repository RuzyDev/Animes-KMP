package br.com.arcom.apparcom.presentation

import kotlinx.coroutines.CoroutineScope

expect abstract class CoroutineViewModel() {
    val coroutineScope: CoroutineScope

    fun dispose()

    protected open fun onCleared()
}
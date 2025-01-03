package br.com.arcom.apparcom.ui.designsystem.components.text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

//@Composable
//fun <T> FieldRemember() {
//    var value: T? by remember { mutableStateOf(null) }
//    var error: Boolean by remember { mutableStateOf(false) }
//}

@Composable
fun rememberFieldString(initialValue: String = ""): MutableFieldStringRemember {
    var value: String by remember { mutableStateOf(initialValue) }
    var error: Boolean by remember { mutableStateOf(false) }

    return MutableFieldStringRemember(
        value,
        error,
        { value = it },
        { error = it }
    )
}

interface FieldStringRemember {
    val value: String
    val error: Boolean

    fun setValue(value: String)
    fun setError(value: Boolean)
    fun checkIsEmpty(): Boolean
    fun asLong(): Boolean

}

class MutableFieldStringRemember(
    override val value: String,
    override val error: Boolean,
    private val emitValue: (String) -> Unit,
    private val emitError: (Boolean) -> Unit,
) : FieldStringRemember {
    override fun setValue(value: String) {
        emitValue(value)
    }

    override fun setError(value: Boolean) {
        emitError(value)
    }

    override fun checkIsEmpty(): Boolean {
        val isEmpty = value.isEmpty()
        emitError(isEmpty)

        return isEmpty
    }

    override fun asLong(): Boolean {
        val asLong = value.toLongOrNull()
        if (asLong == null) emitError(true)
        return asLong != null
    }

}

package br.com.arcom.apparcom.model

import database.UsuarioEntity
import kotlinx.datetime.LocalDateTime

data class Usuario(
  val id: Long,
  val nome: String,
  val dataBloqueio: LocalDateTime?
)

fun UsuarioEntity.toExternalModel() = Usuario(
  id = id,
  nome = nome,
  dataBloqueio = data_bloqueio?.let { LocalDateTime.parse(it) }
)
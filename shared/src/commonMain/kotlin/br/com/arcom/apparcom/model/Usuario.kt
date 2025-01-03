package br.com.arcom.apparcom.model

import br.com.arcom.apparcom.db.solicitacao.UsuarioEntity
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
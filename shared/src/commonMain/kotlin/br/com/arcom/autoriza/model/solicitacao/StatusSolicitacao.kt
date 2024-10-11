package br.com.arcom.autoriza.model.solicitacao

enum class StatusSolicitacao(val descricao: String, val value: String) {
    APROVADO("Aprovado", "aprovado"),
    NEGADO("Negado", "negado"),
    AGUARDANDO_REPOSTA("Aguardando reposta", "aguardando-reposta"),
    PENDENTE("Pendente", "pendente"),
    DESCONHECIDO("Desconhecido", "desconhecido");

    companion object {
        fun getByValue(value: String) = entries.firstOrNull { it.value == value } ?: DESCONHECIDO
    }
}
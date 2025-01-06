package br.com.arcom.apparcom.model.solicitacao;

enum class TipoSolicitacao(val descricao: String, val value: String) {
    OCS("Validação OCS", "validacao-ocs"),
    PEDIDO_COMPRA("Validação pedido compra", "validacao-pedido-compra"),
    PEDIDO_VENDA("Validação pedido venda", "validacao-pedido-venda"),
    ROTEIRO("Validação de roteiro", "validacao-roteiro"),
    TODOS("Todos", "todos");

    companion object {
        fun getByValue(value: String) = entries.firstOrNull { it.value == value } ?: TODOS
    }
}


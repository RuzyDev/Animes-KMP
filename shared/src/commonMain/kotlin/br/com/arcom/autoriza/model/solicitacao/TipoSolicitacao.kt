package br.com.arcom.autoriza.model.solicitacao

enum class TipoSolicitacao(val descricao: String, val value: String){
    OCS( "Validação OCS", "validacao-ocs" ),
    PEDIDO_COMPRA( "Validação pedido compra", "validacao-pedido-compra" ),
    PEDIDO_VENDA( "Validação pedido venda", "validacao-pedido-venda" ),
    COMISSAO_MOTORISTA( "Validação comissão motorista", "validacao-comissao-motorista" ),
    DESCONHECIDO("Desconhecido", "desconhecido");

    companion object {
        fun getByValue(value: String) = entries.firstOrNull { it.value == value } ?: DESCONHECIDO
    }
}
package br.com.arcom.apparcom.network.service

import br.com.arcom.apparcom.network.models.NetworkSolicitacaoAceite

interface SolicitacaoService {
      suspend fun buscarSolicitacoes(idUsuario: Long, nroPagina: Short): List<NetworkSolicitacaoAceite>?
      suspend fun registrarSolicitacao(solicitacao: NetworkSolicitacaoAceite, idUsuario: Long)
}

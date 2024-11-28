package br.com.arcom.autoriza.network.service

import br.com.arcom.autoriza.network.models.NetworkSolicitacaoAceite

interface SolicitacaoService {
      suspend fun buscarSolicitacoes(idUsuario: Long, nroPagina: Short): List<NetworkSolicitacaoAceite>?
      suspend fun registrarSolicitacao(solicitacao: NetworkSolicitacaoAceite)
}

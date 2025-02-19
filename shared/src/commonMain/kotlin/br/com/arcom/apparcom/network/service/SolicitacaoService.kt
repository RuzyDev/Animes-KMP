package br.com.arcom.apparcom.network.service

import br.com.arcom.apparcom.network.models.NetworkSolicitacaoAceite
import br.com.arcom.apparcom.network.models.NetworkSolicitacaoAceitePaginado

interface SolicitacaoService {
      suspend fun buscarSolicitacoes(idUsuario: Long, nroPagina: Long): NetworkSolicitacaoAceitePaginado?
      suspend fun registrarSolicitacao(solicitacao: NetworkSolicitacaoAceite, idUsuario: Long)
}

package br.com.arcom.apparcom.network.service

import br.com.arcom.apparcom.model.solicitacao.TipoSolicitacao
import br.com.arcom.apparcom.network.models.NetworkSolicitacaoAceite
import br.com.arcom.apparcom.network.models.NetworkSolicitacaoAceitePaginado

interface SolicitacaoService {
      suspend fun buscarSolicitacoes(idUsuario: Long, nroPagina: Long, tipoSolicitacao: TipoSolicitacao?): NetworkSolicitacaoAceitePaginado?
      suspend fun registrarSolicitacao(solicitacao: NetworkSolicitacaoAceite, idUsuario: Long)
}

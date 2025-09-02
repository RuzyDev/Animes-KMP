package br.com.arcom.apparcom.network.service

import br.com.arcom.apparcom.model.FilterTopAnimes
import br.com.arcom.apparcom.model.solicitacao.TipoSolicitacao
import br.com.arcom.apparcom.network.models.NetworkAnime
import br.com.arcom.apparcom.network.models.NetworkAnimeWithPagination
import br.com.arcom.apparcom.network.models.NetworkGenre
import br.com.arcom.apparcom.network.models.NetworkSolicitacaoAceite
import br.com.arcom.apparcom.network.models.NetworkSolicitacaoAceitePaginado

interface AnimeService {
      suspend fun getGenres(): List<NetworkGenre>
      suspend fun getTopAnimes(filter: FilterTopAnimes, page: Long): NetworkAnimeWithPagination?
}

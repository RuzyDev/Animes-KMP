package br.com.arcom.apparcom.domain.observers

import br.com.arcom.apparcom.domain.SubjectInteractor
import br.com.arcom.apparcom.domain.repository.AnimeRepository
import br.com.arcom.apparcom.domain.repository.SolicitacaoAceiteRepository
import br.com.arcom.apparcom.model.FilterTopAnimes
import br.com.arcom.apparcom.model.solicitacao.SolicitacaoAceite
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ObserveTopAnimes(
    private val animeRepository: AnimeRepository
) : SubjectInteractor<ObserveTopAnimes.Params, SolicitacaoAceite>() {

    data class Params(
        val filter: FilterTopAnimes,
        val page: Long
    )

    override fun createObservable(params: Params): Flow<SolicitacaoAceite> {
        return flowOf();
    }
}

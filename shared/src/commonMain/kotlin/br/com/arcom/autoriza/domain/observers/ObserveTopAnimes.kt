package br.com.arcom.autoriza.domain.observers

import br.com.arcom.autoriza.domain.SubjectInteractor
import br.com.arcom.autoriza.domain.repository.TopRepository
import br.com.arcom.autoriza.model.anime.AnimeDetails
import br.com.arcom.autoriza.model.ranking.TypeRakingAnime
import kotlinx.coroutines.flow.Flow

class ObserveTopAnimes(
    private val topRepository: TopRepository
) : SubjectInteractor<ObserveTopAnimes.Params, List<AnimeDetails>>() {

    data class Params(
        val typeRakingAnime: TypeRakingAnime,
        val page: Long = 0
    )

    override fun createObservable(params: Params): Flow<List<AnimeDetails>> {
        return topRepository.observeTopAnimes(params.typeRakingAnime, params.page)
    }
}

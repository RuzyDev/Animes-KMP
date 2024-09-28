package br.com.arcom.sign.domain.observers

import br.com.arcom.sign.domain.SubjectInteractor
import br.com.arcom.sign.domain.repository.TopRepository
import br.com.arcom.sign.model.anime.AnimeDetails
import br.com.arcom.sign.model.ranking.TypeRakingAnime
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

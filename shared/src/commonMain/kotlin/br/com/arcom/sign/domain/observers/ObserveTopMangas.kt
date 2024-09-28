package br.com.arcom.sign.domain.observers

import br.com.arcom.sign.domain.SubjectInteractor
import br.com.arcom.sign.domain.repository.TopRepository
import br.com.arcom.sign.model.manga.MangaDetails
import br.com.arcom.sign.model.ranking.TypeRakingManga
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class ObserveTopMangas(
    private val topRepository: TopRepository
) : SubjectInteractor<ObserveTopMangas.Params, List<MangaDetails>>() {

    data class Params(
        val typeRakingManga: TypeRakingManga,
        val page: Long = 0
    )

    override fun createObservable(params: Params): Flow<List<MangaDetails>> {
        return topRepository.observeTopMangas(params.typeRakingManga, params.page)
    }
}

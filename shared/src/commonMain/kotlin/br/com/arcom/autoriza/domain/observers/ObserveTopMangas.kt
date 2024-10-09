package br.com.arcom.autoriza.domain.observers

import br.com.arcom.autoriza.domain.SubjectInteractor
import br.com.arcom.autoriza.domain.repository.TopRepository
import br.com.arcom.autoriza.model.manga.MangaDetails
import br.com.arcom.autoriza.model.ranking.TypeRakingManga
import kotlinx.coroutines.flow.Flow

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

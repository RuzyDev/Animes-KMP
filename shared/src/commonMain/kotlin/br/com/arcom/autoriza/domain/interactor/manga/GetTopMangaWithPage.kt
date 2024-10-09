package br.com.arcom.autoriza.domain.interactor.manga

import br.com.arcom.autoriza.domain.repository.TopRepository
import br.com.arcom.autoriza.model.ranking.TypeRakingManga
import br.com.arcom.autoriza.util.AppCoroutineDispatchers
import kotlinx.coroutines.withContext

class GetTopMangaWithPage(
    private val topRepository: TopRepository,
    private val dispatchers: AppCoroutineDispatchers
) {
    suspend fun invoke(
        typeRakingAnime: TypeRakingManga,
        page: Long
    ) = withContext(dispatchers.io) { topRepository.getTopMangas(typeRakingAnime, page) }
}
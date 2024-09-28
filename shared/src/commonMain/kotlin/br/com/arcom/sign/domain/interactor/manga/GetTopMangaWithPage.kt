package br.com.arcom.sign.domain.interactor.manga

import br.com.arcom.sign.domain.Interactor
import br.com.arcom.sign.domain.repository.TopRepository
import br.com.arcom.sign.model.ranking.TypeRakingAnime
import br.com.arcom.sign.model.ranking.TypeRakingManga
import br.com.arcom.sign.util.AppCoroutineDispatchers
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
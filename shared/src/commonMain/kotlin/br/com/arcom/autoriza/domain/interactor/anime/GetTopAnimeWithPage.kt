package br.com.arcom.autoriza.domain.interactor.anime

import br.com.arcom.autoriza.domain.repository.TopRepository
import br.com.arcom.autoriza.model.ranking.TypeRakingAnime
import br.com.arcom.autoriza.util.AppCoroutineDispatchers
import kotlinx.coroutines.withContext

class GetTopAnimeWithPage(
    private val topRepository: TopRepository,
    private val dispatchers: AppCoroutineDispatchers
) {
    suspend fun invoke(
        typeRakingAnime: TypeRakingAnime,
        page: Long
    ) = withContext(dispatchers.io) { topRepository.getTopAnimes(typeRakingAnime, page) }
}
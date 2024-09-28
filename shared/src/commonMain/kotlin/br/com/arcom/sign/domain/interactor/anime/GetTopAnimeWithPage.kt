package br.com.arcom.sign.domain.interactor.anime

import br.com.arcom.sign.domain.Interactor
import br.com.arcom.sign.domain.repository.TopRepository
import br.com.arcom.sign.model.ranking.TypeRakingAnime
import br.com.arcom.sign.util.AppCoroutineDispatchers
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
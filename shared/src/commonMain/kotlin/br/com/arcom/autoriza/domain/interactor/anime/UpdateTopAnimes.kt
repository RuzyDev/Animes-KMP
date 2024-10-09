package br.com.arcom.autoriza.domain.interactor.anime

import br.com.arcom.autoriza.domain.Interactor
import br.com.arcom.autoriza.domain.repository.TopRepository
import br.com.arcom.autoriza.model.ranking.TypeRakingAnime
import br.com.arcom.autoriza.util.AppCoroutineDispatchers
import kotlinx.coroutines.withContext

class UpdateTopAnimes(
    private val topRepository: TopRepository,
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<UpdateTopAnimes.Params, Unit>() {

    data class Params(
        val typeRakingAnime: TypeRakingAnime
    )

    override suspend fun doWork(params: Params) {
        withContext(dispatchers.io) {
            topRepository.updateTopAnimes(params.typeRakingAnime)
        }
    }
}
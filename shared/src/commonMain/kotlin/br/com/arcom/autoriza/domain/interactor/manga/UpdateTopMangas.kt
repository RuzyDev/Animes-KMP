package br.com.arcom.autoriza.domain.interactor.manga

import br.com.arcom.autoriza.domain.Interactor
import br.com.arcom.autoriza.domain.repository.TopRepository
import br.com.arcom.autoriza.model.ranking.TypeRakingManga
import br.com.arcom.autoriza.util.AppCoroutineDispatchers
import kotlinx.coroutines.withContext

class UpdateTopMangas(
    private val topRepository: TopRepository,
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<UpdateTopMangas.Params, Unit>() {

    data class Params(
        val typeRakingAnime: TypeRakingManga
    )

    override suspend fun doWork(params: Params) {
        withContext(dispatchers.io) {
            topRepository.updateTopMangas(params.typeRakingAnime)
        }
    }
}
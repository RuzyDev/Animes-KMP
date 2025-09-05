package br.com.arcom.apparcom.domain.interactor

import br.com.arcom.apparcom.domain.Interactor
import br.com.arcom.apparcom.domain.repository.AnimeRepository
import br.com.arcom.apparcom.model.FilterTopAnimes
import br.com.arcom.apparcom.util.AppCoroutineDispatchers
import kotlinx.coroutines.withContext

class UpdateTopAnimes(
    private val dispatchers: AppCoroutineDispatchers,
    private val animeRepository: AnimeRepository
) : Interactor<UpdateTopAnimes.Params, Unit>() {

    data class Params(val filter: FilterTopAnimes, val page: Long)

    override suspend fun doWork(params: Params) {
        withContext(dispatchers.io) {
            animeRepository.updateTopAnimes(filter = params.filter, page = params.page)
        }
    }
}
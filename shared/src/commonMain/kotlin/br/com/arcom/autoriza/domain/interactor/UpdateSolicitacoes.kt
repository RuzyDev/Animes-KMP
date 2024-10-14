package br.com.arcom.autoriza.domain.interactor

import br.com.arcom.autoriza.domain.Interactor
import br.com.arcom.autoriza.domain.SubjectInteractor
import br.com.arcom.autoriza.domain.repository.TopRepository
import br.com.arcom.autoriza.model.ranking.TypeRakingAnime
import br.com.arcom.autoriza.util.AppCoroutineDispatchers
import kotlinx.coroutines.withContext

class UpdateSolicitacoes(
    private val topRepository: TopRepository,
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<Unit, Unit>() {

    override suspend fun doWork(params: Unit) {

    }
}
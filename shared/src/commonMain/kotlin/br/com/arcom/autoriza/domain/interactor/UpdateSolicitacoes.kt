package br.com.arcom.autoriza.domain.interactor

import br.com.arcom.autoriza.domain.Interactor
import br.com.arcom.autoriza.util.AppCoroutineDispatchers

class UpdateSolicitacoes(
    private val dispatchers: AppCoroutineDispatchers
) : Interactor<Unit, Unit>() {

    override suspend fun doWork(params: Unit) {

    }
}
package br.com.arcom.autoriza.domain.repository.impl

import br.com.arcom.autoriza.domain.repository.SolicitacaoAceiteRepository
import br.com.arcom.autoriza.model.solicitacao.SolicitacaoAceite
import br.com.arcom.autoriza.network.service.SolicitacaoService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SolicitacaoAceiteRepositoryImpl(
    private val solicitacaoService: SolicitacaoService
) : SolicitacaoAceiteRepository {

    override suspend fun updateSolicitacaoAceite(idUsuario: Long, page: Short) {
        val solicitacoes = solicitacaoService.buscarSolicitacoes(idUsuario, page) ?: emptyList()
        if (solicitacoes.isNotEmpty()){
            solicitacoes.forEach {

            }
        }
    }

    override fun observeSolicitacaoAceite(page: Long): Flow<List<SolicitacaoAceite>> {
//        animeQueries.getTopAnimes(type.type, page).asFlow().mapToList(Dispatchers.IO).map {
//            it.map { anime ->
//                val images = imageAnimeQueries.getByIdAnime(anime.id).executeAsList()
//                    .map(ImageAnimeEntity::toExternalModel)
//                anime.toAnimeDetails(images)
//            }
//        }
        return flow {  }
    }

}

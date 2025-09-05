package br.com.arcom.apparcom.db.dao

import br.com.arcom.apparcom.model.QueryTypeAnime
import br.com.arcom.apparcom.network.models.NetworkAnime
import br.com.arcom.apparcom.network.models.NetworkPagination
import br.com.arcom.apparcom.network.models.NetworkUsuarioAppAnime
import database.AnimeQueryEntity
import database.SelectAnimesByQuery
import database.UsuarioEntity
import kotlinx.coroutines.flow.Flow

interface AnimeQueryDao {
    suspend fun insertOrUpdate(
        pagination: NetworkPagination,
        queryType: QueryTypeAnime,
        queryParam: String
    )

    fun observeAnimeQuery(type: QueryTypeAnime, param: String): Flow<AnimeQueryEntity?>

    fun observeAnimes(type: QueryTypeAnime, param: String): Flow<List<SelectAnimesByQuery>>
}
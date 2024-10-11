package br.com.arcom.autoriza.model.solicitacao

data class SolicitacaoAceite(
    val idSolicitacao: Long,
    val idUsuario: Long,
    val idEmpresa: Long,
    val descricao: String,
    val status: StatusSolicitacao,
    val tipoSolicitacao: TipoSolicitacao
)


//fun GetTopAnimes.toAnimeDetails(listImages: List<ImageAnime>) =
//    AnimeDetails(
//        id = id,
//        title = title ?: "Not found",
//        images = listImages,
//        rank = ranking_value,
//        rank_type = TypeRakingAnime.getType(ranking_type)
//    )
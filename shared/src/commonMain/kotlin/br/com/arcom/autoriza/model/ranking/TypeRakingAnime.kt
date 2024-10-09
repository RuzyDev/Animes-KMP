package br.com.arcom.autoriza.model.ranking

enum class TypeRakingAnime (val type: String){
    AIRING("airing"),
    UPCOMING("upcoming"),
    BY_POPULARITY("bypopularity"),
    FAVORITE("favorite");

    companion object{
        fun getType(type: String?) = entries.firstOrNull{it.type == type}
    }
}
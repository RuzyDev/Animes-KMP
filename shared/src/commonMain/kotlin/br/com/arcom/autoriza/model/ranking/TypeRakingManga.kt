package br.com.arcom.autoriza.model.ranking

enum class  TypeRakingManga (val type: String){
    UPCOMING("upcoming"),
    BY_POPULARITY("bypopularity"),
    FAVORITE("favorite");

    companion object{
        fun getType(type: String?) = TypeRakingManga.entries.firstOrNull{it.type == type}
    }
}
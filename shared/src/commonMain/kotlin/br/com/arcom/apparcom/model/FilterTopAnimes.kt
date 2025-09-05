package br.com.arcom.apparcom.model

enum class FilterTopAnimes(val filter: String?, val param: String){
  AIRING("airing","anime_airing"),
  UPCOMING("upcoming","anime_upcoming"),
  BY_POPULARITY("bypopularity","anime_by_popularity"),
  FAVORITE("favorite","anime_favorite"),
  RANKING(null, "anime_ranking");
}
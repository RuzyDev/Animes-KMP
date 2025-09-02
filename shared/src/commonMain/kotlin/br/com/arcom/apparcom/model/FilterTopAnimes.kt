package br.com.arcom.apparcom.model

enum class FilterTopAnimes(val filter: String){
  AIRING("airing"),
  UPCOMING("upcoming"),
  BY_POPULARITY("bypopularity"),
  FAVORITE("favorite");
}
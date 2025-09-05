package br.com.arcom.apparcom.model

enum class QueryTypeAnime(val type: String){
  TOP("top"),
  UNKNOW("unknow");

  companion object{
    fun get(value: String) = entries.firstOrNull { it.type == value } ?: UNKNOW
  }
}
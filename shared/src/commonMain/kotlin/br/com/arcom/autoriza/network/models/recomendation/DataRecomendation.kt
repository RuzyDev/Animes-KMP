package br.com.arcom.autoriza.network.models.recomendation

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class DataRecomendation (

  @SerialName("mal_id"  ) var malId   : String?          = null,
  @SerialName("entry"   ) var entryRecomendation   : ArrayList<EntryRecomendation> = arrayListOf(),
  @SerialName("content" ) var content : String?          = null,
  @SerialName("date"    ) var date    : String?          = null,
  @SerialName("user"    ) var userRecomendation    : UserRecomendation?            = UserRecomendation()

)
package br.com.arcom.autoriza.network.models.top.manga

import br.com.arcom.autoriza.network.models.top.NetworkTopDemographics
import br.com.arcom.autoriza.network.models.top.NetworkTopGenres
import br.com.arcom.autoriza.network.models.top.NetworkTopImages
import br.com.arcom.autoriza.network.models.top.NetworkTopTitles
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class NetworkTopMangaData (

  @SerialName("mal_id"          ) var malId          : Int?                      = null,
  @SerialName("url"             ) var url            : String?                   = null,
  @SerialName("images"          ) var images         : NetworkTopImages?                   = NetworkTopImages(),
  @SerialName("approved"        ) var approved       : Boolean?                  = null,
  @SerialName("titles"          ) var titles         : ArrayList<NetworkTopTitles>         = arrayListOf(),
  @SerialName("title"           ) var title          : String?                   = null,
  @SerialName("title_english"   ) var titleEnglish   : String?                   = null,
  @SerialName("title_japanese"  ) var titleJapanese  : String?                   = null,
  @SerialName("title_synonyms"  ) var titleSynonyms  : ArrayList<String>         = arrayListOf(),
  @SerialName("type"            ) var type           : String?                   = null,
  @SerialName("chapters"        ) var chapters       : String?                   = null,
  @SerialName("volumes"         ) var volumes        : String?                   = null,
  @SerialName("status"          ) var status         : String?                   = null,
  @SerialName("publishing"      ) var publishing     : Boolean?                  = null,
  @SerialName("published"       ) var networkTopMangaPublished      : NetworkTopMangaPublished?                = NetworkTopMangaPublished(),
  @SerialName("score"           ) var score          : Double?                   = null,
  @SerialName("scored"          ) var scored         : Double?                   = null,
  @SerialName("scored_by"       ) var scoredBy       : Int?                      = null,
  @SerialName("rank"            ) var rank           : Int?                      = null,
  @SerialName("popularity"      ) var popularity     : Int?                      = null,
  @SerialName("members"         ) var members        : Int?                      = null,
  @SerialName("favorites"       ) var favorites      : Int?                      = null,
  @SerialName("synopsis"        ) var synopsis       : String?                   = null,
  @SerialName("background"      ) var background     : String?                   = null,
  @SerialName("authors"         ) var authors        : ArrayList<NetworkTopMangaAuthors>        = arrayListOf(),
  @SerialName("serializations"  ) var serializations : ArrayList<NetworkTopMangaSerializations> = arrayListOf(),
  @SerialName("genres"          ) var genres         : ArrayList<NetworkTopGenres>         = arrayListOf(),
  @SerialName("explicit_genres" ) var explicitGenres : ArrayList<String>         = arrayListOf(),
  @SerialName("demographics"    ) var demographics   : ArrayList<NetworkTopDemographics>   = arrayListOf()

)
package br.com.arcom.autoriza.network.models.top.anime

import br.com.arcom.autoriza.network.models.top.NetworkTopDemographics
import br.com.arcom.autoriza.network.models.top.NetworkTopGenres
import br.com.arcom.autoriza.network.models.top.NetworkTopImages
import br.com.arcom.autoriza.network.models.top.NetworkTopTitles
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NetworkTopAnimeData (

    @SerialName("mal_id"          ) var malId          : Long?                    = null,
    @SerialName("url"             ) var url            : String?                 = null,
    @SerialName("images"          ) var networkAnimeImagesTop         : NetworkTopImages?                 = NetworkTopImages(),
    @SerialName("trailer"         ) var networkTopAnimeTrailer        : NetworkTopAnimeTrailer?                = NetworkTopAnimeTrailer(),
    @SerialName("approved"        ) var approved       : Boolean?                = null,
    @SerialName("titles"          ) var titles         : ArrayList<NetworkTopTitles>       = arrayListOf(),
    @SerialName("title"           ) var title          : String?                 = null,
    @SerialName("title_english"   ) var titleEnglish   : String?                 = null,
    @SerialName("title_japanese"  ) var titleJapanese  : String?                 = null,
    @SerialName("title_synonyms"  ) var titleSynonyms  : ArrayList<String>       = arrayListOf(),
    @SerialName("type"            ) var type           : String?                 = null,
    @SerialName("source"          ) var source         : String?                 = null,
    @SerialName("episodes"        ) var episodes       : Long?                    = null,
    @SerialName("status"          ) var status         : String?                 = null,
    @SerialName("airing"          ) var airing         : Boolean?                = null,
    @SerialName("aired"           ) var netwotkTopAnimeAired          : NetwotkTopAnimeAired?                  = NetwotkTopAnimeAired(),
    @SerialName("duration"        ) var duration       : String?                 = null,
    @SerialName("rating"          ) var rating         : String?                 = null,
    @SerialName("score"           ) var score          : Double?                 = null,
    @SerialName("scored_by"       ) var scoredBy       : Long?                    = null,
    @SerialName("rank"            ) var rank           : Long?                    = null,
    @SerialName("popularity"      ) var popularity     : Long?                    = null,
    @SerialName("members"         ) var members        : Long?                    = null,
    @SerialName("favorites"       ) var favorites      : Long?                    = null,
    @SerialName("synopsis"        ) var synopsis       : String?                 = null,
    @SerialName("background"      ) var background     : String?                 = null,
    @SerialName("season"          ) var season         : String?                 = null,
    @SerialName("year"            ) var year           : Long?                    = null,
    @SerialName("broadcast"       ) var networkTopAnimeBroadcast      : NetworkTopAnimeBroadcast?              = NetworkTopAnimeBroadcast(),
    @SerialName("producers"       ) var producers      : ArrayList<NetworkTopAnimeProducers>    = arrayListOf(),
    @SerialName("studios"         ) var studios        : ArrayList<NetworkTopAnimeStudios>      = arrayListOf(),
    @SerialName("genres"          ) var genres         : ArrayList<NetworkTopGenres>       = arrayListOf(),
    @SerialName("explicit_genres" ) var explicitGenres : ArrayList<String>       = arrayListOf(),
    @SerialName("demographics"    ) var demographics   : ArrayList<NetworkTopDemographics> = arrayListOf()

)
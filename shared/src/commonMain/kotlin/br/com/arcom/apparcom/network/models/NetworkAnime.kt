package br.com.arcom.apparcom.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkAnime (
    @SerialName("mal_id"          ) val malId          : Long?                    = null,
    @SerialName("url"             ) val url            : String?                 = null,
    @SerialName("approved"        ) val approved       : Boolean?                = null,
    @SerialName("title"           ) val title          : String?                 = null,
    @SerialName("title_english"   ) val titleEnglish   : String?                 = null,
    @SerialName("title_japanese"  ) val titleJapanese  : String?                 = null,
    @SerialName("type"            ) val type           : String?                 = null,
    @SerialName("source"          ) val source         : String?                 = null,
    @SerialName("episodes"        ) val episodes       : Long?                    = null,
    @SerialName("status"          ) val status         : String?                 = null,
    @SerialName("airing"          ) val airing         : Boolean?                = null,
    @SerialName("duration"        ) val duration       : String?                 = null,
    @SerialName("rating"          ) val rating         : String?                 = null,
    @SerialName("score"           ) val score          : Double?                 = null,
    @SerialName("scored_by"       ) val scoredBy       : Long?                    = null,
    @SerialName("rank"            ) val rank           : Long?                    = null,
    @SerialName("popularity"      ) val popularity     : Long?                    = null,
    @SerialName("members"         ) val members        : Long?                    = null,
    @SerialName("favorites"       ) val favorites      : Long?                    = null,
    @SerialName("synopsis"        ) val synopsis       : String?                 = null,
    @SerialName("background"      ) val background     : String?                 = null,
    @SerialName("season"          ) val season         : String?                 = null,
    @SerialName("year"            ) val year           : String?                 = null
)

@Serializable
data class Images (
    @SerialName("jpg"  ) val jpg  : Jpg?  = Jpg()
)

@Serializable
data class Jpg (
    @SerialName("image_url"       ) val imageUrl      : String? = null,
    @SerialName("small_image_url" ) val smallImageUrl : String? = null,
    @SerialName("large_image_url" ) val largeImageUrl : String? = null
)
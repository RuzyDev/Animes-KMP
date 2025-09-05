package br.com.arcom.apparcom.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkAnime (
    @SerialName("mal_id"         ) val malId          : Long?                   = null,
    @SerialName("url"            ) val url            : String?                 = null,
    @SerialName("approved"       ) val approved       : Boolean?                = null,
    @SerialName("title"          ) val title          : String?                 = null,
    @SerialName("title_english"  ) val titleEnglish   : String?                 = null,
    @SerialName("title_japanese" ) val titleJapanese  : String?                 = null,
    @SerialName("type"           ) val type           : String?                 = null,
    @SerialName("source"         ) val source         : String?                 = null,
    @SerialName("episodes"       ) val episodes       : Long?                   = null,
    @SerialName("status"         ) val status         : String?                 = null,
    @SerialName("airing"         ) val airing         : Boolean?                = null,
    @SerialName("duration"       ) val duration       : String?                 = null,
    @SerialName("rating"         ) val rating         : String?                 = null,
    @SerialName("score"          ) val score          : Double?                 = null,
    @SerialName("scored_by"      ) val scoredBy       : Long?                   = null,
    @SerialName("rank"           ) val rank           : Long?                   = null,
    @SerialName("popularity"     ) val popularity     : Long?                   = null,
    @SerialName("members"        ) val members        : Long?                   = null,
    @SerialName("favorites"      ) val favorites      : Long?                   = null,
    @SerialName("synopsis"       ) val synopsis       : String?                 = null,
    @SerialName("background"     ) val background     : String?                 = null,
    @SerialName("season"         ) val season         : String?                 = null,
    @SerialName("year"           ) val year           : String?                 = null,
    @SerialName("images"         ) val images         : TypeImages?             = null,
    @SerialName("producers"      ) val producers      : List<Details>           = emptyList(),
    @SerialName("studios"        ) val studios        : List<Details>           = emptyList(),
    @SerialName("genres"         ) val genres         : List<Details>           = emptyList(),
    @SerialName("trailer"        ) val trailer        : Trailer                 = Trailer(),
    @SerialName("aired"          ) val aired          : Aired                   = Aired()
)

@Serializable
data class TypeImages (
    @SerialName("jpg"  ) val jpg  : Images?  = Images()
)

@Serializable
data class Images (
    @SerialName("image_url"        ) val imageUrl        : String? = null,
    @SerialName("small_image_url"  ) val smallImageUrl   : String? = null,
    @SerialName("medium_image_url" ) val mediumImageUrl : String? = null,
    @SerialName("large_image_url"  ) val largeImageUrl   : String? = null,
    @SerialName("maximum_image_url") val maximumImageUrl : String? = null
)

@Serializable
data class Trailer (
    @SerialName("youtube_id" ) val youtubeId : String? = null,
    @SerialName("url"        ) val url       : String? = null,
    @SerialName("embed_url"  ) val embedUrl  : String? = null,
    @SerialName("images"     ) val images    : Images? = Images()
)

@Serializable
data class Aired (
    @SerialName("from"   ) val from   : String? = null,
    @SerialName("to"     ) val to     : String? = null,
    @SerialName("string" ) val string : String? = null
)

@Serializable
data class Prop (
    @SerialName("from" ) val from : Date? = Date(),
    @SerialName("to"   ) val to   : Date? = Date()
)

@Serializable
data class Date (
    @SerialName("day"   ) val day   : Int? = null,
    @SerialName("month" ) val month : Int? = null,
    @SerialName("year"  ) val year  : Int? = null
)


@Serializable
data class Details (
    @SerialName("mal_id" ) var malId : Int?    = null,
    @SerialName("type"   ) var type  : String? = null,
    @SerialName("name"   ) var name  : String? = null,
    @SerialName("url"    ) var url   : String? = null
)
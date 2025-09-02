package br.com.arcom.apparcom.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkGenre (
    @SerialName("mal_id"          ) val malId          : Int?                    = null,
    @SerialName("name"            ) val name           : String?                 = null,
    @SerialName("url"             ) val url            : String?                 = null,
    @SerialName("count"           ) val count          : Int?                    = null
)

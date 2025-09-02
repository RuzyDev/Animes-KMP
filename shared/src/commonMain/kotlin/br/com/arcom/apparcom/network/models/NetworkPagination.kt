package br.com.arcom.apparcom.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPagination (
    @SerialName("last_visible_page" ) val lastVisiblePage : Long?     = null,
    @SerialName("has_next_page"     ) val hasNextPage     : Boolean? = null,
    @SerialName("current_page"      ) val currentPage     : Long?     = null,
    @SerialName("items"             ) val items           : Items?   = Items()
)

@Serializable
data class Items (
    @SerialName("count"    ) val count   : Long? = null,
    @SerialName("total"    ) val total   : Long? = null,
    @SerialName("per_page" ) val perPage : Long? = null
)
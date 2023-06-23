package com.example.youtube_6month.data.remote.model

data class Videos(
    val items: List<Items>,
)

data class Items(
    val contentDetails: ContentDetails,
) {
    data class ContentDetails(
        val duration: String,
    )
}

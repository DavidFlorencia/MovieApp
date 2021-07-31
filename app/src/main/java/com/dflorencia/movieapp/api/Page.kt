package com.dflorencia.movieapp.api

import com.squareup.moshi.Json

class Page {
    @Json(name = "page")
    var page: Int? = null

    @Json(name = "results")
    var items: List<Item>? = null

    @Json(name = "total_pages")
    var totalPages: Int? = null

    @Json(name = "total_results")
    var totalResults: Int? = null
}
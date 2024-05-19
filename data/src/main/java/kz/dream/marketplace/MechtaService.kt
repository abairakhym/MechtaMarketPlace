package kz.dream.marketplace

import kz.dream.marketplace.model.MechtaNetworkDataSource
import retrofit2.http.GET
import retrofit2.http.Query

interface MechtaService {

    @GET("catalog")
    suspend fun getCatalogFromApi(
        @Query("page") page: Int,
        @Query("gender") page_limit: Int = 24,
        @Query("section") section: String="smartfony"
    ): MechtaNetworkDataSource
}
package ma.ac.ensas.project_news.api

import ma.ac.ensas.project_news.models.NewsResponse
import ma.ac.ensas.project_news.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ):Response<NewsResponse> //Return a response use to handle http responses

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q") // Represnt the key word
        searchQuery: String,
        @Query("page")
        pageNumber: Int =1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ):Response<NewsResponse>

}
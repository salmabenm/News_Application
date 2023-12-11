package ma.ac.ensas.project_news.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ma.ac.ensas.project_news.models.NewsResponse
import ma.ac.ensas.project_news.repository.NewsRepository
import ma.ac.ensas.project_news.util.Resource
import retrofit2.Response

class NewsViewModel(app: Application,val newsRepository: NewsRepository): AndroidViewModel(app) {

    val headlines: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var headlinesPage = 1
    var headlinesResponse: NewsResponse? = null

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse: NewsResponse? = null
    var newSearchQuery: String? = null //to store search query
    var oldSearchQuery: String? = null //to store old search query var

    private fun handleHeadlinesResponse(response: Response<NewsResponse>): Resource<NewsResponse>{
        if(response.isSuccessful){
            response.body()?.let{ resultResponse ->
                headlinesPage++
                if(headlinesResponse == null){
                    headlinesResponse = resultResponse
                } else {
                    val oldArticles = headlinesResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(headlinesResponse ?:resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse>{
        if(response.isSuccessful){
            response.body()?.let{ resultResponse ->
                if(searchNewsResponse == null || newSearchQuery != oldSearchQuery){
                    searchNewsPage = 1
                    oldSearchQuery = newSearchQuery
                    searchNewsResponse = resultResponse
                }else{
                    searchNewsPage++
                    val oldArticles = searchNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(searchNewsResponse ?: resultResponse)
            }
        }
        return  Resource.Error(response.message())
    }
}
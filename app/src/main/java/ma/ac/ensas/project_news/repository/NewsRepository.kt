package ma.ac.ensas.project_news.repository

import androidx.room.Query
import ma.ac.ensas.project_news.api.RetrofitInstance
import ma.ac.ensas.project_news.db.ArticleDatabase
import ma.ac.ensas.project_news.models.Article

class NewsRepository(val db:ArticleDatabase) {

    suspend fun getHeadlines(countryCode: String, pageNumber: Int)=
        RetrofitInstance.api.getHeadlines(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int)=
        RetrofitInstance.api.searchForNews(searchQuery,pageNumber)

    suspend fun upsert(article: Article)= db.getArticleDao().upsert(article)

    fun getFavouriteNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)

}
package ma.ac.ensas.project_news.models

import ma.ac.ensas.project_news.models.Article

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)
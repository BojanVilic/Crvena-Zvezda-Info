package com.bojanvilic.crvenazvezdainfo.repositories

import com.bojanvilic.crvenazvezdainfo.data.api.ArticleWebService
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleDao
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    articleDao: ArticleDao,
    articleWebService: ArticleWebService
) {
}
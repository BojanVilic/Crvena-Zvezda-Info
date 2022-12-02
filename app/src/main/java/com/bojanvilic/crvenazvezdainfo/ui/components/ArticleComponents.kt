package com.bojanvilic.crvenazvezdainfo.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.bojanvilic.crvenazvezdainfo.R
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.bojanvilic.crvenazvezdainfo.theme.AppTheme
import com.bojanvilic.crvenazvezdainfo.ui.ArticlesViewModel
import com.bojanvilic.crvenazvezdainfo.util.Resource
import com.bojanvilic.crvenazvezdainfo.util.Status
import com.bojanvilic.crvenazvezdainfo.util.toHtmlString

@Composable
fun ArticlesScreen(
    articlesViewModel: ArticlesViewModel = hiltViewModel(),
    onArticleClicked: (Int) -> Unit
) {
    val articles = articlesViewModel.articlesList.collectAsState(initial = Resource.success(listOf()))
    val currentPage = articlesViewModel.pager.collectAsState()

    Box {
        LazyColumn {
            items(count = articles.value.data.size) { index ->
                if (index == 0) {
                    HeadlineArticle(
                        articleUiState = articles.value.data[index],
                        onArticleClicked = {
                            onArticleClicked(it)
                        })
                } else if (index == articles.value.data.size-1) {
                    if (articles.value.status != Status.LOADING) {
                        articlesViewModel.fetchNextPage()
                    }
                } else {
                    ArticleContent(
                        articles.value.data[index],
                        onArticleClicked = {
                            onArticleClicked(it)
                        }
                    )
                }
            }
            item {
                if (articles.value.status == Status.LOADING && currentPage.value > 1) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }

        if (articles.value.status == Status.LOADING && currentPage.value == 1) {
            Snackbar(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.BottomCenter)
            ) { Text(text = stringResource(id = R.string.label_refreshing)) }
        }
    }

}

@Composable
fun HeadlineArticle(
    articleUiState: ArticleModelRoom,
    onArticleClicked: (Int) -> Unit
) {
    Surface {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    articleUiState.id?.let {
                        onArticleClicked(it)
                    }
                }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    modifier = Modifier
                        .heightIn(min = 180.dp, max = 200.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillWidth,
                    model = ImageRequest.Builder(LocalContext.current)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .data(articleUiState.imageUrl)
                        .build(),
                    imageLoader = LocalContext.current.imageLoader,
                    error = painterResource(id = R.drawable.ic_broken_image),
                    contentDescription = null
                )
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge,
                        text = articleUiState.title.toHtmlString()
                    )
                    TimeAndCategorySection(
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(top = 8.dp),
                        articleUiState = articleUiState
                    )
                    Divider(
                        modifier = Modifier.padding(top = 8.dp),
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        style = MaterialTheme.typography.labelMedium,
                        text = articleUiState.excerpt.toHtmlString()
                    )
                }
            }
        }
    }
}

@Composable
fun ArticleContent(
    articleUiState: ArticleModelRoom,
    onArticleClicked: (Int) -> Unit
) {
    Surface {
        Card(modifier = Modifier
            .padding(8.dp)
            .clickable {
                articleUiState.id?.let {
                    onArticleClicked(it)
                }
            }
        ) {
            Row {
                AsyncImage(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.small)
                        .fillMaxWidth(0.35f)
                        .height(130.dp),
                    contentScale = ContentScale.FillHeight,
                    model = ImageRequest.Builder(LocalContext.current)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .data(articleUiState.imageUrl)
                        .build(),
                    imageLoader = LocalContext.current.imageLoader,
                    error = painterResource(id = R.drawable.ic_broken_image),
                    contentDescription = null
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .height(130.dp)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.TopStart),
                        style = MaterialTheme.typography.titleMedium,
                        text = articleUiState.title.toHtmlString(),
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis
                    )
                    TimeAndCategorySection(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(bottom = 4.dp),
                        articleUiState = articleUiState
                    )
                }
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun HeaderArticlePreview() {
    AppTheme {
        HeadlineArticle(
            articleUiState = ArticleModelRoom(
                title = "Veoma dugacak naslov vesti. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam nisi eros, hendrerit a nulla sit amet.",
                date = "2022-11-18T17:45:59"
            ),
            onArticleClicked = {}
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun ArticleContentPreview() {
    AppTheme {
        ArticleContent(
            articleUiState = ArticleModelRoom(
                title = "Veoma dugacak naslov vesti. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam nisi eros, hendrerit a nulla sit amet.",
                date = "2022-11-18T17:45:59"
            ),
            onArticleClicked = {}
        )
    }
}
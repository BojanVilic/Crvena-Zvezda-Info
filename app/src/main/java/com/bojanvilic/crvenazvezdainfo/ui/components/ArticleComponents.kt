package com.bojanvilic.crvenazvezdainfo.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.bojanvilic.crvenazvezdainfo.R
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.bojanvilic.crvenazvezdainfo.theme.AppTheme
import com.bojanvilic.crvenazvezdainfo.ui.ArticlesViewModel
import com.bojanvilic.crvenazvezdainfo.util.Resource
import com.bojanvilic.crvenazvezdainfo.util.categoryNumberToStringResource
import com.bojanvilic.crvenazvezdainfo.util.formatDateForArticle
import com.bojanvilic.crvenazvezdainfo.util.toHtmlString

@Composable
fun ArticlesScreen(articlesViewModel: ArticlesViewModel = viewModel()) {
    val articles = articlesViewModel.articlesList.collectAsState(initial = Resource.success(listOf()))

    LazyColumn {
        items(count = articles.value.data.size) { index ->
            if (index == 0) {
                HeadlineArticle(articleUiState = articles.value.data[index])
            } else {
                ArticleContent(
                    articles.value.data[index],
                    onArticleClicked = {}
                )
            }
        }
    }

}

@Composable
fun HeadlineArticle(
    articleUiState: ArticleModelRoom
) {
    Surface {
        Card(modifier = Modifier.padding(8.dp)) {
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
                    placeholder = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null
                )
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.titleLarge,
                        text = articleUiState.title?: ""
                    )
                    Row(
                        modifier = Modifier.align(Alignment.End),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_clock), contentDescription = null, tint = if (isSystemInDarkTheme()) Color.White else Color.Black)
                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            text = articleUiState.date.formatDateForArticle()?: ""
                        )
                        Text(
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.tertiary,
                            text = " | " + stringResource(id = articleUiState.category.categoryNumberToStringResource())
                        )
                    }
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
    onArticleClicked: () -> Unit
) {
    Surface {
        Card(modifier = Modifier
            .padding(8.dp)
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
                    placeholder = painterResource(id = R.drawable.construction),
                    contentDescription = null
                )
                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .height(130.dp)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.TopStart),
                        style = MaterialTheme.typography.titleMedium,
                        text = articleUiState.title.toHtmlString()
                    )
                    Row(
                        modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(painter = painterResource(id = R.drawable.ic_clock), contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            text = articleUiState.date.formatDateForArticle()?: ""
                        )
                        Text(
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.tertiary,
                            text = " | " + stringResource(id = articleUiState.category.categoryNumberToStringResource())
                        )
                    }
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
        HeadlineArticle(ArticleModelRoom())
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun ArticleContentPreview() {
    AppTheme {
        ArticleContent(
            articleUiState = ArticleModelRoom(title = "Veoma dugacak naslov vesti. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam nisi eros, hendrerit a nulla sit amet."),
            onArticleClicked = {}
        )
    }
}
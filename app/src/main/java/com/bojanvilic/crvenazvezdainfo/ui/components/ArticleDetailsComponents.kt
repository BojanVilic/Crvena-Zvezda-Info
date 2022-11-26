package com.bojanvilic.crvenazvezdainfo.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.bojanvilic.crvenazvezdainfo.ui.ArticleDetailsViewModel
import com.bojanvilic.crvenazvezdainfo.util.Resource
import com.bojanvilic.crvenazvezdainfo.util.Status
import com.bojanvilic.crvenazvezdainfo.util.toHtmlString
import kotlinx.coroutines.launch

@Composable
fun ArticleDetailsScreen(
    articlesViewModel: ArticleDetailsViewModel = hiltViewModel()
) {

    val articleDetails = articlesViewModel.articleDetails.collectAsState(initial = Resource.success(ArticleModelRoom()))
    val recommendedArticles = articlesViewModel.recommendedArticles.collectAsState(initial = listOf())

    Box {
        ArticleDetailsScreenContent(
            articleUiState = articleDetails.value.data,
            recommendedArticles = recommendedArticles.value,
            onArticleClicked = {
                articlesViewModel.setArticleId(it.toString())
            }
        )
        if (articleDetails.value.status == Status.LOADING) {
            Snackbar(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.BottomCenter)
            ) { Text(text = stringResource(id = R.string.label_refreshing)) }
        }
    }
}

@Composable
fun ArticleDetailsScreenContent(
    articleUiState: ArticleModelRoom,
    recommendedArticles: List<ArticleModelRoom>,
    onArticleClicked: (Int) -> Unit
) {

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Surface {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState
        ) {
            item {
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
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.headlineSmall,
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
                        style = MaterialTheme.typography.labelLarge,
                        text = articleUiState.content.toHtmlString()
                    )
                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        style = MaterialTheme.typography.titleLarge,
                        text = "Pogledajte jos:"
                    )
                }
            }
            items(count = recommendedArticles.size) { index ->
                ArticleContent(
                    recommendedArticles[index],
                    onArticleClicked =  {
                        onArticleClicked(it)
                        coroutineScope.launch {
                            listState.animateScrollToItem(0)
                        }
                    })
            }
        }
    }

}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ArticleDetailsScreenPreview() {
    AppTheme {
        ArticleDetailsScreenContent(
            ArticleModelRoom(
                title = "Veoma dugacak naslov vesti. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam nisi eros, hendrerit a nulla sit amet.",
                date = "2022-11-18T17:45:59",
                content = "Duško Ivanović nastavlja sa promenama\n" +
                        "Crvena zvezda se prvo rastala sa trojcom ljudi iz stručnog štaba, klub su napustili Marko Simonović, Branko Jorović i Marko Dimić, a sada je došlo pojačanje. Klub sa Malog Kalemegdana je objavio da je novi član struke Španac Karles Marko.\n" +
                        "Nekadašnji španski košarkaš i reprezentativac te države rođen je 1974. godine i biće pomoćnik Dušku Ivanoviću u nastavku sezone. On je u trenerskoj karijeri bio asistent u Manresi, a potom je vodio Oviedo i Palensiju. Ipak, mnogo je poznatiji po svojoj igračkoj karijeri. Najveće uspehe je ostvario kao reprezentativac gde je 2003. godine na Eurobasketu u Švedskoj uzeo srebro u ekipi sa Pau Gasolom, Huanom Karlosom Navarom, Felipeom Rejesom, Horheom Garbahosom, Karlosom Himenezom...\n" +
                        "U klupskoj karijeri je najpoznatiji po svoje četiri sezone u Huventudu, a igrao je i za Valjadolid, Hihon, Betis, Manresu i Saragosu. Kako navode iz kluba Karles Marko će se u rad stručnog štaba Crvene zvezde uključiti od vikenda, što znači da će debitovati na klupi za duplo kolo Evrolige protiv Albe i Makabija."
            ),
            recommendedArticles = listOf(),
            onArticleClicked = {}
        )
    }
}
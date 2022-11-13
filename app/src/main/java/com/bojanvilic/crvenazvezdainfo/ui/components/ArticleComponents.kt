package com.bojanvilic.crvenazvezdainfo.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.bojanvilic.crvenazvezdainfo.R
import com.bojanvilic.crvenazvezdainfo.theme.AppTheme

@Composable
fun ArticleContent(
    modifier: Modifier = Modifier,

    onArticleClicked: () -> Unit
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
       Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
           AsyncImage(
               modifier = Modifier.fillMaxWidth().height(180.dp),
               model = ImageRequest.Builder(LocalContext.current)
                   .diskCachePolicy(CachePolicy.ENABLED)
                   .data("https://www.citypng.com/public/uploads/preview/world-cup-trophy-icon-hd-png-11649363121koavwpqhpw.png")
                   .build(),
               imageLoader = LocalContext.current.imageLoader,
               placeholder = painterResource(id = R.drawable.construction),
               contentDescription = null
           )
           Text(text = "Naslov vesti")
       }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun ArticleContentPreview() {
    AppTheme {
        ArticleContent {

        }
    }
}
package com.bojanvilic.crvenazvezdainfo.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bojanvilic.crvenazvezdainfo.R
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.bojanvilic.crvenazvezdainfo.theme.AppTheme
import com.bojanvilic.crvenazvezdainfo.util.categoryNumberToStringResource
import com.bojanvilic.crvenazvezdainfo.util.formatDateForArticle

@Composable
fun TimeAndCategorySection(
    modifier: Modifier = Modifier,
    articleUiState: ArticleModelRoom
) {
    Row(
        modifier = modifier,
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
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun TimeAndCategorySectionPreview() {
    AppTheme {
        TimeAndCategorySection(
            modifier = Modifier,
            articleUiState = ArticleModelRoom(
                title = "Veoma dugacak naslov vesti. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam nisi eros, hendrerit a nulla sit amet.",
                date = "2022-11-18T17:45:59"
            )
        )
    }
}


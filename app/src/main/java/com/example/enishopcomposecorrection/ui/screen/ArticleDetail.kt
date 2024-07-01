package com.example.enishopcomposecorrection.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.enishopcompose.utils.DateConverter
import com.example.enishopcomposecorrection.bo.Article
import com.example.enishopcomposecorrection.repository.ArticleRepository
import com.example.enishopcomposecorrection.ui.common.TopBar


@Composable
fun ArticleDetailScreen(
    modifier: Modifier = Modifier,
    article: Article,
) {

    Scaffold(
      topBar = { TopBar() }
    ) {

        Column(
            modifier = modifier.padding(it)
        ) {
            ArticleDetail(article = article)
        }
    }
}


@Composable
fun ArticleDetail(
    article: Article,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = article.name,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 30.sp,
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Justify,
            lineHeight = 1.em
        )
        Surface(
            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            AsyncImage(
                model = article.urlImage, contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
        }
        Text(
            text = article.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Prix : ${String.format("%.2f", article.price)} €")
            Text(text = "Date de sortie : ${DateConverter.convertDateToSimpleString(article.date)}")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Checkbox(checked = true, onCheckedChange = {})
            Text("Favoris ?")
        }
    }
}

@Preview
@Composable
fun Preview() {
    val article = ArticleRepository().getArticle(1)
    ArticleDetailScreen(article = article!!)

}
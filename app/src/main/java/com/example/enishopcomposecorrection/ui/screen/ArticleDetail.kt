package com.example.enishopcomposecorrection.ui.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.enishopcompose.utils.DateConverter
import com.example.enishopcomposecorrection.bo.Article
import com.example.enishopcomposecorrection.ui.common.EniShopScaffold
import com.example.enishopcomposecorrection.ui.common.TopBar
import com.example.enishopcomposecorrection.vm.ArticleDetailViewModel

@Composable
fun ArticleDetailScreen2(
    modifier: Modifier = Modifier,
    articleId: Long,
    articleDetailViewModel: ArticleDetailViewModel = viewModel(factory = ArticleDetailViewModel.Factory),
    paddingValues: PaddingValues
) {
    LaunchedEffect(Unit) {
        articleDetailViewModel.initArticle(articleId)
    }

    val article by articleDetailViewModel.article.collectAsState()

    Column(
        modifier = modifier
            .padding(paddingValues)
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        ArticleDetail(article = article)
    }
}


@Composable
fun ArticleDetailScreen(
    modifier: Modifier = Modifier,
    articleId: Long,
    articleDetailViewModel: ArticleDetailViewModel = viewModel(factory = ArticleDetailViewModel.Factory),
    navigationIcon: @Composable () -> Unit
) {

    LaunchedEffect(Unit) {
        articleDetailViewModel.initArticle(articleId)
    }

    val article by articleDetailViewModel.article.collectAsState()

    Scaffold(
        topBar = { TopBar(navigationIcon = navigationIcon) }
    ) {

        Column(
            modifier = modifier
                .padding(it)
                .verticalScroll(
                    rememberScrollState()
                )
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

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = article.name,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 30.sp,
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.fr/search?q=eni+shop+${article.name}")
                    ).also {
                        context.startActivity(it)
                    }
                },
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
    //val article = ArticleRepository().getArticle(1)
    //ArticleDetailScreen(articleId = 3)

}
package com.article_feature.ui.screens.article_list

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.article_feature.domain.model.Article
import com.article_feature.ui.R
import com.article_feature.ui.screens.article_list.utils.SortingOption
import com.article_feature.ui.screens.article_list.utils.TestTag
import com.core.common.differenceOfDateFromCurrentDate

@Composable
fun ArticleListScreen(viewModel: ArticleViewModel) {

    val articleList = viewModel.articleList.value

    val popUpMenu = rememberSaveable { mutableStateOf(false) }
    val selectedItem = viewModel.sortingOption.value

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = stringResource(id = R.string.app_title)) },
            actions = {
                IconButton(
                    modifier=Modifier.testTag(TestTag.ARTICLE_SCREEN_MORE_VERT),
                    onClick = {
                    popUpMenu.value = !popUpMenu.value
                }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                }
                if (popUpMenu.value) {
                    MoreMenu(
                        expanded = popUpMenu.value,
                        selectedItem = selectedItem.sortingName,
                        dismissRequest = { popUpMenu.value = !popUpMenu.value },
                        onClick = {
                            viewModel.sortArticles(it)
                            popUpMenu.value = !popUpMenu.value
                        })
                }
            })
    }) {
        Log.d("TAG", "ArticleListScreen: ${it}")
        if (articleList.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.testTag(TestTag.ARTICLE_SCREEN_PROGRESS_BAR))
            }
        }
        if (articleList.error.isNotBlank()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag(TestTag.ARTICLE_SCREEN_ERROR_TEST_TAG),
                contentAlignment = Alignment.Center
            ) {
                Text(text = articleList.error)
            }
        }
        articleList.data?.let {
            LazyColumn(modifier = Modifier.testTag(TestTag.ARTICLE_LIST)) {
                items(articleList.data) {
                    ArticleListItem(article = it)
                }
            }
        }
    }
}

@Composable
fun ArticleListItem(article: Article) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Card(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(4.dp), shape = RoundedCornerShape(8.dp), elevation = 8.dp
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {

                AsyncImage(
                    model = article.banner_url, contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    text = article.title, style = MaterialTheme.typography.h6, maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    text = article.description,
                    style = MaterialTheme.typography.body1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    text = differenceOfDateFromCurrentDate(article.time_created),
                    style = MaterialTheme.typography.caption,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(14.dp))
            }
        }
    }
}

@Composable
fun MoreMenu(
    expanded: Boolean,
    selectedItem: String,
    dismissRequest: () -> Unit,
    onClick: (SortingOption) -> Unit
) {
    DropdownMenu(expanded = expanded, onDismissRequest = { dismissRequest.invoke() }) {
        DropdownMenuItem(onClick = { onClick.invoke(SortingOption.POPULAR) }) {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = SortingOption.POPULAR.sortingName,
                style = MaterialTheme.typography.body1,
                color = if (selectedItem == SortingOption.POPULAR.sortingName) Color.Blue else Color.Black
            )
        }
        DropdownMenuItem(onClick = { onClick.invoke(SortingOption.RECENT) }) {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = SortingOption.RECENT.sortingName,
                style = MaterialTheme.typography.body1,
                color = if (selectedItem == SortingOption.RECENT.sortingName) Color.Blue else Color.Black
            )
        }
    }
}

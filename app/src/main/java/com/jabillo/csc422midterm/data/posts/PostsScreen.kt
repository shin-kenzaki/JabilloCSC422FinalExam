package com.jabillo.csc422midterm.data.posts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jabillo.csc422midterm.data.posts.viewmodel.PostViewModel
import kotlinx.coroutines.launch


@Composable
fun PostsScreen(
    navController: NavController,
    viewModel: PostViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val postsState = viewModel.postsUiState

    return Column {
        Button(onClick = {
            coroutineScope.launch {
                viewModel.getPosts()
            }
        }) {
            Text("Get posts")
        }
         LazyColumn {
            items(postsState.posts) { post ->
                Card (modifier = Modifier.padding(11.dp)){
                    Text(post.id.toString())
                    Text(post.title)
                }
            }
        }
    }
}
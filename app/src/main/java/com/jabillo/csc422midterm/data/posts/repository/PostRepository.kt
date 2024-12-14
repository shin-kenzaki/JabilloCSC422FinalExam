package com.jabillo.csc422midterm.data.posts.repository

import com.jabillo.csc422midterm.data.posts.model.Post

interface PostRepository {

    // Retrieve all posts
    suspend fun getPosts(): List<Post>
}
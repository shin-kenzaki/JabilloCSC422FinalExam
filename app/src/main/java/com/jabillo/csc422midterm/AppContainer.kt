package com.jabillo.csc422midterm

import android.content.Context
import com.jabillo.csc422midterm.data.posts.repository.OnlinePostRepository
import com.jabillo.csc422midterm.data.quotes.repository.OnlineQuoteRepository
import com.jabillo.csc422midterm.data.recipes.repository.OnlineRecipeRepository
import com.jabillo.csc422midterm.data.todos.repository.OnlineTodoRepository
import com.jabillo.csc422midterm.data.users.repository.OfflineUserRepository
import com.jabillo.csc422midterm.data.users.repository.OnlineUserRepository


interface AppContainer {
    val onlinePostRepository: OnlinePostRepository
    val onlineQuoteRepository: OnlineQuoteRepository
    val onlineUserRepository: OnlineUserRepository
    val onlineRecipeRepository: OnlineRecipeRepository
    val onlineTodoRepository: OnlineTodoRepository
    val offlineUserRepository: OfflineUserRepository
}

class AppDataContainer (
    private val context: Context,
) : AppContainer {
    override val onlinePostRepository: OnlinePostRepository by lazy {
        OnlinePostRepository()
    }

    override val onlineQuoteRepository: OnlineQuoteRepository by lazy {
        OnlineQuoteRepository()
    }

    override val onlineUserRepository: OnlineUserRepository by lazy {
        OnlineUserRepository()
    }

    override val onlineRecipeRepository: OnlineRecipeRepository by lazy {
        OnlineRecipeRepository()
    }

    override val onlineTodoRepository: OnlineTodoRepository by lazy {
        OnlineTodoRepository()
    }

    override val offlineUserRepository: OfflineUserRepository by lazy {
        OfflineUserRepository(AppDatabase.getDatabase(context).userDao())
    }
}

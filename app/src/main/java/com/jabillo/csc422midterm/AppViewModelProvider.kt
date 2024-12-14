package com.jabillo.csc422midterm

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jabillo.csc422midterm.data.posts.viewmodel.PostViewModel
import com.jabillo.csc422midterm.data.quotes.viewmodel.QuotesViewModel
import com.jabillo.csc422midterm.data.recipes.viewmodel.RecipeViewModel
import com.jabillo.csc422midterm.data.todos.viewmodel.TodosViewModel
import com.jabillo.csc422midterm.data.users.viewmodel.UsersViewModel


object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            PostViewModel(ktorapp().container.onlinePostRepository)
        }

        initializer {
            QuotesViewModel(ktorapp().container.onlineQuoteRepository)
        }

        initializer {
            UsersViewModel(
                ktorapp().container.onlineUserRepository,
                ktorapp().container.offlineUserRepository
            )
        }

        initializer {
            RecipeViewModel(ktorapp().container.onlineRecipeRepository)
        }

        initializer {
            TodosViewModel(ktorapp().container.onlineTodoRepository)
        }
    }
}

fun CreationExtras.ktorapp(): KtorApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KtorApplication)
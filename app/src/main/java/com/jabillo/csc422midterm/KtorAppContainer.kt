package com.jabillo.csc422midterm

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.jabillo.csc422midterm.data.posts.PostsScreen
import com.jabillo.csc422midterm.data.posts.viewmodel.PostViewModel
import com.jabillo.csc422midterm.data.quotes.HomeScreen
import com.jabillo.csc422midterm.data.quotes.viewmodel.QuotesViewModel
import com.jabillo.csc422midterm.data.recipes.RecipeScreen
import com.jabillo.csc422midterm.data.recipes.viewmodel.RecipeViewModel
import com.jabillo.csc422midterm.data.todos.TodoScreen
import com.jabillo.csc422midterm.data.todos.viewmodel.TodosViewModel
import com.jabillo.csc422midterm.data.users.ProfileScreen
import com.jabillo.csc422midterm.data.users.viewmodel.UsersViewModel
import com.jabillo.csc422midterm.routes.MainRoutes
import com.jabillo.csc422midterm.routes.PostRoutes
import com.jabillo.csc422midterm.routes.Routes

@Composable
fun KtorAppContainer(
    postViewModel: PostViewModel = viewModel(factory = AppViewModelProvider.Factory),
    quotesViewModel: QuotesViewModel = viewModel(factory = AppViewModelProvider.Factory),
    usersViewModel: UsersViewModel = viewModel(factory = AppViewModelProvider.Factory),
    recipesViewModel: RecipeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    todosViewModel: TodosViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val navController: NavHostController = rememberNavController()

    Scaffold  (content = { padding->
        Box(modifier = Modifier.padding(padding)) {
            NavHost(navController = navController, startDestination = Routes.MAIN.name) {

                navigation(
                    startDestination = MainRoutes.HOME.name,
                    route = Routes.MAIN.name
                ) {
                    composable(route = MainRoutes.RECIPE.name) {
                        RecipeScreen(navController, recipesViewModel)
                    }

                    composable(route = MainRoutes.TODO.name) {
                        TodoScreen(navController, todosViewModel)
                    }

                    composable(route = MainRoutes.HOME.name) {
                        HomeScreen(navController, quotesViewModel)
                    }

                    composable(route = MainRoutes.PROFILE.name) {
                        ProfileScreen(navController)
                    }
                }



                navigation(
                    startDestination = PostRoutes.PostsScreen.name,
                    route = Routes.POSTS.name
                ) {
                    composable(route = PostRoutes.PostsScreen.name) {
                        PostsScreen(navController, postViewModel)
                    }
                }
            }
        }
    })
}

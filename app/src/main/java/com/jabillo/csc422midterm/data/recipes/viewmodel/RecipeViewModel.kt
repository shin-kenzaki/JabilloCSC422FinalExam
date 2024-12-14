package com.jabillo.csc422midterm.data.recipes.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jabillo.csc422midterm.data.recipes.model.Recipe
import com.jabillo.csc422midterm.data.recipes.repository.RecipeRepository

class RecipeViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {
    var recipeList by mutableStateOf<List<Recipe>>(emptyList())

    suspend fun getRecipes() {
        try {
            val recipes = recipeRepository.getRecipes()
            recipeList = recipes
            Log.i("", "recipeList $recipeList")
        } catch (e: Exception) {
            Log.e("", "Failed to fetch recipes: ${e.message}", e)
        }
    }
}
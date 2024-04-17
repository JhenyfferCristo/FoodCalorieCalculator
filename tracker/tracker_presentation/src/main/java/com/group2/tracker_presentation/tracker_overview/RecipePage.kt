package com.group2.tracker_presentation.tracker_overview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.group2.core_ui.LocalSpacing

data class Recipe(
    val title: String,
    val steps: List<String>
)

val sampleRecipes = listOf(
    Recipe(
        title = "Tomato Pasta",

        steps = listOf(
            "Boil water in a large pot.",
            "Add pasta and cook for 8-10 minutes.",
            "Drain and add tomato sauce."
        )
    ),
    Recipe(
        title = "Chicken Curry",

        steps = listOf(
            "Marinate chicken in spices and yogurt.",
            "Cook onions in a pan until golden.",
            "Add chicken and simmer until done."
        )
    ),
    Recipe(
        title = "Chocolate Cake",

        steps = listOf(
            "Preheat oven to 175 degrees Celsius.",
            "Mix ingredients and pour into a baking tin.",
            "Bake for 35 minutes."
        )
    )
)

@Composable
fun RecipePage() {
    val dimens = LocalSpacing.current
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimens.medium)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            sampleRecipes.forEach { recipe ->
                RecipeCard(recipe)
                Spacer(modifier = Modifier.height(dimens.large))
            }
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = recipe.title)
        Spacer(modifier = Modifier.height(8.dp))
        recipe.steps.forEach { step ->
            Text(text = "• $step")
        }
    }
}

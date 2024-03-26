package com.example.foodcaloriesexplorer
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import model.Category
import model.Ingredient
import model.IngredientDataSource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IngredientsScreen(category: Category?) {
    val ingredients = IngredientDataSource.getIngredientsForCategory(category);

    LazyVerticalGrid(GridCells.Fixed(2)) {
        items(ingredients.size) { index ->
            val ingredient = ingredients[index]
            IngredientCard(ingredient = ingredient)
        }
    }
}

@Composable
fun IngredientCard(ingredient: Ingredient) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),

        border = BorderStroke(1.dp, Color.Gray),

    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(ingredient.nameRes),
                style = MaterialTheme.typography.displayMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Calories: ${ingredient.calories}, Protein: ${ingredient.protein}g, Carbs: ${ingredient.carbs}g, Fat: ${ingredient.fat}g",
                style = MaterialTheme.typography.displaySmall
            )
        }
    }
}
package com.example.foodcaloriesexplorer.ui
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import model.Category
import model.DashboardDataSource
import model.Ingredient
import model.IngredientDataSource
import model.ListType

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IngredientsScreen(listToAddIngredient: ListType? = null, goBack: () -> Unit) {
    val ingredients = IngredientDataSource.Ingredients;

    LazyVerticalGrid(GridCells.Fixed(2)) {
        items(ingredients.size) { index ->
            val ingredient = ingredients[index]
            IngredientCard(ingredient = ingredient, onClick = { listToAddIngredient?.let {
                DashboardDataSource.addIngredientToList(ingredient, it)
                goBack()
            }})
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IngredientCard(ingredient: Ingredient, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),

        border = BorderStroke(1.dp, Color.Gray),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(ingredient.nameRes),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Calories: ${ingredient.calories}, Protein: ${ingredient.protein}g, Carbs: ${ingredient.carbs}g, Fat: ${ingredient.fat}g",
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}
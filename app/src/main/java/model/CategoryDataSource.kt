package model

import com.example.foodcaloriesexplorer.R

object CategoryDataSource {
    val categories = listOf(
        Category(
            nameRes =R.string.fruit,
            imageRes = R.drawable.fruit
        ),
        Category(
            nameRes =R.string.vegetable,
            imageRes = R.drawable.vegetable
    ),
        Category(
            nameRes =R.string.grains,
            imageRes = R.drawable.grains
        ),
        Category(
            nameRes =R.string.seaFood,
            imageRes = R.drawable.sea_food
        ),
        Category(
            nameRes =R.string.meat,
            imageRes = R.drawable.meat
        ),
    )
}
package model
import com.example.foodcaloriesexplorer.R

object IngredientDataSource {
    private fun getCategory(categoryNameRes: Int) =
        CategoryDataSource.categories.first { it.nameRes == categoryNameRes }

    val Ingredients = listOf(
        // Fruits
        Ingredient(
            nameRes = R.string.apples,
            category = getCategory(R.string.fruit),
            fat = 0.3,
            protein = 0.5,
            carbs = 14.0,
            calories = 52
        ),
        Ingredient(
            nameRes = R.string.bananas,
            category = getCategory(R.string.fruit),
            fat = 0.4,
            protein = 1.3,
            carbs = 27.0,
            calories = 89
        ),
        Ingredient(
            nameRes = R.string.oranges,
            category = getCategory(R.string.fruit),
            fat = 0.2,
            protein = 1.2,
            carbs = 12.0,
            calories = 47
        ),
        Ingredient(
            nameRes = R.string.strawberries,
            category = getCategory(R.string.fruit),
            fat = 0.4,
            protein = 0.8,
            carbs = 7.7,
            calories = 32
        ),
        Ingredient(
            nameRes = R.string.pineapples,
            category = getCategory(R.string.fruit),
            fat = 0.1,
            protein = 0.5,
            carbs = 13.1,
            calories = 50
        ),

        // Seafood
        Ingredient(
            nameRes = R.string.salmon,
            category = getCategory(R.string.seaFood),
            fat = 13.6,
            protein = 25.4,
            carbs = 0.0,
            calories = 208
        ),
        Ingredient(
            nameRes = R.string.shrimp,
            category = getCategory(R.string.seaFood),
            fat = 1.7,
            protein = 20.0,
            carbs = 0.0,
            calories = 99
        ),
        Ingredient(
            nameRes = R.string.tuna,
            category = getCategory(R.string.seaFood),
            fat = 0.8,
            protein = 30.9,
            carbs = 0.0,
            calories = 137
        ),
        Ingredient(
            nameRes = R.string.crab,
            category = getCategory(R.string.seaFood),
            fat = 0.9,
            protein = 19.4,
            carbs = 0.0,
            calories = 84
        ),
        Ingredient(
            nameRes = R.string.mussels,
            category = getCategory(R.string.seaFood),
            fat = 2.0,
            protein = 24.0,
            carbs = 7.0,
            calories = 172
        ),

        // Vegetables
        Ingredient(
            nameRes = R.string.broccoli,
            category = getCategory(R.string.vegetable),
            fat = 0.4,
            protein = 2.8,
            carbs = 6.0,
            calories = 34
        ),
        Ingredient(
            nameRes = R.string.carrots,
            category = getCategory(R.string.vegetable),
            fat = 0.3,
            protein = 0.9,
            carbs = 9.6,
            calories = 41
        ),
        Ingredient(
            nameRes = R.string.spinach,
            category = getCategory(R.string.vegetable),
            fat = 0.4,
            protein = 2.9,
            carbs = 3.6,
            calories = 23
        ),
        Ingredient(
            nameRes = R.string.bell_peppers,
            category = getCategory(R.string.vegetable),
            fat = 0.3,
            protein = 0.9,
            carbs = 6.0,
            calories = 31
        ),
        Ingredient(
            nameRes = R.string.tomatoes,
            category = getCategory(R.string.vegetable),
            fat = 0.2,
            protein = 0.9,
            carbs = 3.9,
            calories = 18
        ),

        // Meat
        Ingredient(
            nameRes = R.string.chicken,
            category = getCategory(R.string.meat),
            fat = 3.6,
            protein = 21.4,
            carbs = 0.0,
            calories = 165
        ),
        Ingredient(
            nameRes = R.string.beef,
            category = getCategory(R.string.meat),
            fat = 18.3,
            protein = 25.8,
            carbs = 0.0,
            calories = 250
        ),
        Ingredient(
            nameRes = R.string.pork,
            category = getCategory(R.string.meat),
            fat = 6.5,
            protein = 27.3,
            carbs = 0.0,
            calories = 212
        ),
        Ingredient(
            nameRes = R.string.lamb,
            category = getCategory(R.string.meat),
            fat = 25.6,
            protein = 20.4,
            carbs = 0.0,
            calories = 294
        ),
        Ingredient(
            nameRes = R.string.turkey,
            category = getCategory(R.string.meat),
            fat = 0.7,
            protein = 29.7,
            carbs = 0.0,
            calories = 157
        ),

        // Grains
        Ingredient(
            nameRes = R.string.rice,
            category = getCategory(R.string.grains),
            fat = 0.3,
            protein = 2.7,
            carbs = 28.0,
            calories = 130
        ),
        Ingredient(
            nameRes = R.string.quinoa,
            category = getCategory(R.string.grains),
            fat = 6.1,
            protein = 4.4,
            carbs = 21.3,
            calories = 120
        ),
        Ingredient(
            nameRes = R.string.oats,
            category = getCategory(R.string.grains),
            fat = 6.9,
            protein = 16.9,
            carbs = 66.3,
            calories = 389
        ),
        Ingredient(
            nameRes = R.string.barley,
            category = getCategory(R.string.grains),
            fat = 1.2,
            protein = 12.5,
            carbs = 73.5,
            calories = 354
        ),
        Ingredient(
            nameRes = R.string.wheat_flour,
            category = getCategory(R.string.grains),
            fat = 1.2,
            protein = 10.3,
            carbs = 74.1,
            calories = 364
        )
    )
}
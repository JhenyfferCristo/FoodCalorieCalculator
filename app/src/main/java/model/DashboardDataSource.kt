package model;

enum class ListType {
  Breakfast,
  Lunch,
  Dinner,
  Snack
}

object DashboardDataSource {
  private val breakfastIngredients = mutableListOf<Ingredient>();
  private val lunchIngredients = mutableListOf<Ingredient>();
  private val dinnerIngredients =mutableListOf<Ingredient>()
  private val snackIngredients = mutableListOf<Ingredient>()

  fun addIngredientToList(ingredient: Ingredient, listType: ListType) {
    when (listType) {
      ListType.Breakfast -> breakfastIngredients.add(ingredient)
      ListType.Lunch -> lunchIngredients.add(ingredient)
      ListType.Dinner -> dinnerIngredients.add(ingredient)
      ListType.Snack -> snackIngredients.add(ingredient)
    }
  }

  fun getTotalCaloriesForList(listType: ListType): Int{
    val selectedList = when (listType) {
      ListType.Breakfast -> breakfastIngredients
      ListType.Lunch -> lunchIngredients
      ListType.Dinner -> dinnerIngredients
      ListType.Snack -> snackIngredients
    }
    val caloriesList = selectedList.map { it -> it.calories }
    return caloriesList.sum()
  }
}

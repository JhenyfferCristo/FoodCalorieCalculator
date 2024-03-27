
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodcaloriesexplorer.data.UserManager
import model.DashboardDataSource
import model.ListType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(modifier: Modifier = Modifier.fillMaxSize(), onAddIngredientsClick: (selectedList: ListType) -> Unit) {
//    CenterAlignedTopAppBar(
//        title = {
//            Text(
//                text = stringResource(R.string.app_name),
//                style = MaterialTheme.typography.displaySmall,
//            )
//        },
//        modifier = modifier
//    )
    Column {
        DashboardStats()
        CategoryCard(title= "Breakfast", onPlusClick = { onAddIngredientsClick(ListType.Breakfast)})
        CategoryCard(title= "Lunch", onPlusClick = {onAddIngredientsClick(ListType.Lunch)})
        CategoryCard(title= "Dinner", onPlusClick = {onAddIngredientsClick(ListType.Dinner)})
        CategoryCard(title= "Snack", onPlusClick = {onAddIngredientsClick(ListType.Snack)})
    }

}

@Composable
fun DashboardStats(modifier: Modifier = Modifier){
    val breakfastCalories = DashboardDataSource.getTotalCaloriesForList(ListType.Breakfast);
    val lunchCalories = DashboardDataSource.getTotalCaloriesForList(ListType.Lunch);
    val dinnerCalories = DashboardDataSource.getTotalCaloriesForList(ListType.Dinner);
    val snackCalories = DashboardDataSource.getTotalCaloriesForList(ListType.Snack);
    Column {
        Row(){
            Text(text = "Calories Goal: ")
            Text(text= "${UserManager.currentUser?.caloriesGoal ?: 0}")
        }
        Row(){
            CaloriesStats(label = "Breakfast", value = breakfastCalories)
            CaloriesStats(label = "Lunch", value = lunchCalories)
            CaloriesStats(label = "Dinner", value =dinnerCalories)
            CaloriesStats(label = "Snack", value = snackCalories)
        }
    }
}

@Composable
fun CaloriesStats(label: String, value: Number){
    Column {
        Text(text= label, style = MaterialTheme.typography.titleSmall)
        Text(text= value.toString(), style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun CategoryCard(title: String, onPlusClick: () -> Unit){
    Card( elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Spacer(Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp))

            ) {

                    IconButton(onClick = onPlusClick) {
                        Icon(
                            Icons.Filled.Add, contentDescription = "Add ingredient")
                    }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDashScreen() {
    DashboardScreen(onAddIngredientsClick = {})
}
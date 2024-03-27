package com.example.foodcaloriesexplorer.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.foodcaloriesexplorer.data.UserManager


@Composable
fun MainContent(isSignIn: () -> Unit, navigateToCategories: () -> Unit) {
   var showDialog by remember { mutableStateOf(false) }
   var dialogMessage by remember { mutableStateOf("") }

   // Use Box for alignment
   Box(modifier = Modifier.fillMaxSize()) {
      Text(text = "Successfully Sign In, This is the main page")

      Button(onClick = {
         if (UserManager.currentUser != null) {
            navigateToCategories()
         } else {
            dialogMessage = "Please Sign In."
            showDialog = true
         }
      }, modifier = Modifier.align(Alignment.Center)) { // Example alignment, adjust as needed
         Text("Profile")
      }
   }
   if (showDialog){
      AlertDialog(
         onDismissRequest = { showDialog = false },
         title = { Text("Alert") },
         text = { Text(dialogMessage) },
         confirmButton = {
            Button(onClick = {
               showDialog = false
               isSignIn()
            }) {
               Text("OK")
            }
         })
   }

}

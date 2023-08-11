package com.example.myplaygroundapp.screens

import android.widget.ProgressBar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myplaygroundapp.viewmodels.CategoryViewModel

@Composable
fun CategoryScreen(onClick: (category: String) -> Unit) {
    val categoryViewModel : CategoryViewModel = hiltViewModel()
    val categories:State<List<String>> = categoryViewModel.categories.collectAsState()

    if (categories.value.isEmpty()){
       Box(modifier = Modifier.fillMaxSize(1f), contentAlignment = Alignment.Center) {
           CircularProgressIndicator(color = Color.Black)  }
    }
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp), 
        verticalArrangement = Arrangement.SpaceAround,
        ) {
        items(categories.value) {
            CategoryItem(category = it, onClick)
        }
    }
}

@Composable
fun CategoryItem(category: String, onClick : (category:String) ->Unit) {

        Card(modifier= Modifier
            .padding(8.dp).clickable {
                onClick(category)
            }
            .safeContentPadding(), elevation = CardDefaults.cardElevation()) {
            Text(
                text = category,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(20.dp, 20.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }

}
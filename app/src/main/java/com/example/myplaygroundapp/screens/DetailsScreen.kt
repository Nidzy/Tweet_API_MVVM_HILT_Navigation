package com.example.myplaygroundapp.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myplaygroundapp.R
import com.example.myplaygroundapp.viewmodels.DetailViewModel

@Composable
fun DetailsScreen (){
    val detailViewModel : DetailViewModel = hiltViewModel()
    val tweets = detailViewModel.tweet.collectAsState()


    if (tweets.value.isEmpty()){
        Box(modifier = Modifier.fillMaxSize(1f), contentAlignment = Alignment.Center) {
            Text(text = "Loading......", style =MaterialTheme.typography.titleLarge)
        }
    }
    LazyColumn(content = {
        items(tweets.value){
            DetailItem(tweet = it.content)
        }
    })
}


@Composable
fun DetailItem(tweet: String) {

    Card(modifier= Modifier
        .padding(8.dp)
        .safeContentPadding(), colors = CardDefaults.cardColors(
        containerColor = Color.Black,
    ),elevation = CardDefaults.outlinedCardElevation()) {
        Text(
            text = tweet,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.padding(20.dp, 20.dp),
            style = MaterialTheme.typography.bodySmall
        )
    }
}
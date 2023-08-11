package com.example.myplaygroundapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myplaygroundapp.api.TweetAPI
import com.example.myplaygroundapp.screens.CategoryScreen
import com.example.myplaygroundapp.screens.DetailsScreen
import com.example.myplaygroundapp.ui.theme.MyPlaygroundAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var tweetAPI: TweetAPI
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch {
            var response= tweetAPI.getCategories()
            Log.d("TweetResponse", response.body().toString())
            var result = response.body().toString()
        }

        setContent {
            MyPlaygroundAppTheme {
                Scaffold(
                    topBar = {
                    TopAppBar(title = {
                            Text(text =  "Tweets", color = Color.White)
                        },colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Black))
                    }
                ){
                    Box(modifier= Modifier.padding(it)){
                        App()
                    }
                }

            }
        }
    }
}

@Composable
fun App(){
    var navController = rememberNavController()
    NavHost(navController = navController, startDestination = "category"){
        composable(route = "category"){
            CategoryScreen(onClick = {
                navController.navigate("detail/${it}")
            })
        }

        composable(route = "detail/{category}", arguments = listOf(
            navArgument("category"){
                type = NavType.StringType
            }
        )){
            DetailsScreen()
        }
    }
}

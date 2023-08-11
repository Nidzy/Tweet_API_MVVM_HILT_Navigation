package com.example.myplaygroundapp.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myplaygroundapp.model.Tweet
import com.example.myplaygroundapp.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val tweetRepository: TweetRepository,private val savedStateHandle: SavedStateHandle) :
    ViewModel() {

    val tweet: StateFlow<List<Tweet>>
        get() = tweetRepository.tweets

    init {
        viewModelScope.launch() {
            val category = savedStateHandle.get<String>("category") ?: "Travel"
            tweetRepository.getTweets(category)
        }
    }


}
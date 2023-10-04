package com.example.flashcard

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "FlashcardViewModel"
private const val USER = "Thursday"
private const val PASSWORD = "Tuesday"

class FlashcardViewModel : ViewModel() {

    init {
        Log.d(TAG,"ViewModel instance created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG,"ViewModel instance about to be destroyed")
    }

    fun isTextEqualToUSER(text: String): Boolean {
        return text == USER
    }

    fun isTextEqualToPASSWORD(text: String): Boolean {
        return text == PASSWORD
    }

}
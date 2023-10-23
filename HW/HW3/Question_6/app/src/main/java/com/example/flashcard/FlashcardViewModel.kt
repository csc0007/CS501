package com.example.flashcard

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.random.Random

private const val TAG = "FlashcardViewModel"
private const val USER = "t"
private const val PASSWORD = "t"

const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"

class FlashcardViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    init {
        Log.d(TAG,"ViewModel instance created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG,"ViewModel instance about to be destroyed")
    }

    private var randomNum1: Int = 0
    private var randomNum2: Int = 0
    private var operationNum: Int = Random.nextInt(0, 1)
    //0 is addition, 1 is subtraction

    var currentIndex : Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY)?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY,value)

    fun isTextEqualToUSER(text: String): Boolean {
        return text == USER
    }

    fun isTextEqualToPASSWORD(text: String): Boolean {
        return text == PASSWORD
    }

    fun setQuestion() : String {
        var text : String
        randomNum1 = Random.nextInt(1, 100)
        randomNum2 = Random.nextInt(1, 21)
        if (operationNum==0) text = currentIndex.toString()+". "+
                randomNum1.toString() + " + " + randomNum2.toString()
        else text = currentIndex.toString()+". " +
                randomNum1.toString() + " - " + randomNum2.toString()
        return text
    }

    fun questionResult(): Int{
        var num : Int
        if (operationNum == 0) num = randomNum1+randomNum2  //addition condition
        else num = randomNum1-randomNum2  //subtraction condition
        return num
    }

    fun moveToNext(){
        currentIndex = currentIndex + 1
        if (currentIndex==11) currentIndex=0

    }
}
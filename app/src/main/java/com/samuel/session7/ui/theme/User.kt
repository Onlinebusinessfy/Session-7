package com.samuel.session7.ui.theme

import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.runtime.snapshots.SnapshotStateList

data class User (
    val fullName: String,
    val age: Int,
    val birthday: String,
    val address: String,
    val username: String,
    val isVerified: Boolean,
    val likesCount: Int,
){
    //Challenge 1 Session 3
    fun getAgeGroup (age: Int): String{
        return when {
            age < 13 -> "Child"
            age <= 17 -> "Teenager"
            age <= 59 -> "Adult"
            else -> "Senior"
        }
    }

    fun updateProfile(newUsername: String, newAge: Int): User{
        return copy(
            username = newUsername,
            age = newAge
        )
    }

    fun addFriend(friendList: SnapshotStateList<String>, newFriend: String){
        if(newFriend.isNotBlank() && !friendList.contains(newFriend)){
            friendList.add(newFriend)
        }
    }

    //Challenge 2
    fun removeFriend(friendList: SnapshotStateList<String>, removeFriend: String){
        if (removeFriend.isNotBlank() && friendList.contains(removeFriend)) {
            friendList.remove(removeFriend)
        }
    }
}
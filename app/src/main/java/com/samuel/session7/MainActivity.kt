package com.samuel.session7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samuel.session7.ui.theme.Session7Theme
import com.samuel.session7.ui.theme.User

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserProfileScreen()
        }
    }
}

@Composable
fun UserProfileScreen(){
    var user1 by remember {
        mutableStateOf(
            User(
                fullName = "John Lenon",
                age = 85,
                birthday = "10/09/1940",
                address = "Liverpool, UK",
                username = "@jlennon",
                isVerified = true,
                likesCount = 0
            )
        )
    }

    //Challenge 3
    val friends = remember {
        mutableStateListOf(
            "Samuel",
            "Reggie",
            "Claudia",
            "Christian",
            "Esteban"
        )
    }

    //Challenge 4
    var newUsername by remember { mutableStateOf(user1.username) }
    var newAge by remember { mutableStateOf(user1.age.toString()) }
    var newFriendName by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize()
    ){
        ProfileContent(
            user = user1,
            friends = friends,
            newUsername = newUsername,
            newAge = newAge,
            newFriendName = newFriendName,
            //Actions
            onUsernameChange = { newUsername = it },
            onAgeChange = { newAge = it },
            onFriendNameChange = { newFriendName = it },
            onLike = { user1 = user1.copy(likesCount = user1.likesCount + 1)},
            onUpdateProfile = {
                val ageInt = newAge.toIntOrNull()
                if (ageInt != null){
                    user1 = user1.updateProfile(newUsername, ageInt)
                }
            },
            onAddFriend = {
                user1.addFriend(friends, newFriendName)
                newFriendName = ""
            },
            onRemoveFriend = { friendName -> user1.removeFriend(friends, friendName)},
            onResetLike = {user1 = user1.copy(likesCount = 0)}
        )

    }

}

@Composable
fun ProfileContent(
    user: User,
    friends: List<String>,
    newUsername: String,
    newAge: String,
    newFriendName:String,
    onUsernameChange: (String) -> Unit,
    onAgeChange: (String) -> Unit,
    onFriendNameChange: (String) -> Unit,
    onLike: () -> Unit,
    onUpdateProfile: () -> Unit,
    onAddFriend: () -> Unit,
    onRemoveFriend: (String) -> Unit,
    onResetLike: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        UserProfileCard(user)
    }
}

@Composable
fun UserProfileCard(user: User){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = user.fullName)
            Text(text = user.username)
            Spacer(modifier = Modifier.height(8.dp))

            //Challenge 5
            Text(text = "Age: ${user.age}")
            Text(text = "Birthday: ${user.birthday}")
            Text(text = "Address: ${user.address}")
            Text(text = "Verified: ${if (user.isVerified) "Yes" else "No"}")
            Text(text = "LikesCount: ${user.likesCount}")
            Text(text = "Age Group: ${user.getAgeGroup(user.age)}")
        }

    }
}
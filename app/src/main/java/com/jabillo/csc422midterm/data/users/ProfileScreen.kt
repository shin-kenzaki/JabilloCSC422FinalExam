package com.jabillo.csc422midterm.data.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.jabillo.csc422midterm.data.users.model.LoggedInUserHolder

@Composable
fun ProfileScreen (navController: NavController) {
    val loggedInUser = LoggedInUserHolder.loggedInUser.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Profile",
                fontSize = 45.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        // Display user image
        val imagePainter = rememberImagePainter(loggedInUser.value?.image ?: "")
        Image(
            painter = imagePainter,
            contentDescription = "User Image",
            modifier = Modifier
                .size(150.dp) // Increase circle size to 160dp
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display user information
        ProfileTextField(
            label = "Name",
            value = "${loggedInUser.value?.firstName.orEmpty()} ${loggedInUser.value?.lastName.orEmpty()}"
        )
        ProfileTextField("Username", loggedInUser.value?.username ?: "")
        ProfileTextField("Email", loggedInUser.value?.email ?: "")
        ProfileTextField("Phone", loggedInUser.value?.phone ?: "")
    }


    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        FloatingActionButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier
                .width(100.dp)
                .height(110.dp)
                .padding(bottom = 50.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(15.dp)
        ) {
            Icon(Icons.Filled.Home, contentDescription = "Back")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTextField(label: String, value: String) {

    OutlinedTextField(
        value = value,
        onValueChange = { /* Nothing */ },
        enabled = true,
        modifier = Modifier
            .width(300.dp),
        readOnly = true,
        label = { Text(text = label) }, // Display label as placeholder
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Transparent, // Set border color to transparent
            focusedBorderColor = Color.Transparent, // Set border color to transparent
            disabledBorderColor = Color.Transparent // Set border color to transparent
        )
    )
}
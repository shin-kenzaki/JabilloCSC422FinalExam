package com.jabillo.csc422midterm.data.quotes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jabillo.csc422midterm.data.quotes.viewmodel.QuotesViewModel
import com.jabillo.csc422midterm.data.users.model.LoggedInUserHolder
import com.jabillo.csc422midterm.routes.MainRoutes
import java.time.LocalDateTime

@SuppressLint("NewApi")
@Composable
fun HomeScreen(
    navController: NavController,
    quotesViewModel: QuotesViewModel
) {
    val randomQuote = quotesViewModel.randomQuote
    val currentTime = LocalDateTime.now()
    val randomGreeting = remember {
        listOf("Hi", "Hello", "Hey", "Greetings", "What's up").random()
    }

    val timeBasedGreeting = when (currentTime.hour) {
        in 0..11 -> "Good morning"
        in 12..16 -> "Good afternoon"
        else -> "Good evening"
    }

    val finalGreeting = "$randomGreeting, $timeBasedGreeting"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = finalGreeting,
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column (
            modifier = Modifier.width(300.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            randomQuote?.let { quote ->
                Text(
                    text = "\"${quote.quote}\"",
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "- ${quote.author}",
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                modifier = Modifier
                    .size(100.dp, 75.dp),
                shape = RoundedCornerShape(15.dp),
                onClick = { navController.navigate(MainRoutes.TODO.name) }
            ) {
                Text(text = "Todos")
            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(
                modifier = Modifier
                    .size(100.dp, 75.dp),
                shape = RoundedCornerShape(15.dp),
                onClick = { navController.navigate(MainRoutes.RECIPE.name) }
            ) {
                Text(text = "Recipes")
            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(
                modifier = Modifier
                    .size(100.dp, 75.dp),
                shape = RoundedCornerShape(15.dp),
                onClick = { navController.navigate(MainRoutes.PROFILE.name) }
            ) {
                Text(text = "Profile")
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            modifier = Modifier
                .size(200.dp, 50.dp),
            shape = RoundedCornerShape(15.dp),
            onClick = { LoggedInUserHolder.clearLoggedInUser() }
        ) {
            Row (
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = "Logout")
                Spacer(modifier = Modifier.width(15.dp))
                Icon(Icons.Filled.ExitToApp, contentDescription = "Logout")

            }
        }
    }

    LaunchedEffect(true) {
        quotesViewModel.getRandomQuote()
    }
}
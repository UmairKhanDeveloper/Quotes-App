package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun AboutScreen(navController: NavHostController) {
    val features = listOf(
        "About the App: Purpose: The Quotes App is designed to inspire, motivate, and connect users through a vast collection of meaningful quotes. It serves as a daily source of positivity, wisdom, and reflection.",
        "Vision: Empower users to discover and share quotes that resonate with them, fostering personal growth and spreading inspiration worldwide.",
        "Features Highlight: Curated Quotes: Access a wide range of quotes categorized by themes like love, motivation, humor, and success.",
        "Favorites: Save your favorite quotes for quick access anytime.",
        "Sharing: Easily share quotes with friends and family via social media or messaging apps.",
        "Search Functionality: Find quotes by keywords, topics, or authors with ease.",
        "Daily Notifications: Receive a handpicked 'Quote of the Day' to start your morning with positivity.",
        "User Submissions: Contribute your favorite or original quotes to the app’s growing collection.",
        "Mission Statement: Our mission is to make meaningful words accessible to everyone, fostering a supportive and inspiring community. Whether it's a boost of motivation or a comforting thought, we aim to enrich lives through the power of quotes.",
        "Community and Engagement: Join a global community of quote enthusiasts.",
        "Share, discuss, and reflect on the impact of quotes in your life.",
        "Participate in weekly challenges and themed discussions for deeper connections."
    )

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(features) { feature ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
            ) {
                Text(
                    text = feature,
                    style = MaterialTheme.typography.bodyMedium.copy(fontStyle = FontStyle.Italic),
                    modifier = Modifier.padding(16.dp),
                    color = Color(0xFF616161)
                )
            }
        }
    }
}


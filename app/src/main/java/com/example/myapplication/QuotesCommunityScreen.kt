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
fun QuotesCommunityScreen(navController: NavHostController) {
    val features = listOf(
        "User-Submitted Quotes:\n\nAllow users to contribute their favorite or original quotes. Provide proper attribution to user-submitted quotes. Curate and display top-rated submissions on the app's main feed.",
        "Discussion Forums:\n\nEnable users to discuss quotes, their meanings, and their impact on personal lives. Host themed discussions such as 'Quotes on Love' or 'How Motivation Quotes Changed My Day.'",
        "Inspirational Challenges:\n\nWeekly or daily challenges that encourage users to reflect on a particular quote and share their thoughts. Example: 'Write a paragraph about how this quote applies to your life.'",
        "Social Features:\n\nUsers can follow each other and engage with others' favorite or submitted quotes. Include options to like, comment, and share within the app.",
        "Community Events:\n\nHost live events or webinars featuring motivational speakers or authors. Run contests like 'Create the Best Custom Quote Design.'",
        "Language & Cultural Diversity:\n\nAllow users to share quotes in different languages and cultures. Provide translations to foster inclusivity.",
        "Leaderboards:\n\nGamify the experience by rewarding users with points for submitting, liking, or sharing quotes. Recognize top contributors or most-liked quotes of the week.",
        "Custom Profile for Users:\n\nEnable users to create profiles displaying their favorite quotes, submitted quotes, and badges earned.",
        "Benefits of the Community:\n\nInspiration and Motivation: A place where people can share and discover uplifting thoughts and encourage a supportive environment for personal growth."
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
                    modifier = androidx.compose.ui.Modifier.padding(16.dp),
                    color = Color(0xFF616161)
                )
            }
        }
    }
}



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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun FeaturePreviewScreen(navController: NavHostController) {

    val features = listOf(
        "Browse Quotes by Categories\n\nAllow users to explore quotes categorized by themes such as Inspiration, Love, Humor, and Success. Display category images or icons for visual appeal.",
        "Save Favorites\n\nUsers can mark quotes as favorites with a heart icon. Saved quotes can be viewed in a dedicated 'Favorites' section.",
        "Share Quotes\n\nProvide an option to share quotes on social media platforms or via messaging apps with one tap. Include customizable background designs for visually appealing shared content.",
        "Daily Quote Notification\n\nSend a new quote to users as a daily notification to inspire or motivate them.",
        "Author Information\n\nInclude a section with information about the author of each quote. Optionally, provide links to learn more about the author or related works.",
        "Search Functionality\n\nAllow users to search for quotes by keywords, topics, or authors.",
        "Dark and Light Themes\n\nProvide light and dark modes for better readability and user preference. Toggle available in the settings menu.",
        "Custom Quote Creation\n\nEnable users to create and save their own quotes. Allow users to style their quotes with custom fonts and background images.",
        "Quote of the Day Widget\n\nOffer a widget that users can add to their home screen, displaying a new quote daily.",
        "In-App Quote Editing\n\nLet users personalize quotes by changing fonts, background colors, or adding illustrations before sharing.",
        "Offline Access\n\nAllow users to save quotes for offline viewing.",
        "Community Contributions\n\nProvide a feature for users to submit their favorite quotes to the app's database. Display user-submitted quotes with attributions.",
        "Inspirational Challenges\n\nCreate daily or weekly challenges encouraging users to reflect on a quote and share their thoughts.",
        "Quotes in Different Languages\n\nOffer multilingual support for quotes in various languages. Provide translations for popular quotes.",
        "Animated Quote Display\n\nUse animations or transitions when displaying quotes to make the app visually engaging."
    )

    LazyColumn(
        modifier = androidx.compose.ui.Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(features) { feature ->
            Card(
                modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
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


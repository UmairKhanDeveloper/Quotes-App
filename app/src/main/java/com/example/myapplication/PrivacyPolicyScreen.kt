package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun PrivacyPolicyScreen(navController: NavHostController) {
    val features = listOf(
        "Introduction" to listOf(
            "App ka naam aur purpose explain karein.",
            "Aapka company/organization ka naam aur contact details."
        ),
        "Data Collection" to listOf(
            "Aap kis tarah ka data collect karte hain (e.g., personal information, device data, location data, etc.).",
            "Agar aap third-party services (jaise Google Analytics, Ad networks) use karte hain to unka mention karein."
        ),
        "Use of Data" to listOf(
            "Data ka use kis purpose ke liye hota hai (e.g., app ka functionality improve karna, personalized experience dena, analytics)."
        ),
        "Data Protection" to listOf(
            "Aap kis tarah se users ke data ko secure karte hain (e.g., encryption, secure servers).",
            "Agar koi data breach hoti hai to aap kaise inform karenge."
        ),
        "Cookies" to listOf(
            "Agar aap cookies ya similar technologies use karte hain to unka explanation aur users ko cookies ko disable karne ka option dena."
        ),
        "Data Sharing" to listOf(
            "Kya aap kisi third-party ke saath users ka data share karte hain? Agar haan, to kis purpose ke liye."
        ),
        "User Rights" to listOf(
            "Users ko kis tarah ke rights milte hain, jaise apne data ko access karna, modify karna, delete karna.",
            "Agar aapki app ka user kisi particular region se hai (e.g., EU), to relevant laws ka mention karein (GDPR, CCPA, etc.)."
        ),
        "Children’s Privacy" to listOf(
            "Agar aapki app children ke liye designed nahi hai, to aapko is baat ka mention karna zaroori hai ke aap 13 saal se neeche ke bachon se data collect nahi karte."
        ),
        "Changes to Privacy Policy" to listOf(
            "Aap apni privacy policy ko kabhi update kar sakte hain, isliye aapko users ko notify karna hoga agar koi significant change ho."
        ),
        "Contact Information" to listOf(
            "Agar users ke paas koi questions hoon, to wo aap se kis tareeqe se contact kar sakte hain."
        )
    )

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(features) { (title, description) ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF616161)
                    )
                    description.forEach { line ->
                        Text(
                            text = line,
                            style = MaterialTheme.typography.bodyMedium.copy(fontStyle = FontStyle.Italic),
                            color = Color(0xFF616161)
                        )
                    }
                }
            }
        }
    }
}

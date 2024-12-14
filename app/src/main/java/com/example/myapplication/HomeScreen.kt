package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.db.Favorite
import com.example.myapplication.db.FavoriteDataBase
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSceen(navController: NavHostController) {
    val context = LocalContext.current
    val sharePref = remember { context.getSharedPreferences("theme", Context.MODE_PRIVATE) }
    val database = remember { FavoriteDataBase.getDataBase(context) }
    val repository = remember { Repository(database) }
    val viewModel = remember { MainViewModel(repository) }

    var isLoading by remember { mutableStateOf(false) }
    var quotesData by remember { mutableStateOf<List<QuotesItem>?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getQuotes()
    }

    MyApplicationTheme(darkTheme = sharePref.getBoolean("isDark", false)) {
        val state by viewModel.allQuotes.collectAsState()

        when (state) {
            is ResultState.Error -> {
                isLoading = false
                val error = (state as ResultState.Error).error
                Text(text = error.toString())
            }

            ResultState.Laoding -> {
                isLoading = true
                Log.i("RESPONSE", "HomeSceen: Loading")
            }

            is ResultState.Success -> {
                isLoading = false
                val success = (state as ResultState.Success).response
                quotesData = success
                Log.i("RESPONSE", "HomeSceen: Success")

            }
        }

        Scaffold(topBar = {
            SmallTopAppBar(
                title = {

                        Text(
                            text = "Quotes",
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                },
                colors = TopAppBarDefaults.topAppBarColors(Color(0xFFE3F2FD)),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "",
                        tint = Color.Black
                    )
                },
                actions = {
                }
            )
        }) { paddingValues ->
            when (isLoading) {
                true -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                false -> {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth().background(Color(0xFFE3F2FD))
                            .padding(
                                top = paddingValues.calculateTopPadding(),
                                bottom = paddingValues.calculateBottomPadding()
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        SearchBar(
                            query = searchQuery,
                            onQueryChange = {
                                searchQuery = it
                                quotesData
                                    ?.filter { it.quote.lowercase().contains(searchQuery.lowercase(), ignoreCase = true) }
                            },
                            onSearch = {

                            },
                            active = isSearchActive,
                            onActiveChange = {
                                isSearchActive = it
                            },
                            placeholder = {
                                Text("Search Quotes")
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = ""
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(
                                    if (isSearchActive) {
                                        1200.dp
                                    }else {
                                        60.dp
                                    })
                                .offset(y = -(18).dp)
                                .padding( start= 2.dp, end = 2.dp)
                                .clickable {
                                    isSearchActive = true
                                },
                            colors = SearchBarDefaults.colors(
                                containerColor = Color(0xFFE3F2FD)

                            ), shadowElevation = 8.dp
                        ) {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.Start
                            ) {

                                quotesData
                                    ?.filter { it.quote.lowercase().contains(searchQuery.lowercase(), ignoreCase = true) }
                                    ?.let { filteredQuotes ->
                                        items(filteredQuotes) { quoteItem ->
                                            QuotesItem(quotesItem = quoteItem, viewModel)
                                        }
                                    }
                            }
                        }

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.Start
                        ) {

                            quotesData
                                ?.filter { it.quote.lowercase().contains(searchQuery.lowercase(), ignoreCase = true) }
                                ?.let { filteredQuotes ->
                                    items(filteredQuotes) { quoteItem ->
                                        QuotesItem(quotesItem = quoteItem, viewModel)
                                    }
                                }
                        }
                    }
                }
            }

        }
    }

}


@Composable
fun QuotesItem(quotesItem: QuotesItem, viewModel: MainViewModel) {
    val scope = rememberCoroutineScope()
    var isFavorite by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "\"${quotesItem.quote}\" - ${quotesItem.author}")
        type = "text/plain"
    }



    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = quotesItem.quote,
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = Color(0xFF0D47A1)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "- ${quotesItem.author}",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = Color(0xFF616161)
            )
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Icon(
                    imageVector = if (isFavorite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                    tint = if (isFavorite) Color.Red else Color.Gray,
                    contentDescription = "Add to Favorites",
                    modifier = Modifier.clickable {
                        scope.launch {
                            val favorite = Favorite(
                                id = null,
                                title = quotesItem.author,
                                des = quotesItem.quote
                            )
                            viewModel.Insert(favorite)
                            isFavorite = !isFavorite
                        }
                    }
                )

                Icon(
                    imageVector = Icons.Outlined.Share,
                    contentDescription = "Share Quote",
                    modifier = Modifier.clickable {
                        context.startActivity(Intent.createChooser(intent, "Share Quote"))
                    }
                )
            }
        }
    }
}


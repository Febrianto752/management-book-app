package com.example.managebookapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.managebookapp.AppViewModelProvider
import com.example.managebookapp.Routes
import com.example.managebookapp.components.BookCard
import com.example.managebookapp.data.bookList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@ExperimentalMaterial3Api
@Composable
fun BookListCard(
    navController: NavHostController,
    viewModel: LoginViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
//    var itemsState = viewModel.getAllSignedUser().collectAsState(initial = emptyList())
//    var itemsModel = itemsState.value[0];
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Welcome ") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                withContext(Dispatchers.IO) {
                                    var logged = viewModel.logout()
                                    println("setelah logged")
                                }

                            }
                            println("otw to login page")
                            navController.navigate(Routes.Login.route)
                        }

                    ) {
                        Icon(Icons.Filled.ExitToApp, contentDescription = "logout icon")
                    }
                },

                )
        },
    ) { param ->
        param.toString()
        Column(modifier = Modifier.padding(top = 60.dp)) {
            Text(
                text = "Book List",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .fillMaxWidth()
            )

            LazyVerticalGrid(

                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                content = {
                    items(bookList.count()) { index ->
                        BookCard(navController, bookList[index], index)
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            )
        }

    }

}
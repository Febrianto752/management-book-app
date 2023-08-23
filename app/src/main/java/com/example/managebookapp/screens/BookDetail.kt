package com.example.managebookapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.managebookapp.Routes
import com.example.managebookapp.data.Book
import com.example.managebookapp.data.bookList

@ExperimentalMaterial3Api
@Composable
fun BookDetail(navController: NavHostController, bookIndex: Int?){
    var book: Book? = null
    if (bookIndex != null){
        book = bookList[bookIndex]
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigate(Routes.Home.route) }
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "back icon")
                    }
                },

                )
        },
    ) { param ->
        param.toString()

        if (book != null){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.fillMaxWidth().padding(all = 10.dp).verticalScroll(
                    rememberScrollState()
                )) {
                    Text(
                        text = book.title,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 80.dp, bottom = 40.dp).fillMaxWidth(),
                        fontSize = 32.sp,
                        lineHeight = 36.sp
                    )
                    Image(
                        painter = painterResource(id = book.cover),
                        contentDescription = "book image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(380.dp)
                    )

                    Text(
                        text = "Description : ${book.description}",
                        modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
                    )
                    Text(
                        text = "Author : ${book.author}",
                        modifier = Modifier.padding(bottom = 20.dp)
                    )
                }
            }
        }else{
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .wrapContentSize(Alignment.Center),
            ) {
                Text(text = "Book Data is Not Found !!!")
            }
        }

    }


}

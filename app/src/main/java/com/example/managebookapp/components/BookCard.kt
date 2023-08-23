package com.example.managebookapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.managebookapp.data.Book

@ExperimentalMaterial3Api
@Composable
fun BookCard(navController: NavHostController, book: Book, indexBook: Int) {
    Card(
        onClick = { navController.navigate("BookDetail/$indexBook") },
        modifier = Modifier
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(16.dp)),
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = book.cover),
                    contentDescription = "book image",
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(280.dp)
                )
                Text(
                    book.title,
                    modifier = Modifier.padding(top = 10.dp, start = 16.dp, end = 16.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    "created by \n" + book.author,
                    modifier = Modifier.padding(top = 10.dp, start = 16.dp, end = 16.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}
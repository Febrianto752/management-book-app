package com.example.managebookapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.managebookapp.AppViewModelProvider
import com.example.managebookapp.Greeting
import com.example.managebookapp.R
import com.example.managebookapp.Routes
import com.example.managebookapp.data.User
import com.example.managebookapp.ui.theme.ManageBookAppTheme
import com.example.managebookapp.viewModels.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@ExperimentalMaterial3Api
@Composable
fun Registration(
    navController: NavHostController,
    viewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    println("hello")
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.secondary)
            .padding(all = 20.dp)
    ) {

        Text(
            text = "Register",
            color = Color(0xFFFFFFFFFF),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive
        )

    }

    Column(
        modifier = Modifier.padding(20.dp, top = 80.dp).verticalScroll(scrollState),
//        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        val email = remember { mutableStateOf("") }
        var showToast = remember { mutableStateOf(false) }
        var isError = remember { mutableStateOf(false) }

        var errorMessage = remember { mutableStateOf("") }

        val coroutineScope = rememberCoroutineScope()


        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Name") },
            value = username.value,
            onValueChange = { username.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Email") },
            value = email.value,
            onValueChange = { email.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Password") },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    if (username.value != "" && email.value != "" && password.value != "") {
                        var user = User(
                            name = username.value,
                            email = email.value,
                            password = password.value
                        )

                        coroutineScope.launch {
                            viewModel.createUser(user)
                        }

                        showToast.value = true
                        username.value = ""
                        email.value = ""
                        password.value = ""
                    } else {
                        errorMessage.value = "all fields are required";
                        showToast.value = true;
                        isError.value = true;
                    }


                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Register")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (showToast.value) {
            Snackbar(
                action = {
                    TextButton(onClick = {
                        showToast.value = false; isError.value = false; errorMessage.value = ""
                    }) {
                        Text("Dismiss")
                    }
                }
            ) {
                if (isError.value == true) {
                    Text(errorMessage.value)
                } else {
                    Text("Successfully registered account")
                }

            }
        }

        ClickableText(
            text = text,
            onClick = { offset ->
                text.getStringAnnotations(
                    tag = "URL",
                    start = offset,
                    end = offset
                ).firstOrNull()?.let { annotation ->
                    navController.navigate(Routes.Login.route)
                }
            }
        )

        LazyColumn(modifier = Modifier.padding(top = 100.dp).heightIn(0.dp, 300.dp)) {
            items(viewModel.usersList) { user ->

                Column(){
                    Text(user.name)
                    Text(user.email)
                    Text(user.password)
                    Button(onClick = {
                        coroutineScope.launch{
                            viewModel.deleteUser(user)
                        }
                    }) {
                        Text(text = "${user.name} ${user.email} ${user.password}")
                    }
                }

            }


        }

        Button(onClick = {
            coroutineScope.launch{
                withContext(Dispatchers.IO) {
                    // Operasi database dijalankan di Dispatchers.IO
                    viewModel.deleteAllUser()
                }

            }
        }) {
            Text(text = "Delete All User")
        }
    }
}


val text = AnnotatedString.Builder("Already account? login!")
    .apply {
        addStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            ),
            start = 17, // Start index of the link text
            end = 22   // End index of the link text
        )

        // Add a string annotation for the link click
        addStringAnnotation(
            tag = "URL",
            annotation = "https://www.example.com",
            start = 17,
            end = 22
        )
    }
    .toAnnotatedString()

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun RegistrationPreview() {
    val navController = rememberNavController()
    ManageBookAppTheme {
        Registration(navController)
    }
}
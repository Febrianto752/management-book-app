package com.example.managebookapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.managebookapp.AppViewModelProvider
import com.example.managebookapp.R
import com.example.managebookapp.Routes
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        println("hello")
        coroutineScope.launch {
            println("hello2")
            var itemsState = viewModel.getAllSignedUser();
            itemsState.collect {signedList ->
                println(signedList.count())
                if (signedList.isNotEmpty()){
                    navController.navigate(Routes.Home.route)
                }

            }

        }
    }





    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.secondary)
            .padding(all = 20.dp)
    ) {


        Text(
            text = "Login",
            color = Color(0xFFFFFFFFFF),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive
        )

    }
    Column(
        modifier = Modifier.padding(20.dp, top = 80.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        var showToast = remember { mutableStateOf(false) }

        val coroutineScope = rememberCoroutineScope()
        Image(
            painter = painterResource(id = R.drawable.book),
            contentDescription = "profile image",
            modifier = Modifier
                .size(100.dp)
        )

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

                    coroutineScope.launch {
                        var user = viewModel.login(email.value, password.value)

                        user.collect {user ->
                            if(user == null){
                                showToast.value = true
                            }else{
                                viewModel.insertSignedUser(user)
                                navController.navigate(Routes.Home.route)
                            }
                        }




                    }
                    // Simulate login logic
//                    if (email.value == "admin" && password.value == "admin123") {
//                        navController.navigate(Routes.Home.route)
//                    } else {
//                        showToast.value = true
//                    }

                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Login")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    navController.navigate(Routes.Register.route)

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
                    TextButton(onClick = { showToast.value = false }) {
                        Text("Dismiss")
                    }
                }
            ) {
                Text("Username or password is wrong!")
            }
        }
    }
}
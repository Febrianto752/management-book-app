package com.example.managebookapp

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.managebookapp.screens.LoginViewModel
import com.example.managebookapp.screens.RegistrationViewModel
import com.example.managebookapp.viewModels.UserViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
//        initializer {
//            ItemEditViewModel(
//                this.createSavedStateHandle()
//            )
//        }
//        // Initializer for ItemEntryViewModel
//        initializer {
//            ItemEntryViewModel(inventoryApplication().container.itemsRepository)
//        }
//
//
//        // Initializer for ItemDetailsViewModel
//        initializer {
//            ItemDetailsViewModel(
//                this.createSavedStateHandle()
//            )
//        }
//
        // Initializer for HomeViewModel
        initializer {
            RegistrationViewModel(managementBookApplication().container.usersRepository)
        }

        initializer {
            UserViewModel(managementBookApplication().container.usersRepository)
        }

        // Initializer for HomeViewModel
        initializer {
            LoginViewModel(
                managementBookApplication().container.usersRepository,
                managementBookApplication().container.signedRepository
            )
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.managementBookApplication(): ManagementBookApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ManagementBookApplication)

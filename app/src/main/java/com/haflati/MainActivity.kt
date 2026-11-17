package com.haflati

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.haflati.data.HaflatiDatabase
import com.haflati.data.HaflatiPreferences
import com.haflati.ui.screens.HomeScreen
import com.haflati.ui.screens.PersistentTotalCostBar
import com.haflati.ui.theme.HaflatiTheme
import com.haflati.viewmodel.HaflatiViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = Room.databaseBuilder(
            applicationContext,
            HaflatiDatabase::class.java,
            "haflati_local_db"
        ).build()

        val prefs = HaflatiPreferences(applicationContext)
        val viewModel = HaflatiViewModel(db.cartDao(), db.favoriteDao(), db.orderDao(), prefs)

        setContent {
            val uiState by viewModel.uiState.collectAsState()
            HaflatiTheme(darkTheme = uiState.isDarkMode) {
                Scaffold(
                    bottomBar = {
                        PersistentTotalCostBar(
                            totalCost = uiState.totalCartCost,
                            itemCount = uiState.cartItems.size,
                            isOverBudget = uiState.isOverBudget,
                            currency = uiState.currency,
                            exchangeRate = uiState.exchangeRates[uiState.currency] ?: 1.0,
                            onOpenCart = { /* الانتقال لشاشة السلة */ }
                        )
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        HomeScreen(
                            uiState = uiState,
                            onSearchChange = viewModel::onSearchQueryChange,
                            onStartWizard = { },
                            onEventClick = viewModel::selectEventType,
                            onServiceClick = { }
                        )
                    }
                }
            }
        }
    }
}
package com.emindev.schoolgadgets.main.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.emindev.schoolgadgets.main.common.util.Page
import com.emindev.schoolgadgets.gradecalculator.ui.components.CardPageIcon
import com.emindev.schoolgadgets.main.ui.components.TextWelcome

@Composable
fun MainPage(navController: NavController) {

    Surface(color = MaterialTheme.colorScheme.surface) {


        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {


            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp),
                verticalArrangement = Arrangement.spacedBy(50.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                TextWelcome()
                Divider(modifier = Modifier.padding(vertical = 24.dp, horizontal = 12.dp))
            }

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 160.dp), modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {

                item {
                    CardPageIcon(page = Page.Grade, navController)
                }

            }
        }
    }

}


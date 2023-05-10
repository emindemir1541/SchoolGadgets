package com.emindev.schoolgadgets.gradecalculator.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.emindev.schoolgadgets.main.common.util.Page



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardPageIcon(page: Page, navController: NavController) {
    Card(modifier = Modifier
        .size(160.dp)
        .clickable { navController.navigate(page.name) }) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(painter = painterResource(id = page.icon!!), contentDescription = page.name)
        }
    }
}
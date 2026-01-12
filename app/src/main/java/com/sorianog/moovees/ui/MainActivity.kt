package com.sorianog.moovees.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sorianog.moovees.ui.navigation.AppNavGraph
import com.sorianog.moovees.ui.navigation.Destination
import com.sorianog.moovees.ui.theme.MooveesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MooveesTheme {
                val navController = rememberNavController()
                val startDestination = Destination.LIST

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavBar(navController, startDestination)
                    }
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        AppNavGraph(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavBar(
    navController: NavController,
    startDest: Destination
) {
//    val navController = rememberNavController()
//    val startDestination = Destination.LIST
    var selectedDest by rememberSaveable { mutableIntStateOf(startDest.ordinal) }

    NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
        Destination.entries.forEachIndexed { index, destination ->
            NavigationBarItem(
                selected = selectedDest == index,
                onClick = {
                    navController.navigate(route = destination.route)
                    selectedDest = index
                },
                icon = {
                    Icon(
                        destination.icon,
                        contentDescription = stringResource(destination.contentDesc)
                    )
                },
                label = { Text(stringResource(destination.label)) }
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MooveesTheme {
        Greeting("Android")
    }
}
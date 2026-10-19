package com.example.myapplication.componentes

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue

@Composable
fun nanbar(navController: NavController){
    val items=listOf(
        Rutas.Perfil,
        Rutas.Mensajes,
        Rutas.Asignaturas
    )
    NavigationBar(

    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute= navBackStackEntry?.destination?.route
        items.forEach {
            item-> NavigationBarItem(
                icon = { Icon(item.icon,item.title) },
                label = { Text(item.title) },
                selected = currentRoute==item.route,
                onClick = {
                    if(currentRoute!= item.route){
                        navController.navigate(item.route){
                            popUpTo(navController.graph.startDestinationId){
                                saveState = true
                            }
                            launchSingleTop=true
                            restoreState= true
                        }
                    }
                }
            )
        }
    }
}
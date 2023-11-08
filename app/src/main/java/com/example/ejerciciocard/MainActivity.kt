package com.example.ejerciciocard

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejerciciocard.ui.theme.EjercicioCardTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjercicioCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        Modifier.verticalScroll(
                           rememberScrollState()
                        )
                    ) {
                        MyCard("Tarea 1")
                        Divider()
                        MyCard("Tarea 2")
                    }
                }
            }
        }
    }
}

@Composable
fun BadgeContent(prioridad: String) {
    if(prioridad.equals("Alta")){
        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = Color.Red,
                    shape = CircleShape)
                .padding(4.dp)
        ) {
            Text(
                text = prioridad,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier.padding(4.dp)
            )
        }
    }else if(prioridad.equals("Media")){
        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = Color.Yellow,
                    shape = CircleShape)
                .padding(4.dp)
        ) {
            Text(
                text = prioridad,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.Black
                ),
                modifier = Modifier.padding(4.dp)
            )
        }
    }else{
        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = Color.Gray,
                    shape = CircleShape)
                .padding(4.dp)
        ) {
            Text(
                text = prioridad,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.Black
                ),
                modifier = Modifier.padding(4.dp)
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCard(titulo: String) {
    val listaPrioridad = listOf("Alta", "Media", "Baja")
    val completado = listOf("Completada", "No Completada")

    var selectedText by rememberSaveable {
        mutableStateOf(listaPrioridad.component3())
    }

    var selectedText2 by rememberSaveable {
        mutableStateOf(completado.component2())
    }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { }
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Cyan,
        )
    ) {
        Column(
        ) {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = titulo,
                    fontSize = 30.sp
                )
                Box(
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 60.dp, 0.dp)
                ) {
                    @Composable
                    fun MyBadgeBox2() {
                        BadgedBox(badge = { BadgeContent(selectedText) }) {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    MyBadgeBox2()
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
               Text(
                   text = selectedText2.toString(),
                   fontSize = 30.sp
               )
            }

            Column(
                Modifier.padding(66.dp)
            ) {
                @Composable
                fun MyDropDownMenu(list: List<String>) {

                    var expanded by rememberSaveable {
                        mutableStateOf(false)
                    }
                    Column(
                        Modifier.padding(20.dp)
                    ) {
                        OutlinedTextField(
                            value = selectedText,
                            onValueChange = { selectedText = it },
                            enabled = false,
                            readOnly = true,
                            modifier = Modifier
                                .clickable { expanded = true }
                                .fillMaxWidth()
                        )
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            list.forEach { lista ->
                                DropdownMenuItem(text = { Text(text = lista) },
                                    onClick = {
                                        expanded = false
                                        selectedText = lista
                                    })
                            }
                        }
                    }
                }

                @Composable
                fun MyDropDownMenu2(list: List<String>) {

                    var expanded by rememberSaveable {
                        mutableStateOf(false)
                    }
                    Column(
                        Modifier.padding(20.dp)
                    ) {
                        OutlinedTextField(
                            value = selectedText2,
                            onValueChange = { selectedText2 = it },
                            enabled = false,
                            readOnly = true,
                            modifier = Modifier
                                .clickable { expanded = true }
                                .fillMaxWidth()
                        )
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            list.forEach { lista ->
                                DropdownMenuItem(text = { Text(text = lista) },
                                    onClick = {
                                        expanded = false
                                        selectedText2 = lista
                                    })
                            }
                        }
                    }
                }
                MyDropDownMenu(listaPrioridad)
                MyDropDownMenu2(completado)
            }
        }
    }
}

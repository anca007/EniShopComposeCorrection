package com.example.enishopcomposecorrection.ui.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.enishopcomposecorrection.ui.common.FormRowSurface
import com.example.enishopcomposecorrection.ui.common.FormTextRow
import com.example.enishopcomposecorrection.ui.common.TopBar


@Composable
fun ArticleFormScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    isDarkThemeActivated: Boolean,
    onDarkThemeToggle: (Boolean) -> Unit
) {
    val contextForToast = LocalContext.current.applicationContext
    Scaffold(
        topBar = { TopBar(
            navController = navController,
            isDarkThemeActivated = isDarkThemeActivated,
            onDarkThemeToggle = onDarkThemeToggle
        ) },

        ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(it)
        ) {
            ArticleForm()
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    Toast.makeText(
                        contextForToast,
                        "L'article a été ajouté",
                        Toast.LENGTH_LONG
                    ).show()
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Enregistrer")
            }
        }
    }
}


@Composable
fun ArticleForm(
    modifier: Modifier = Modifier
) {

    var name by rememberSaveable {
        mutableStateOf("")
    }
    var description by rememberSaveable {
        mutableStateOf("")
    }
    var price by rememberSaveable {
        mutableStateOf("")
    }

    Column {
        FormTextRow(
            label = "Titre",
            value = name,
            onValueChange = {
                name = it
            }
        )
        FormTextRow(
            label = "Description",
            value = description,
            onValueChange = {
                description = it
            }
        )
        FormTextRow(
            label = "Prix",
            value = price,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            onValueChange = {
                price = it
            }
        )
        DropdownCategories()
    }
}

@Composable
fun DropdownCategories() {

    val categories = listOf("electronics", "jewelery", "men's clothing", "women's clothing")

    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedText by rememberSaveable { mutableStateOf("Choisir une catégorie") }

    FormRowSurface {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Catégorie", Modifier.padding(top = 4.dp, bottom = 4.dp), fontSize = 24.sp)
            TextField(
                value = selectedText,
                onValueChange = {},
                enabled = false,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.clickable { expanded = !expanded }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        expanded = !expanded
                    }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                categories.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item.replaceFirstChar { it.uppercase() }) },
                        onClick = {
                            selectedText = item
                            expanded = false
                        })
                }
            }
        }
    }
}

@Preview
@Composable
fun ArticleFormPreview() {
    ArticleForm()
}
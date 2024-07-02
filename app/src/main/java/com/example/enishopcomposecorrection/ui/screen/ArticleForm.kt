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
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.enishopcomposecorrection.EniShopDestination
import com.example.enishopcomposecorrection.EniShopHome
import com.example.enishopcomposecorrection.ui.common.FormRowSurface
import com.example.enishopcomposecorrection.ui.common.FormTextRow
import com.example.enishopcomposecorrection.ui.common.TopBar
import com.example.enishopcomposecorrection.vm.ArticleFormViewModel


@Composable
fun ArticleFormScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    isDarkThemeActivated: Boolean,
    onDarkThemeToggle: (Boolean) -> Unit,
    articleFormViewModel: ArticleFormViewModel = viewModel(
        factory = ArticleFormViewModel.Factory
    ),
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
            ArticleForm(articleFormViewModel = articleFormViewModel)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    articleFormViewModel.addArticle()
                    Toast.makeText(
                        contextForToast,
                        "L'article a été ajouté",
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate(EniShopHome.route)
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
    modifier: Modifier = Modifier,
    articleFormViewModel : ArticleFormViewModel
) {

    val name by articleFormViewModel.name.collectAsState()
    val description by articleFormViewModel.description.collectAsState()
    val price by articleFormViewModel.price.collectAsState()

    Column {
        FormTextRow(
            label = "Titre",
            value = name,
            onValueChange = {
                articleFormViewModel.setName(it)
            }
        )
        FormTextRow(
            label = "Description",
            value = description,
            onValueChange = {
                articleFormViewModel.setDescription(it)
            }
        )
        FormTextRow(
            label = "Prix",
            value = price,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            onValueChange = {
                articleFormViewModel.setPrice(it)
            }
        )
        DropdownCategories(articleFormViewModel =  articleFormViewModel)
    }
}

@Composable
fun DropdownCategories(articleFormViewModel : ArticleFormViewModel) {

    val categories by articleFormViewModel.categories.collectAsState()

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
   // ArticleForm()
}
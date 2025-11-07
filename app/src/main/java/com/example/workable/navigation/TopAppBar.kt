package com.workable.mobile.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.unit.dp
import com.workable.mobile.ui.theme.Text
import androidx.compose.foundation.layout.RowScope

@Composable
fun WorkAbleTopBar(title: String, actions: @Composable RowScope.() -> Unit = {}) {
    TopAppBar(
        title = { Text(text = title) },
        actions = actions,
        navigationIcon = {
            IconButton(onClick = { /* open drawer if any */ }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        modifier = Modifier.semantics { contentDescription = "Top navigation bar" },
        colors = TopAppBarDefaults.smallTopAppBarColors()
    )
}

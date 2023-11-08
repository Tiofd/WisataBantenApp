package com.bangkit.wisatabanten.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.wisatabanten.R
import com.bangkit.wisatabanten.ui.theme.WisataBantenTheme

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(top = 64.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.author_pict),
            contentDescription = "Author Image",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(200.dp)
                .clip(CircleShape)
        )

        Text(
            text = stringResource(R.string.author_name),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(top = 16.dp)
        )
        Text(
            text = stringResource(R.string.author_email),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal,
            modifier = modifier.padding(top = 8.dp)
        )
    }
}

@Preview (showBackground = true)
@Composable
fun ProfileScreenPreview(){
    WisataBantenTheme {
        ProfileScreen()
    }
}

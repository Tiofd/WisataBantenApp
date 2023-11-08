package com.bangkit.wisatabanten.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.wisatabanten.R
import com.bangkit.wisatabanten.ui.theme.WisataBantenTheme

@Composable
fun CardTour(
    image: Int,
    title: String,
    modifier: Modifier = Modifier,
) {

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.shadow(elevation = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(ShapeDefaults.Medium)
                    .shadow(elevation = 4.dp)
            )
            Column(modifier = Modifier.padding(start = 16.dp).weight(1f)) {
                Text(
                    text = title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CardTourPreview() {
    WisataBantenTheme {
        CardTour(
            image = R.drawable.ujung_kulon,
            title = "Taman Nasional Ujung Kulon",
        )
    }
}
package com.uraga.moviestmdb.presentation.movies_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import com.uraga.moviestmdb.R
import com.uraga.moviestmdb.ui.theme.Shapes

@Composable
fun MovieItem(
    urlImage: String,
    placeholder: Int = R.drawable.logo,
    contentDescription: String,
) {
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(
        model = urlImage,
        imageLoader = ImageLoader.Builder(context)
            .components {
                add(SvgDecoder.Factory())
            }
            .placeholder(placeholder)
            .build()
    )
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(156.dp, 205.dp),
        elevation = 4.dp,
        shape = Shapes.medium
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun PreviewMovieItem() {
    MovieItem(
        urlImage = "https://image.tmdb.org/t/p/w500/uJYYizSuA9Y3DCs0qS4qWvHfZg4.jpg",
        contentDescription = ""
    )
}
package com.uraga.moviestmdb.presentation.movies_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uraga.moviestmdb.BuildConfig
import com.uraga.domain.models.Movie

@Composable
fun SectionHorizontal(
    movieList: List<Movie>
) {
    Column {
        Text(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 16.dp),
            text = "Próximos estrenos",
            fontSize = 20.sp,
            fontWeight = FontWeight(600),
            lineHeight = 30.sp
        )
        LazyRow(modifier = Modifier, contentPadding = PaddingValues(start = 8.dp, end = 8.dp)) {
            itemsIndexed(movieList) { index, item ->
                MovieItem(
                    urlImage = BuildConfig.URL_IMG + item.posterPath,
                    contentDescription = item.title
                )
            }
        }
    }

}

@Preview
@Composable
fun PreviewMovieList() {
    SectionHorizontal(
        movieList = listOf(
            Movie(
                title = "",
                posterPath = "https://image.tmdb.org/t/p/w500/uJYYizSuA9Y3DCs0qS4qWvHfZg4.jpg"
            )
        )
    )
}

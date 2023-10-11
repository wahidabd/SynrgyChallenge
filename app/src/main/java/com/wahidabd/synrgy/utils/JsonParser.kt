package com.wahidabd.synrgy.utils

import android.content.Context
import com.wahidabd.synrgy.R
import com.wahidabd.synrgy.domain.Genre
import com.wahidabd.synrgy.domain.Movie
import org.json.JSONArray
import org.json.JSONObject


/**
 * Created by Wahid on 10/11/2023.
 * Github github.com/wahidabd.
 */


object JsonParser {

    fun getListMovie(context: Context, id: Long): List<Movie> {
        val result = mutableListOf<Movie>()
        val json =
            context.resources.openRawResource(R.raw.movies).bufferedReader().use { it.readText() }

        try {
            val jsonObject = JSONObject(json)
            val movies = jsonObject.getJSONArray("results")

            for (i in 0 until movies.length()) {
                val jsonMovie = movies.getJSONObject(i)
                val id = jsonMovie.getLong("id")
                val title = jsonMovie.getString("title")
                val posterPath = jsonMovie.getString("poster_path")
                val genreIds = jsonArrayToList(jsonMovie.getJSONArray("genre_ids"))

                val movie = Movie(
                    id = id,
                    poster_path = posterPath,
                    title = title,
                    genre_ids = genreIds
                )

                result.add(movie)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return result.filter { movie -> movie.genre_ids[0] == id }
    }

    private fun jsonArrayToList(jsonArray: JSONArray): List<Long> {
        val list = mutableListOf<Long>()
        for (i in 0 until jsonArray.length()) {
            list.add(jsonArray.getLong(i))
        }
        return list
    }

    fun getAllGenres(context: Context): List<Genre>{
        val result = mutableListOf<Genre>()
        val json = context.resources.openRawResource(R.raw.genres).bufferedReader().use { it.readText() }

        try {
            val jsonObject = JSONObject(json)
            val genres = jsonObject.getJSONArray("genres")

            for (i in 0 until genres.length()){
                val jsonGenre = genres.getJSONObject(i)
                val id = jsonGenre.getLong("id")
                val name = jsonGenre.getString("name")
                val image = jsonGenre.getString("image")

                val genre = Genre(id, name, image)
                result.add(genre)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }

        return result
    }

}
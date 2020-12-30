package com.example.android4a.Retrofit

import com.example.android4a.data.local.models.Pokedex
import io.reactivex.Observable
import retrofit2.http.GET

interface IPokemonList {
    @get:GET("pokedex.json")
    val listPokemon:Observable<Pokedex>
}
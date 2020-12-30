package com.example.android4a.presentation.pokedex

import  android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android4a.presentation.Adapter.PokemonListAdapter
import com.example.android4a.data.local.Common.Common
import com.example.android4a.data.local.Common.ItemOffsetDecoration
import com.example.android4a.R
import com.example.android4a.domain.Retrofit.IPokemonList
import com.example.android4a.domain.Retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PokemonList : Fragment() {

    internal var compositeDisposable = CompositeDisposable()
    internal var iPokemonList: IPokemonList

    internal lateinit var recyclerView: RecyclerView

    init {
        val retrofit = RetrofitClient.instance
        iPokemonList = retrofit.create(IPokemonList::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val itemView = inflater.inflate(R.layout.fragment_pokemon_list, container, false)


        recyclerView = itemView.findViewById(R.id.pokemon_recyclerview) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(activity,2)
        val itemDecoration =
            ItemOffsetDecoration(
                activity!!,
                R.dimen.spacing
            )
        recyclerView.addItemDecoration(itemDecoration)

        fetchData()


        return itemView
    }

    private fun fetchData() {
        compositeDisposable.add(iPokemonList.listPokemon
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { pokemonDex ->
                Common.pokemonList = pokemonDex.pokemon!!
                val adapter =
                    PokemonListAdapter(
                        activity!!,
                        Common.pokemonList
                    )

                recyclerView.adapter = adapter
            }

        )
    }
}
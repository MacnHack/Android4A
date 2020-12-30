package com.example.android4a.presentation.pokedex

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.android4a.R
import com.example.android4a.data.local.Common.Common
import kotlinx.android.synthetic.main.pokedex.*

class PokedexActivity : AppCompatActivity() {

    //Create BroadCast Handle
    private val showDetail = object: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent!!.action!!.toString() == Common.KEY_ENABLE_HOME){
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                supportActionBar!!.setDisplayShowHomeEnabled(true)

                //Replace Fragment
                val detailFragment = PokemonDetail.getInstance()
                val position = intent.getIntExtra("position", -1)
                val bundle = Bundle()
                bundle.putInt("position", position)
                detailFragment.arguments = bundle

                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.list_pokemon_fragment, detailFragment)
                fragmentTransaction.addToBackStack("detail")
                fragmentTransaction.commit()

                //Set Pokemon Name for Toolbar
                val pokemon = Common.pokemonList[position]
                toolbar.title = pokemon.name
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokedex)

        toolbar.setTitle("POKEDEX")
        setSupportActionBar(toolbar)

        //Register Broadcast
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(showDetail, IntentFilter(Common.KEY_ENABLE_HOME))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                toolbar.title = "POKEDEX"

                //Clear all fragment in stack with name 'detail'
                supportFragmentManager.popBackStack("detail", FragmentManager.POP_BACK_STACK_INCLUSIVE)

                supportActionBar!!.setDisplayShowHomeEnabled(false)
                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            }
        }
        return true
    }
}
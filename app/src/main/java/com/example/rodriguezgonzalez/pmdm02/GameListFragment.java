package com.example.rodriguezgonzalez.pmdm02;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rodriguezgonzalez.pmdm02.databinding.GameListFragmentBinding;

import java.util.ArrayList;

/**
 * Esta clase implementa la lógica del fragment que contiene
 * la lista de personajes de nuestra app y que se cargará en
 * un recyclerView.
 */

public class GameListFragment extends Fragment {

    //Variables de clase
    private GameListFragmentBinding binding;
    private ArrayList<GameData> character;
    private RecyclerViewAdapter adapter;

    /**
     * Crea y devuelve la vista asociada a este fragmento.
     *
     * @param inflater           Objeto utilizado para inflar la vista del fragmento.
     * @param container          Contenedor al que se añadirá la vista del fragmento.
     * @param savedInstanceState Si no es null, contiene el estado previamente guardado del fragmento.
     * @return La raíz de la vista inflada para este fragmento, o null si no se puede crear.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Inflar el layot utilizando el binding
        binding = GameListFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    /**
     * Método llamado después de que la vista del fragmento ha sido creada.
     * Aquí se inicializan los componentes de la interfaz de usuario,
     * se cargan los datos de la lista de juegos y se configura el RecyclerView.
     *
     * @param view               Vista asociada a este fragmento.
     * @param savedInstanceState Si no es null, contiene el estado previamente guardado del fragmento.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Inicializa la lista de juegos
        loadItems();

        //Configurar el RecyclerView
        adapter = new RecyclerViewAdapter(character, getActivity());
        binding.gamesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.gamesRecyclerView.setAdapter(adapter);

    }

    /**
     * Método privado de la clase que inserta los datos de los personajes en un ArrayList.
     */
    private void loadItems() {
        character = new ArrayList<GameData>();
        // Mario
        character.add(new GameData(
                getString(R.string.mario),
                getString(R.string.mario_description),
                R.drawable.mario,
                getString(R.string.mario_skills)));

        // Luigi
        character.add(new GameData(
                getString(R.string.luigi),
                getString(R.string.luigi_description),
                R.drawable.luigi,
                getString(R.string.luigi_skills)));

        // Peach
        character.add(new GameData(
                getString(R.string.princess_peach),
                getString(R.string.princess_peach_description),
                R.drawable.peach,
                getString(R.string.princess_peach_skills)));

        // Bowser
        character.add(new GameData(
                getString(R.string.bowser),
                getString(R.string.bowser_description),
                R.drawable.bowser,
                getString(R.string.bowser_skills)));

        // Daisy
        character.add(new GameData(
                getString(R.string.daisy),
                getString(R.string.daisy_description),
                R.drawable.daisy,
                getString(R.string.daisy_skills)));

        // Toad
        character.add(new GameData(
                getString(R.string.toad),
                getString(R.string.toad_description),
                R.drawable.toad2,
                getString(R.string.toad_skills)));

        // Yoshi
        character.add(new GameData(
                getString(R.string.yoshi),
                getString(R.string.yoshi_description),
                R.drawable.yoshi,
                getString(R.string.yoshi_skills)));

        // Wario
        character.add(new GameData(
                getString(R.string.wario),
                getString(R.string.wario_description),
                R.drawable.wario,
                getString(R.string.wario_skills)));

        // Waluigi
        character.add(new GameData(
                getString(R.string.waluigi),
                getString(R.string.waluigi_description),
                R.drawable.waluigi,
                getString(R.string.waluigi_skills)));
        // Rosalina
        character.add(new GameData(
                getString(R.string.rosalina),
                getString(R.string.rosalina_description),
                R.drawable.rosalina,
                getString(R.string.rosalina_skills)));

        // Bowser Jr.
        character.add(new GameData(
                getString(R.string.bowser_jr),
                getString(R.string.bowser_jr_description),
                R.drawable.bowserjr,
                getString(R.string.bowser_jr_skills)));

        // Boo
        character.add(new GameData(
                getString(R.string.boo),
                getString(R.string.boo_description),
                R.drawable.boo,
                getString(R.string.boo_skills)));

        // Donkey Kong
        character.add(new GameData(
                getString(R.string.donkey_kong),
                getString(R.string.donkey_kong_description),
                R.drawable.donkeykong,
                getString(R.string.donkey_kong_skills)));

        // Diddy Kong
        character.add(new GameData(
                getString(R.string.diddy_kong),
                getString(R.string.diddy_kong_description),
                R.drawable.diddykong,
                getString(R.string.diddy_kong_skills)));

    }

    /**
     * Método que se ejecuta antes de que el fragento se haga visible al usuario.
     * En este caso, se utiliza para cambiar el título de la Action Bar.
     */
    @Override
    public void onStart() {
        super.onStart();
        //Cambia el título del ActionBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.character_list);

        }
    }
}
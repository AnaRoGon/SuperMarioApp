package com.example.rodriguezgonzalez.pmdm02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rodriguezgonzalez.pmdm02.databinding.GameCardviewBinding;

import java.util.List;

/**
 * Esta clase actúa como adaptador para la implementación de nuestro RecyclerView.
 * Nos ayuda a conectar nuestros datos con la vista.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<GameViewHolder> {
    //Variables de clase
    private final List<GameData> items;
    private final Context context;

    public RecyclerViewAdapter(List<GameData> items, Context context) {
        this.items = items;
        this.context = context;
    }

    /**
     * Crea un nuevo objeto de tipo GameViewHolder y lo infla a partir de un diseño del xml de game_CardView.
     * Este método es llamado por el RecyclerView.Adapter cuando necesita crear una nueva vista
     * para mostrar un elemento en el RecyclerView.
     *
     * @param parent   El ViewGroup que será el contenedor de la vista creada.
     * @param viewType Un entero que representa el tipo de vista.
     * @return Un nuevo objeto GameViewHolder, que contiene la vista inflada.
     */
    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GameCardviewBinding binding = GameCardviewBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new GameViewHolder(binding);
    }


    /**
     * Método encargado de vincular los datos del personaje en una posición específica con el `ViewHolder`.
     * Este método es llamado por el RecyclerView.Adapter para actualizar la vista de un elemento en la lista
     * en función de la posición del mismo.
     *
     * @param holder   El GameViewHolder que contiene las vistas del personaje a mostrar.
     * @param position La posición del elemento dentro del conjunto de datos que debe ser mostrada.
     */
    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        GameData currentItem = this.items.get(position);
        holder.bind(currentItem);
        //Manejar el evento de clic en el elemento
        holder.itemView.setOnClickListener(view -> itemClicked(currentItem, view));
    }

    /**
     * Método que devuelve el total de elementos en la lista.
     *
     * @return el número total de elementos en la lista.
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Evento de clic que se ejecuta cuando se hace clic en un elemento de la lista.
     * Este evento se maneja en el Main Activity.
     */
    private void itemClicked(GameData currentCharacter, View view) {
        // Llamamos a la MainActivity para gestionar el evento de click
        ((MainActivity) context).itemClicked(currentCharacter, view);
    }
}

package com.example.rodriguezgonzalez.pmdm02;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rodriguezgonzalez.pmdm02.databinding.GameCardviewBinding;

/**
 * Esta clase sirve de contenedor
 * para guardar cada uno de los elementos visibles
 * de nuestra lista de personajes.
 */
public class GameViewHolder extends RecyclerView.ViewHolder {

    private final GameCardviewBinding binding;

    /**
     * Constructor para el bindeo de la clase GameCardviewBinding.
     *
     * @param binding El objeto GameCardviewBinding asociado a este ViewHolder.
     */
    public GameViewHolder(GameCardviewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    /**
     * Método para vincular los datos del objeto GameData al ViewHolder.
     *
     * @param character El objeto GameData que contiene los datos a vincular.
     */
    public void bind(GameData character) {
        binding.name.setText(character.getName());
        binding.characterDescription.setText(character.getDescription());
        binding.image.setImageResource(character.getImage());
        binding.executePendingBindings(); //Asegura que se apliquen los cambios de inmediato
    }
}

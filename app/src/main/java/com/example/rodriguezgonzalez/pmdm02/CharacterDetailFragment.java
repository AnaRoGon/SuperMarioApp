package com.example.rodriguezgonzalez.pmdm02;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rodriguezgonzalez.pmdm02.databinding.CharacterDetailFragmentBinding;

import java.util.Objects;

/**
 * Esta clase implementa la lógica del fragment que contiene
 * los detalles del personaje seleccionado.
 */

public class CharacterDetailFragment extends Fragment {
    //Variable que aprovecha el bindeo para simplificar el acceso a los elementos de la vista
    private CharacterDetailFragmentBinding binding;

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Infla el layout para este fragment
        binding = CharacterDetailFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Método llamado después de que la vista del fragmento ha sido creada.
     * Aquí se inicializan los componentes de la interfaz de usuario y se escriben los datos obtenidos del bundle.
     *
     * @param view               Vista asociada a este fragmento.
     * @param savedInstanceState Si no es null, contiene el estado previamente guardado del fragmento.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Se obtienen los datos del bundle
        if (getArguments() != null) {
            String name = getArguments().getString("name");
            String description = getArguments().getString("description");
            String image = getArguments().getString("image");
            String skills = getArguments().getString("skills");

            //Se asignan los datos de los componentes
            binding.textViewName.setText(name);
            binding.textViewDescription.setText(description);
            binding.imageView.setImageResource(Integer.parseInt(image));
            binding.textViewDescriptionSkills.setText(skills);
        }
        //Toast para informar al usuario del personaje seleccionado
        Toast.makeText(requireContext(), getString(R.string.message_info_about_character) + binding.textViewName.getText(), Toast.LENGTH_SHORT).show();
    }

    /**
     * Método que se ejecuta antes de que el fragento se haga visible al usuario.
     * En este caso, se utiliza para cambiar el título de la Action Bar.
     */
    @Override
    public void onStart() {

        super.onStart();
        //Cambia el titulo de la Action Bar
        if (getActivity() != null) {
            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle(R.string.character_details);
        }
    }
}
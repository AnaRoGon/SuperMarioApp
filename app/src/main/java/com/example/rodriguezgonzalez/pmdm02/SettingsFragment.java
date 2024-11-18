package com.example.rodriguezgonzalez.pmdm02;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.rodriguezgonzalez.pmdm02.databinding.SettingsFragmentBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.Locale;
import java.util.Objects;


/**
 * Esta clase implementa la lógica de la pantalla de ajustes.
 */
public class SettingsFragment extends Fragment {
    //Variable que aprovecha el bindeo para simplificar el acceso a los elementos de la vista
    private SettingsFragmentBinding binding;

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
        //Infla el layout para este fragment
        binding = SettingsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Método llamado después de que la vista del fragmento ha sido creada.
     * Aquí se inicializan los componentes de la interfaz de usuario y se manejan los eventos
     * necesarios para el cambio de idioma.
     *
     * @param view               Vista asociada a este fragmento.
     * @param savedInstanceState Si no es null, contiene el estado previamente guardado del fragmento.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Se obtiene el estado en el que quedo el radioButtom por medio del uso de la clase PreferencesHelper
        int radioButtonId = PreferencesHelper.getRadioButtomState(requireContext());
        if (radioButtonId != -1) {
            binding.radioGroupLanguage.check(radioButtonId);
        }
        //Crea la pantalla de ajustes
        super.onViewCreated(view, savedInstanceState);

        //Listener para implementar el cambio de idioma según el radioButton seleccionado
        binding.radioGroupLanguage.setOnCheckedChangeListener(this::selectedLanguage);
    }

    /**
     * Evento que se ejecuta cuando se selecciona un radio button en el grupo de botones.
     * Si el radio button seleccionado es el inglés, se cambia el idioma a inglés.
     * Si el radio button seleccionado es el español, se cambia el idioma a español.
     * Se guarda la preferencia de idioma en la clase PreferencesHelper.
     *
     * @param radioGroup El grupo de botones en el que se seleccionó el radio button.
     * @param id         El id del radio button seleccionado.
     */
    private void selectedLanguage(RadioGroup radioGroup, int id) {
        if (id == R.id.radioButton_en) {
            changeLanguage("en");
            binding.radioButtonEn.setChecked(true);
        } else {
            changeLanguage("es");
            binding.radioButtonEs.setChecked(true);
        }
        //Se guarda la preferencia de idioma
        PreferencesHelper.saveLanguagePreference(requireContext(), binding.radioGroupLanguage.getCheckedRadioButtonId()
                == R.id.radioButton_en ? "en" : "es");
        //Se guarda el botón seleccionado para mantener la lógica de uso
        PreferencesHelper.saveRadioButtomState(requireContext(), binding.radioGroupLanguage.getCheckedRadioButtonId());
    }

    /**
     * Método para cambiar el idioma de la aplicación por medio de la configuración local.
     * Actualiza también el idioma de la pantalla en la que estamos. Incluido el título de la Action Bar
     * y los elementos del Navigation Drawer.
     *
     * @param languageSelection Idioma seleccionado por el usuario.
     */
    private void changeLanguage(String languageSelection) {

        //Cambia idioma utilizando la configuración local
        Locale locale = new Locale(languageSelection);
        Locale.setDefault(locale);

        //Nueva configuración
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());

        //Actualiza el idioma de la pantalla en la que estamos
        binding.textViewLanguage.setText(R.string.choose_language);
        binding.radioButtonEn.setText(R.string.language_en);
        binding.radioButtonEs.setText(R.string.language_es);

        // Actualiza el idioma del título de la Action Bar y el navigation Drawer llamando al método updateNavigationDrawerText
        if (getActivity() != null) {
            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle(R.string.settings_title);
            NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
            if (navigationView != null) {
                updateNavigationDrawerText(navigationView);
            }
        }
    }

    /**
     * Método para actualizar el texto del Navigation Drawer según el idioma seleccionado.
     *
     * @param navigationView El Navigation Drawer a actualizar.
     */
    private void updateNavigationDrawerText(NavigationView navigationView) {
        // Accede a los elementos del Navigation Drawer y los actualiza según el idioma
        Menu menu = navigationView.getMenu();

        // Actualiza los elementos del menú con los textos del idioma actual
        MenuItem item1 = menu.findItem(R.id.nav_home);
        if (item1 != null) {
            item1.setTitle(R.string.home);
        }

        MenuItem item2 = menu.findItem(R.id.nav_settings);
        if (item2 != null) {
            item2.setTitle(R.string.settings);
        }
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
            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle(R.string.settings_title);
        }
    }

}
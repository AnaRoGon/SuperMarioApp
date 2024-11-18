package com.example.rodriguezgonzalez.pmdm02;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.rodriguezgonzalez.pmdm02.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

/**
 * Esta clase implementa la lógica principal de la aplicación.
 * Se trata de la Activity principal que se nos abrirá por defecto y contendrá
 * los fragmentos de la aplicación.
 * DAM. Curso 2024-2025
 * Módulo: PMDM
 * Unidad 2: Interfaz de usuario.
 * Título de la tarea: Aplicación Android de Super Mario Bros.
 *
 * @author Ana Rodríguez González
 * @version 1.0 Fecha: 18-11-2024
 */

public class MainActivity extends AppCompatActivity {
    //Variables de clase
    private ActivityMainBinding binding; //bindeo para acceder a los componentes de la pantalla
    private ActionBarDrawerToggle toggle; // Hamburguesa del menú lateral
    private NavController navController; //Para manejar la nevagación en la app

    /**
     * Método llamado al crear la actividad.
     * Configura los elementos iniciales de la actividad, como la actualización del idioma,
     * la navegación y los menús, y establece el contenido de la vista.
     *
     * @param savedInstanceState Si no es null, contiene el estado previamente guardado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Actualiza el idioma obtenido de la clase PreferencesHelper
        updateLanguage();
        super.onCreate(savedInstanceState);

        // Infla la actividad principal
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configura del controlador de navegación.
        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }

        //Listener para controlar la flecha de atrás
        navController.addOnDestinationChangedListener(this::onChangeView);

        //Configura navegación del menú lateral
        configureNavigationMenu();
        //Configura la hamburguesa del menú lateral
        configureToggleMenu();

        // Configura la flecha de retroceso de la Actionbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Mensaje Snackbar de bienvenida al iniciar la aplicación
        Snackbar.make(binding.getRoot(), R.string.welcome_message, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * Método para actualizar la configuración de idioma de la aplicación
     * recuperando la preferencia de idioma de la clase PreferencesHelper.
     */
    private void updateLanguage() {
        //Obtiene la preferencia de idioma
        String languagePreference = PreferencesHelper.getLanguagePreference(this);
        Locale locale = new Locale(languagePreference);
        Locale.setDefault(locale);
        //Establece la nueva configuración de idioma
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
    }

    /**
     * Método para configurar la hamburguesa del menú lateral.
     */
    private void configureToggleMenu() {

        // Configurar el ActionBarDrawerToggle
        toggle = new ActionBarDrawerToggle(
                this,
                binding.main,
                R.string.open_drawer,
                R.string.close_drawer
        );
        binding.main.addDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * Método para configurar la navegación del menú lateral.
     * Si está seleccionado el elemento de la página principal, se navega a la página principal.
     * Si se selecciona el elemento de configuración, se navega al fragment de configuración.
     */
    private void configureNavigationMenu() {

        NavigationUI.setupWithNavController(binding.navView, navController);
        //Manejar la selección de los elementos del menú
        binding.navView.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.nav_home) {
                navController.navigate(R.id.gameListFragment); //Navega al fragment de la lista de juegos
            } else if (menuItem.getItemId() == R.id.nav_settings) {
                navController.navigate(R.id.settingsFragment); //Navega al fragment de configuración
            }
            binding.main.closeDrawer(GravityCompat.START); //cierra el menu
            return true;
        });
    }

    /**
     * Evento que se dispara cuando cambia la vista de destino en el controlador de navegación (NavController).
     * Este método actualiza el estado del indicador del menú lateral dependiendo
     * de la vista a la que se navegue (navDestination). Si se navega a un fragmento de detalles del personaje,
     * se desactiva el indicador del menú lateral; de lo contrario, se habilita.
     *
     * @param navController  El controlador de navegación que gestiona las acciones de navegación.
     * @param navDestination El destino de navegación actual al que se ha navegado.
     * @param bundle         Los datos adicionales que se pueden pasar durante la navegación, o null si no hay datos.
     */
    private void onChangeView(NavController navController, NavDestination navDestination, Bundle bundle) {

        if (toggle == null) return;
        if (navDestination.getId() == R.id.characterDetailFragment) {
            toggle.setDrawerIndicatorEnabled(false);
        } else {
            toggle.setDrawerIndicatorEnabled(true);
        }
    }

    /**
     * Método que infla el menú de opciones en la Action Bar.
     * Este método es llamado cuando se crea el menú de opciones para la actividad o fragmento,
     * y se utiliza para cargar los elementos de menú definidos en el archivo xml del menu contextual.
     *
     * @param menu El objeto de tipo Menu en el que se inflará el menú.     *
     * @return true para indicar que el menú se ha creado correctamente.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contextual_menu, menu);
        return true;
    }

    /**
     * Método para manejar la selección de elementos el menú.
     *
     * @param item El elemento del menú seleccionado.
     * @return true si se ha manejado la selección, false en caso contrario.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.about) {
            showAboutDialog();  // Llamada al método que muestra el diálogo
            return true;
        } else if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Método para mostrar el diálogo "Acerca de".
     * Implementa un título, un mensaje predeterminado,
     * un icono personalizado, un botón de OK y se muestra.
     */

    private void showAboutDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.about_app)
                .setMessage(R.string.app_info)
                .setIcon(R.drawable.inf)
                .setPositiveButton("OK", null)
                .show();
    }


    /**
     * Evento que se dispara cuando se hace clic en un elemento de la lista.
     * Recibe el objeto GameData y la vista del elemento clickeado y direcciona
     * a la pantalla de detalles del juego.
     *
     * @param character que representa el personaje que se ha clickeado.
     * @param view      La vista del elemento clickeado.
     */
    public void itemClicked(GameData character, View view) {
        // Creamos un bundle para pasar los datos del juego
        Bundle bundle = new Bundle();
        bundle.putString("name", character.getName());
        bundle.putString("description", character.getDescription());
        bundle.putString("image", Integer.toString(character.getImage()));
        bundle.putString("skills", character.getSkills());

        //Navegar al GameDetailsFragment
        Navigation.findNavController(view).navigate(R.id.characterDetailFragment, bundle);
    }

    /**
     * Método para manejar la navegación hacia atrás.
     *
     * @return true si se ha realizado la navegación hacia atrás, false en caso contrario.
     */
    @Override
    public boolean onSupportNavigateUp() {
        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        //Controlamos que exista un fragment al que se ha navegado para volver atrás
        if (navHostFragment != null) {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            return NavigationUI.navigateUp(navController, binding.main)
                    || super.onSupportNavigateUp();
        }
        return super.onSupportNavigateUp();
    }

}
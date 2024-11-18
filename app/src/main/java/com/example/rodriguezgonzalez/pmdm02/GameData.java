package com.example.rodriguezgonzalez.pmdm02;

/**
 * Esta clase implementa un objeto de tipo GameData que contendrá
 * los datos que necesitaremos cargar en el fragmento de detalles
 * del personaje y la lista de personajes de la pantalla principal
 * de nuestra aplicación.
 */
public class GameData {

    //Variables de clase
    private final String name;
    private final String description;
    private final int image;
    private final String skills;


    /**
     * Constructor para crear una instancia de la clase GameData con
     * el nombre, descripción, imagen y habilidades del personaje.
     *
     * @param name        El nombre del personaje del juego.
     * @param description La descripción del personaje.
     * @param image       El recurso de imagen asociado al personaje (como un ID de recurso).
     * @param skills      Las habilidades del personaje, representadas como una cadena.
     */
    public GameData(String name, String description, int image, String skills) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.skills = skills;
    }

    /**
     * Método getter por defecto para obtener el valor del nombre de la clase.
     *
     * @return El nombre del personaje.
     */
    public String getName() {
        return name;
    }

    /**
     * Método getter por defecto para obtener el valor de la descripción de la clase.
     *
     * @return La descripción del personaje.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Método getter por defecto para obtener el valor de la imagen de la clase.
     *
     * @return El recurso de imagen asociado al personaje.
     */
    public int getImage() {
        return image;
    }

    /**
     * Método getter por defecto para obtener el valor de las habilidades de la clase.
     *
     * @return Las habilidades del personaje.
     */
    public String getSkills() {
        return skills;
    }
}

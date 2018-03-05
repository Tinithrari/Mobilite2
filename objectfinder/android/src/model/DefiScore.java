package model;

/**
 * 
 */
public class DefiScore implements Score{

    /**
     * Default constructor
     */
    public DefiScore() {
    }

    /**
     * L'identifiant associe au score en mode defi
     */
    private Long id;

    /**
     * Le nom du joueur
     */
    private String name;

    /**
     * Le score qu'il a obtenu
     */
    private long score;

    /**
     * Constructeur creant un DefiScore a partir de son id, nom et du score
     * @param id : l'identifiant
     * @param name : le nom
     * @param score : le score
     */
    public DefiScore(Long id, String name, long score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    /**
     * Constructeur creant un DefiScore a partir de son nom et du score
     * @param name : le nom
     * @param score : le score
     */
    public DefiScore(String name, long score) {
        this.id = null;
        this.name = name;
        this.score = score;
    }

    /**
     * Retourne l'identifiant
     * @return : l'identifiant
     */
    public Long getId() {
        return id;
    }

    /**
     * Modification de l'identifiant
     * @param id : l'identifiant
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne le nom du joueur
     * @return : le nom du joueur
     */
    public String getName() {
        return name;
    }

    /**
     * Modification du nom du joueur
     * @param name : le nom du joueur
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retourne le score obtenu
     * @return : le score obtenu par le joueur
     */
    public long getScore() {
        return score;
    }

    /**
     * Modification du score obtenu
     * @param score : le score obtenu par le joueur
     */
    public void setScore(long score) {
        this.score = score;
    }

    @Override
    /**
     * Affichage de nos differentes informations pour le score Defi
     */
    public String toString(){
        return this.name + " : " + this.score;
    }
}
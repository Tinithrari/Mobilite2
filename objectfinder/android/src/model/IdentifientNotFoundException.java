package model;

/**
 * Created by Nicolas Ydee on 27/02/2018.
 */
public class IdentifientNotFoundException extends Exception {

    private static final String MESSAGE_IDENTIFIANT_NULL = "Identifiant invalide, manipulation impossible";

    /**
     * Exception gerant le cas ou l'identifiant est null
     */
    public IdentifientNotFoundException(){
        super(MESSAGE_IDENTIFIANT_NULL);
    }
}

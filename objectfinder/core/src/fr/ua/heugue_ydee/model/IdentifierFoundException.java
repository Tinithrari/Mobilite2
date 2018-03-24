package fr.ua.heugue_ydee.model;

/**
 * Created by Nicolas Ydee on 27/02/2018.
 */
public class IdentifierFoundException extends Exception {

    private static final String MESSAGE_IDENTIFIANT_NULL = "Identifiant invalide, manipulation impossible";

    /**
     * Exception gerant le cas ou l'identifiant est null
     */
    public IdentifierFoundException(){
        super(MESSAGE_IDENTIFIANT_NULL);
    }
}

package fr.esigelec.quiz;

/**
 * Created by gpillet on 11/01/2017.
 *
 * Class used to share variable in the app
 */

public class Global {
    private static volatile Global instance = null;

    private Global(){}

    public int getIdpersonne() {
        return idpersonne;
    }

    public void setIdpersonne(int idpersonne) {
        this.idpersonne = idpersonne;
    }

    private int idpersonne;

    public static synchronized Global getInstance(){
        if (instance == null){
            instance = new Global();
        }
        return instance;
    }
}

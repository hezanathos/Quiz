package fr.esigelec.quiz;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gpillet on 06/01/2017.
 * Class representing data for one row in the ranking
 */

public class ClassementRow {
    int pos;
    String nom;
    long points;

    public ClassementRow(){
        pos = -1;
        nom = "NoName";
        points = -1;
    }

    public ClassementRow(int pos, String nom, long points) {
        this.pos = pos;
        this.nom = nom;
        this.points = points;
    }

    public ClassementRow(JSONObject row) {
        try{
            this.pos = row.getInt("position");
            this.nom = row.getString("nom");
            this.points = row.getInt("points");
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }
}

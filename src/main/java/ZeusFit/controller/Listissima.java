package ZeusFit.controller;

import ZeusFit.model.Corso;
import ZeusFit.model.Lezione;

import java.util.ArrayList;


//NON UTILIZZATA

public class Listissima {
    private ArrayList<Corso> corsi;
    private ArrayList<ArrayList<Lezione>> lezioni;

    public Listissima() {
    }

    public Listissima(ArrayList<Corso> corsi, ArrayList<ArrayList<Lezione>> lezioni) {
        this.corsi = corsi;
        this.lezioni = lezioni;
    }

    public ArrayList<Corso> getCorsi() {
        return corsi;
    }

    public void setCorsi(ArrayList<Corso> corsi) {
        this.corsi = corsi;
    }

    public ArrayList<ArrayList<Lezione>> getLezioni() {
        return lezioni;
    }

    public void setLezioni(ArrayList<ArrayList<Lezione>> lezioni) {
        this.lezioni = lezioni;
    }
}

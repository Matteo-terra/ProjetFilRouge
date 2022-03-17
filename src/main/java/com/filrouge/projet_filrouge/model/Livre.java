package com.filrouge.projet_filrouge.model;

public class Livre {

        int colonne;
        int rng ;
        String titre;
        String parution;
        String auteur;
        String presentation;


        public Livre(String titre, String auteur, String presentation , String parution , int colonne , int rng) {
                this.colonne = colonne;
                this.rng = rng;
                this.titre = titre;
                this.parution = parution;
                this.auteur = auteur;
                this.presentation = presentation;
        }

        public Livre(String titre, Bibliotheque.Livre.Auteur auteur, String presentation, int parution, short colonne, short rangee) {
        }


        public String getParution() {
                return parution;
        }

        public void setParution(String parution) {
                this.parution = parution;
        }

        public String getAuteur() {
                return auteur;
        }

        public void setAuteur(String auteur) {
                this.auteur = auteur;
        }


        public String getTitre() {
                return titre;
        }

        public void setTitre(String titre) {
                this.titre = titre;
        }

        public String getPresentation() {
                return presentation;
        }

        public void setPresentation(String presentation) {
                this.presentation = presentation;
        }

        public int getColonne() {
                return colonne;
        }

        public void setColonne(int colonne) {
                this.colonne = colonne;
        }

        public int getRng() {
                return rng;
        }

        public void setRng(int rng) {
                this.rng = rng;
        }
    }
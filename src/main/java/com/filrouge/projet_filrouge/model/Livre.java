package com.filrouge.projet_filrouge.model;

public class Livre {

        String url;
        String resume;
        int disponible;
        int colonne;
        int rng ;
        String titre;
        int parution;
        String auteur;
        String nom;
        String prenom;
        String presentation;
        int id_livre;

        public Livre(String titre, String auteur, String presentation, int parution, int colonne, int rng) {
                this.colonne = colonne;
                this.rng = rng;
                this.titre = titre;
                this.parution = parution;
                this.auteur = auteur;
                this.presentation = presentation;
        }

        public Livre(String titre, String nom, String prenom, String presentation, int parution, int colonne, int rng) {
                this.colonne = colonne;
                this.rng = rng;
                this.titre = titre;
                this.parution = parution;
                this.auteur = getInfoAuthor(nom, prenom);
                this.presentation = presentation;
        }

        public Livre(int id_livre, String titre, String auteur, String presentation, int parution, int colonne, int rangee) {
                this.id_livre = id_livre;
                this.colonne = colonne;
                this.rng = rangee;
                this.titre = titre;
                this.parution = parution;
                this.auteur = auteur;
                this.presentation = presentation;
        }

        public Livre(int id_livre, String titre, String auteur, String presentation, int parution, int colonne, int rangee, String resume, int disponible, String url) {
                this.id_livre = id_livre;
                this.colonne = colonne;
                this.rng = rangee;
                this.titre = titre;
                this.parution = parution;
                this.auteur = auteur;
                this.presentation = presentation;
                this.resume = resume;
                this.disponible = disponible;
                this.url = url;

        }

        private String getInfoAuthor(String nom, String prenom) {
                String auteur = nom + " " +prenom;
                return auteur;
        }

  /*      private String getInfoAuthor(String nom, String prenom) {
                this.nom = this.auteur.split("_/_")[0];
                this.prenom = this.auteur.split("_/_")[1];
        }*/

        public Livre(String titre, Bibliotheque.Livre.Auteur auteur, String presentation, int parution, int colonne, int rangee) {
        }


        public int getParution() {
                return parution;
        }

        public void setParution(int parution) {
                this.parution = parution;
        }

        public String getNom() {
                return nom;
        }

        public void setNom(String nom) {
                this.nom = nom;
        }

        public String getPrenom() {
                return prenom;
        }

        public void setPrenom(String prenom) {
                this.prenom = prenom;
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

        public int getId_livre() {
                return id_livre;
        }

        public void setId_livre(int id_livre) {
                this.id_livre = id_livre;
        }

        public int getRng() {
                return rng;
        }

        public void setRng(int rng) {
                this.rng = rng;
        }

        public String getResume() {
                return resume;
        }

        public void setResume(String resume) {
                this.resume = resume;
        }

        public int getDisponible() {
                return disponible;
        }

        public void setDisponible(int disponible) {
                this.disponible = disponible;
        }

        public String getUrl() {
                return url;
        }

        public void setUrl(String url) {
                this.url = url;
        }


    }
package com.filrouge.projet_filrouge.controller;

import com.filrouge.projet_filrouge.model.Bibliotheque;
import com.filrouge.projet_filrouge.model.Livre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private Button btnMoins;
    @FXML
    private Button btnPlus;
    @FXML
    private Button btnValider;
    @FXML
    private TableColumn<Livre, String> colAuteur; //objet livre, type (string...)
    @FXML
    private TableColumn<Livre, Integer> colColonne;
    @FXML
    private TableColumn<Livre, Integer> colParution;
    @FXML
    private TableColumn<Livre, String> colPres;
    @FXML
    private TableColumn<Livre, Integer> colRng;
    @FXML
    private TableColumn<Livre, String> colTitre;
    @FXML
    private MenuItem itemInfos;
    @FXML
    private MenuItem itemOuvrir;
    @FXML
    private MenuItem itemQuitter;
    @FXML
    private MenuItem itemSauve;
    @FXML
    private MenuItem itemSauveSous;
    @FXML
    private Label lblAuteur;
    @FXML
    private Label lblColonne;
    @FXML
    private Label lblParution;
    @FXML
    private Label lblPres;
    @FXML
    private Label lblRng;
    @FXML
    private Label lblTitre;
    @FXML
    private Menu menuAbout;
    @FXML
    private Menu menuEdition;
    @FXML
    private Menu menuFichier;
    @FXML
    private TableView<Livre> tabBiblio;
    @FXML
    private TextField txtAuteur;
    @FXML
    private TextField txtColonne;
    @FXML
    private TextField txtParution;
    @FXML
    private TextField txtPres;
    @FXML
    private TextField txtRng;
    @FXML
    private TextField txtTitre;

    @FXML
    protected void onClick() {
    }

    @FXML
    void onClickValider(ActionEvent event) {
        tabBiblio.setItems(addLivre());
    }

    @FXML
    void Explorer(ActionEvent event) throws IOException {
        Process p = new ProcessBuilder("Explorer.exe", "/select,C:\\directory\\selectedFile").start();
    }

    @FXML
    void QuitterApp(){System.exit(0);}

    @FXML
    public ObservableList<Livre> bibliotheque = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colTitre.setCellValueFactory(new PropertyValueFactory<Livre, String>("titre"));
        colAuteur.setCellValueFactory(new PropertyValueFactory<Livre, String>("auteur"));
        colPres.setCellValueFactory(new PropertyValueFactory<Livre, String>("presentation"));
        colColonne.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("colonne"));
        colRng.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("rng"));
        colParution.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("parution"));


        try {
             JAXBContext jc = JAXBContext.newInstance("com.filrouge.projet_filrouge.model");
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            File myfile = new File("C:\\Users\\boble\\Documents\\XML\\biblio.xml");
            Bibliotheque bibliotheque2 = (Bibliotheque) unmarshaller.unmarshal(myfile);
            List livres = (List) bibliotheque2.getLivre();
            for (int i = 0; i < livres.size(); i++) {
                if (livres.get(i) instanceof Bibliotheque.Livre) {
                    System.out.println("C'est vrai");
                }
                else {
                    System.out.println("C'est faux");
                    System.out.println(livres.get(i).getClass().toString());
                }
                Bibliotheque.Livre livre = (Bibliotheque.Livre) livres.get(i);
                System.out.println("Livre ");
                System.out.println("Titre   : " + livre.getTitre());
                System.out.println("Auteur  : " + livre.getAuteur());
                System.out.println("Parution : " + livre.getParution());
                System.out.println("Présentation : " + livre.getPresentation());
                System.out.println("Colonne : " + livre.getColonne());
                System.out.println("Rangée : " + livre.getRangee());
                System.out.println();

                bibliotheque.add(new Livre(livre.getTitre(), livre.getAuteur(),livre.getPresentation() , livre.getParution(), livre.getColonne(), livre.getRangee()));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tabBiblio.setItems(getLi());
    }

        public ObservableList<Livre> getLi(){

            bibliotheque.add(new Livre("test", "Yanis", "Oui", "2000", 5, 2));
            bibliotheque.add(new Livre("Jojo", "Vincent", "Okoko", "2010", 6, 7));

            return bibliotheque;
        }

        public ObservableList<Livre> addLivre(){
            String titre1 = txtTitre.getText();
            String auteur1 = txtAuteur.getText();
            String pres1 = txtPres.getText();
            String parution1 = txtParution.getText();
            int colonne1 = Integer.parseInt(txtColonne.getText());
            int rng1 = Integer.parseInt(txtRng.getText());

            bibliotheque.add(new Livre(titre1,auteur1,pres1,parution1,colonne1,rng1));
            return bibliotheque;
    }}

/*
            Fonction creerLivre()
@FXML
    protected Livre creerLivre (ActionEvent e){

        String titre = txtTitre.getText();
        String auteur = txtAuteur.getText();
        String pres = txtPres.getText();
        int parution = txtParution.getValue();
        int colonne = txtColonne.getValue();
        int rang = txtRng.getValue();

        System.out.println(txtTitre.getText());
        System.out.println(txtAuteur.getText());
        System.out.println(txtPres.getText());
        System.out.println(txtParution.getValue());
        System.out.println(txtColonne.getValue());
        System.out.println(txtRng.getValue());

        Livre livre = new Livre(titre, auteur, pres, parution, colonne, rang);

        System.out.println(livre.getTitre());
        System.out.println(livre.getAuteur());
        System.out.println(livre.getPresentation());
        System.out.println(livre.getParution());
        System.out.println(livre.getColonne());
        System.out.println(livre.getRng());

        return livre;
    }*/



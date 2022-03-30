package com.filrouge.projet_filrouge.controller;

import com.filrouge.projet_filrouge.model.Bibliotheque;
import com.filrouge.projet_filrouge.model.Livre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.*;
import java.awt.*;
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
    private Button btnModify;
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
    private JAXBContext context;

    private static final String FICHIER_XML = "C:\\Users\\visho\\IdeaProjects\\demo\\ProjetFilRougeGit\\livres.xml";

    private File myfile = new File(FICHIER_XML);

    @FXML
    protected void onClick() {
    }

    @FXML
    void onClickValider(ActionEvent event) {
        tabBiblio.setItems(addLivre());
        txtColonne.clear();
        txtParution.clear();
        txtPres.clear();
        txtRng.clear();
        txtTitre.clear();
        txtAuteur.clear();

    }

    @FXML
    void onClickModifier(ActionEvent event) {

        modifierLivre();

        txtColonne.clear();
        txtParution.clear();
        txtPres.clear();
        txtRng.clear();
        txtTitre.clear();
        txtAuteur.clear();

        tabBiblio.refresh();

    }

/*
    @FXML
    void Explorer(ActionEvent event) throws IOException {
        Process p = new ProcessBuilder("Explorer.exe", "/select,C:\\directory\\selectedFile").start();
    }
*/

    @FXML
    void QuitterApp() {
        System.exit(0);
    }

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
        this.tabBiblio.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.showLivreDetails(newValue);
        });


        try {
            JAXBContext jc = JAXBContext.newInstance("com.filrouge.projet_filrouge.model");
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            File myfile = new File("C:\\Users\\visho\\IdeaProjects\\demo\\Projet_FilRouge\\XML\\Biblio.xml");
            Bibliotheque bibliotheque2 = (Bibliotheque) unmarshaller.unmarshal(myfile);
            List livres = (List) bibliotheque2.getLivre();
            for (int i = 0; i < livres.size(); i++) {
                if (livres.get(i) instanceof Bibliotheque.Livre) {
                    System.out.println("C'est vrai");
                } else {
                    System.out.println("C'est faux");
                    System.out.println(livres.get(i).getClass().toString());
                }
                Bibliotheque.Livre livre = (Bibliotheque.Livre) livres.get(i);
                System.out.println("Livre ");
                System.out.println("Titre   : " + livre.getTitre());
                //System.out.println("Auteur  : " + livre.getAuteur());
                System.out.println("Parution : " + livre.getParution());
                System.out.println("Présentation : " + livre.getPresentation());
                System.out.println("Colonne : " + livre.getColonne());
                System.out.println("Rangée : " + livre.getRangee());
                System.out.println();

                //Livre myLivre =new Livre(livre.getTitre(), livre.getAuteur().getNom() + "_/_"+ livre.getAuteur().getPrenom(), livre.getPresentation() , livre.getParution(), livre.getColonne(), livre.getRangee());
                Livre myLivre = new Livre(livre.getTitre(), livre.getAuteur().getNom(), livre.getAuteur().getPrenom(), livre.getPresentation(), livre.getParution(), livre.getColonne(), livre.getRangee());

                //TEST
                bibliotheque.add(new Livre(livre.getTitre(), livre.getAuteur().getNom(), livre.getAuteur().getPrenom(), livre.getPresentation(), livre.getParution(), livre.getColonne(), livre.getRangee()));


                tabBiblio.getItems().add(myLivre);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //tabBiblio.setItems(getLi());
    }
    //TEST AJOUT EN DUR
        /*  public ObservableList<Livre> getLi(){

            bibliotheque.add(new Livre("test", "Yanis", "Oui", "2000", 5, 2));
            bibliotheque.add(new Livre("Jojo", "Vincent", "Okoko", "2010", 6, 7));

            return bibliotheque;
        }*/

    public ObservableList<Livre> addLivre() {
        String titre1 = txtTitre.getText();
        String auteur1 = txtAuteur.getText();
        String pres1 = txtPres.getText();
        int parution1 = Integer.parseInt(txtParution.getText());
        int colonne1 = Integer.parseInt(txtColonne.getText());
        int rng1 = Integer.parseInt(txtRng.getText());

        bibliotheque.add(new Livre(titre1, auteur1, pres1, parution1, colonne1, rng1));
        return bibliotheque;

    }

    @FXML
    void handleDelete(ActionEvent event) {
        int selectedIndex = this.tabBiblio.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            this.tabBiblio.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Aucun livre sélectionné");
            alert.setContentText("Merci de choisir un livre dans le tableau.");
            alert.showAndWait();
        }

    }


    public void initJAXB() throws JAXBException {
        this.context = JAXBContext.newInstance(new Class[]{Livre.class});
    }

    @FXML
    public ObservableList<Livre> modifierLivre() {
        Livre selectedLivre = (Livre) this.tabBiblio.getSelectionModel().getSelectedItem();
        int selectedIndex = this.tabBiblio.getSelectionModel().getSelectedIndex();
        if (selectedLivre != null) {
            for (int i = 0; i < bibliotheque.size(); i++) {
                if (selectedLivre.getTitre().equals(bibliotheque.get(i).getTitre()) && selectedLivre.getRng() == bibliotheque.get(i).getRng()) {

                    System.out.println("Titre du livre selectionné:" + selectedLivre.getTitre());
                    System.out.println("Titre du livre de la biblio:" + bibliotheque.get(i).getTitre());

                    bibliotheque.get(i).setTitre(txtTitre.getText());
                    bibliotheque.get(i).setAuteur(txtAuteur.getText());
                    bibliotheque.get(i).setPresentation(txtPres.getText());
                    bibliotheque.get(i).setParution(Integer.parseInt(txtParution.getText()));
                    bibliotheque.get(i).setColonne(Integer.parseInt(txtColonne.getText()));
                    bibliotheque.get(i).setRng(Integer.parseInt(txtRng.getText()));

                    System.out.println("Titre du livre de la biblio modifié:" + bibliotheque.get(i).getTitre());
                }
            }


        }
        return bibliotheque;

    }

    private void showLivreDetails(Livre livre) {
        if (livre != null) {
            this.txtTitre.setText(livre.getTitre());
            this.txtAuteur.setText(livre.getAuteur());
            this.txtPres.setText(livre.getPresentation());
            this.txtParution.setText(String.valueOf(livre.getParution()));
            this.txtColonne.setText(String.valueOf(livre.getColonne()));
            this.txtRng.setText(String.valueOf(livre.getRng()));
        } else {
            this.txtTitre.clear();
            this.txtAuteur.clear();
            this.txtPres.clear();
            this.txtParution.clear();
            this.txtColonne.clear();
            this.txtRng.clear();
        }

    }

    /*
    private void uploadFile(File outputFile) {
        try {
            XmlSerializer xmlSerializer = new XmlSerializer();

            //Bibliotheque.Livre bibliotheque2 = new Bibliotheque();
            //bibliotheque2.getLivre().addAll(bibliotheque);

            String result = xmlSerializer.serialize(bibliotheque2);

            FileWriter fileWriter = new FileWriter(outputFile);
            fileWriter.write(result);
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Erreur, Une erreur est survenue lors du téléchargement du fichier");
            return;
        }
    }*/

    @FXML
    void SaveXML() {
        if (myfile != null) {
            System.out.println("Le fichier existe deja");
            return;
        }

        //SauvegarderSous();
    }

    @FXML
    void SauvegarderSous() throws JAXBException {

        var context = JAXBContext.newInstance(Bibliotheque.class);
        var m = context.createMarshaller();
        try {
            // create JAXB context and instantiate marshaller
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Write to System.out
            m.marshal(bibliotheque, System.out);

            // Write to File
            m.marshal(bibliotheque, new File(FICHIER_XML));

        } catch (PropertyException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Explorer(ActionEvent event) throws IOException, JAXBException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("xml files (*.xml)", new String[]{"xml"});
        chooser.setDialogTitle("Open schedule file");
        chooser.setFileFilter(xmlfilter);
        int value = chooser.showOpenDialog((Component) null);
        if (value == 0) {
            File target = chooser.getSelectedFile();

            try {
                JAXBContext jc = JAXBContext.newInstance("com.filrouge.projet_filrouge.model");
                Unmarshaller unmarshaller = jc.createUnmarshaller();
                Bibliotheque bibliotheque2 = (Bibliotheque) unmarshaller.unmarshal(target);
                List livres = (List) bibliotheque2.getLivre();
                bibliotheque.clear();
                tabBiblio.getItems().clear();
                for (int i = 0; i < livres.size(); i++) {
                    if (livres.get(i) instanceof Bibliotheque.Livre) {
                        System.out.println("C'est vrai");
                    } else {
                        System.out.println("C'est faux");
                        System.out.println(livres.get(i).getClass().toString());
                    }
                    Bibliotheque.Livre livre = (Bibliotheque.Livre) livres.get(i);
                    System.out.println("Livre ");
                    System.out.println("Titre   : " + livre.getTitre());
                    //System.out.println("Auteur  : " + livre.getAuteur());
                    System.out.println("Parution : " + livre.getParution());
                    System.out.println("Présentation : " + livre.getPresentation());
                    System.out.println("Colonne : " + livre.getColonne());
                    System.out.println("Rangée : " + livre.getRangee());
                    System.out.println();

                    //Livre myLivre =new Livre(livre.getTitre(), livre.getAuteur().getNom() + "_/_"+ livre.getAuteur().getPrenom(), livre.getPresentation() , livre.getParution(), livre.getColonne(), livre.getRangee());
                    Livre myLivre = new Livre(livre.getTitre(), livre.getAuteur().getNom(), livre.getAuteur().getPrenom(), livre.getPresentation(), livre.getParution(), livre.getColonne(), livre.getRangee());

                    //TEST
                    bibliotheque.add(new Livre(livre.getTitre(), livre.getAuteur().getNom(), livre.getAuteur().getPrenom(), livre.getPresentation(), livre.getParution(), livre.getColonne(), livre.getRangee()));


                    tabBiblio.getItems().add(myLivre);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}


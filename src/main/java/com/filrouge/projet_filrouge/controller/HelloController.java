package com.filrouge.projet_filrouge.controller;

import com.filrouge.projet_filrouge.helpers.DbConnect;
import com.filrouge.projet_filrouge.model.Bibliotheque;
import com.filrouge.projet_filrouge.model.Livre;
import com.filrouge.projet_filrouge.model.ObjectFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import javax.swing.text.html.ImageView;
import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
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
    private TextArea txtResume;
    @FXML
    private CheckBox checkDispo;
    @FXML
    private TextField txtURL;
    @FXML
    private TextField txtTitre;
    @FXML
    private Button btnSynchroBdD;
    @FXML
    private Button btnSynchroXML;
    @FXML
    private ImageView itemImage;

    private JAXBContext context;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Livre livreBDD = null;
    int connect = 0;


    private static final String FICHIER_XML = "C:\\Users\\visho\\IdeaProjects\\demo\\ProjetFilRougeGit\\livres.xml";

    private File myfile = new File(FICHIER_XML);

    @FXML
    protected void onClick() {
    }

    @FXML
    void onClickValider(ActionEvent event) {
        if (connect == 1) {
            addLivre();
        } else {
            tabBiblio.setItems(addLivre());
        }
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

    @FXML
    void QuitterApp() {
        System.exit(0);
    }

    @FXML
    public ObservableList<Livre> bibliotheque = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startProject();
    }

    public void startProject() {

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
            File myfile = new File("C:\\Users\\ycheze\\Projet JAVA - Idea\\ProjetFilRougeGit_V2\\XML\\Biblio.xml");
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

                Livre myLivre = new Livre(livre.getTitre(), livre.getAuteur().getNom(), livre.getAuteur().getPrenom(), livre.getPresentation(), livre.getParution(), livre.getColonne(), livre.getRangee());

                //TEST
                bibliotheque.add(new Livre(livre.getTitre(), livre.getAuteur().getNom(), livre.getAuteur().getPrenom(), livre.getPresentation(), livre.getParution(), livre.getColonne(), livre.getRangee()));


                tabBiblio.getItems().add(myLivre);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObservableList<Livre> addLivre() {

        if (connect == 1) {
            System.out.println("bouton ajout BdD");

            String titreBdD;
            String auteurBdD;
            String presBdD;
            int parutionBdD;
            int colonneBdd;
            int rngBdD;
            int disponibleBdD;
            String resumeBdD;
            String urlBdD;

            titreBdD = txtTitre.getText();
            auteurBdD = txtAuteur.getText();
            presBdD = txtPres.getText();
            parutionBdD = Integer.parseInt(txtParution.getText());
            colonneBdd = Integer.parseInt(txtColonne.getText());
            rngBdD = Integer.parseInt(txtRng.getText());
            if (checkDispo.isSelected() == true) {
                disponibleBdD = 1;
            } else {
                disponibleBdD = 0;
            }
            ;

            System.out.println("BOOL:" + disponibleBdD);
            resumeBdD = txtResume.getText();
            urlBdD = txtURL.getText();


            try {
                //étape 1: charger la classe driver
                Class.forName("com.mysql.jdbc.Driver");
                //étape 2: créer l'objet de connexion
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/fil_rouge", "root", "");
                //étape 3: créer l'objet statement
                Statement stmt = conn.createStatement();
                //étape 4: exécuter la requête
                System.out.println("Mise à jour...");
                String sql = "INSERT INTO `livres`(`titre`, `auteur`, `presentation`, `parution`, `colonne`, `rangee`, `disponible`, `resume`, `url`) VALUES ('" + titreBdD + "','" + auteurBdD + "','" + presBdD + "'," + parutionBdD + "," + colonneBdd + "," + rngBdD + "," + disponibleBdD + ", '" + resumeBdD + "','" + urlBdD + "')";
                stmt.executeUpdate(sql);
                System.out.println("La table a été mis à jour avec succès");
                //étape 5: fermez l'objet de connexion
                conn.close();
                loadDate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            String titre1 = txtTitre.getText();
            String auteur1 = txtAuteur.getText();
            String pres1 = txtPres.getText();
            int parution1 = Integer.parseInt(txtParution.getText());
            int colonne1 = Integer.parseInt(txtColonne.getText());
            int rng1 = Integer.parseInt(txtRng.getText());

            bibliotheque.add(new Livre(titre1, auteur1, pres1, parution1, colonne1, rng1));

        }
        return bibliotheque;
    }

    @FXML
    void handleDelete(ActionEvent event) {
        int selectedIndex = this.tabBiblio.getSelectionModel().getSelectedIndex();
        Livre selectedLivre = (Livre) this.tabBiblio.getSelectionModel().getSelectedItem();
        if (connect == 1) {
            System.out.println("bouton suppr BdD");
            int id_livre;
            id_livre = selectedLivre.getId_livre();
            System.out.println(id_livre);

            try {
                //étape 1: charger la classe driver
                Class.forName("com.mysql.jdbc.Driver");
                //étape 2: créer l'objet de connexion
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/fil_rouge", "root", "");
                //étape 3: créer l'objet statement
                Statement stmt = conn.createStatement();
                //étape 4: exécuter la requête
                System.out.println("Suppression du livre...");
                String sql = "DELETE FROM livres WHERE id_livre = " + id_livre + "";
                stmt.executeUpdate(sql);
                System.out.println("La table a été mis à jour avec succès");
                //étape 5: fermez l'objet de connexion
                conn.close();
                loadDate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {


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

    }


    public void initJAXB() throws JAXBException {
        this.context = JAXBContext.newInstance(new Class[]{Livre.class});
    }

    @FXML
    public ObservableList<Livre> modifierLivre() {
        Livre selectedLivre = (Livre) this.tabBiblio.getSelectionModel().getSelectedItem();
        int selectedIndex = this.tabBiblio.getSelectionModel().getSelectedIndex();
        System.out.println("modif : " + connect);
        if (connect == 1) {
            System.out.println("bouton modif BdD");

            int id_livre;
            String titreBdD;
            String auteurBdD;
            String presBdD;
            int parutionBdD;
            int colonneBdd;
            int rngBdD;
            int disponibleBdD;
            String resumeBdD;
            String urlBdD;


            titreBdD = txtTitre.getText();
            auteurBdD = txtAuteur.getText();
            presBdD = txtPres.getText();
            parutionBdD = Integer.parseInt(txtParution.getText());
            colonneBdd = Integer.parseInt(txtColonne.getText());
            rngBdD = Integer.parseInt(txtRng.getText());
            if (checkDispo.isSelected() == true) {
                disponibleBdD = 1;
            } else {
                disponibleBdD = 0;
            }
            ;

            System.out.println("BOOL:" + disponibleBdD);
            resumeBdD = txtResume.getText();
            urlBdD = txtURL.getText();

            id_livre = selectedLivre.getId_livre();
            System.out.println(id_livre);

            try {
                //étape 1: charger la classe driver
                Class.forName("com.mysql.jdbc.Driver");
                //étape 2: créer l'objet de connexion
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/fil_rouge", "root", "");
                //étape 3: créer l'objet statement
                Statement stmt = conn.createStatement();
                //étape 4: exécuter la requête
                System.out.println("Mise à jour...");
                String sql = "UPDATE livres SET titre = '" + titreBdD + "', auteur = '" + auteurBdD + "', presentation = '" + presBdD + "', parution = " + parutionBdD + ", colonne = " + colonneBdd + ",rangee = " + rngBdD + ", disponible = " + disponibleBdD + " ,resume = '" + resumeBdD + "', url = '" + urlBdD + "' WHERE id_livre = " + id_livre + "";
                stmt.executeUpdate(sql);
                System.out.println("La table a été mis à jour avec succès");
                //étape 5: fermez l'objet de connexion
                conn.close();
                loadDate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {

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
                    tabBiblio.refresh();
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
            this.txtResume.setText(String.valueOf(livre.getResume()));
            this.txtURL.setText(String.valueOf(livre.getUrl()));

            if (livre.getDisponible() == 1) {
                checkDispo.setSelected(true);
            } else {
                checkDispo.setSelected(false);
            }

        } else {
            this.txtTitre.clear();
            this.txtAuteur.clear();
            this.txtPres.clear();
            this.txtParution.clear();
            this.txtColonne.clear();
            this.txtRng.clear();
            this.txtResume.clear();
            this.txtURL.clear();
        }

    }

    // Sauvegarder le fichier sous un format choisi
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

    FileChooser fileChooser = new FileChooser();

    // Ouverture de l'explorateur de fichier pour choisir un fichier XML
    @FXML
    void Explorer(ActionEvent event) throws IOException, JAXBException {
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Document (*.xml", "*.xml"));
        File myfile = fileChooser.showOpenDialog(null);

        if (myfile != null) {

            try {
                JAXBContext jc = JAXBContext.newInstance("com.filrouge.projet_filrouge.model");
                Unmarshaller unmarshaller = jc.createUnmarshaller();
                Bibliotheque bibliotheque2 = (Bibliotheque) unmarshaller.unmarshal(myfile);
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
                    System.out.println("Parution : " + livre.getParution());
                    System.out.println("Présentation : " + livre.getPresentation());
                    System.out.println("Colonne : " + livre.getColonne());
                    System.out.println("Rangée : " + livre.getRangee());
                    System.out.println();

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

    // CONNEXION BDD
    @FXML
    private void refreshTable() {
        try {
            bibliotheque.clear();
            query = "SELECT * FROM `livres`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //On récupère les données de la table livres et les ajoute dans la classe bibliothèque
                bibliotheque.add(new Livre(
                        resultSet.getInt("id_livre"),
                        resultSet.getString("titre"),
                        resultSet.getString("auteur"),
                        resultSet.getString("presentation"),
                        resultSet.getInt("parution"),
                        resultSet.getInt("colonne"),
                        resultSet.getInt("rangee"),
                        resultSet.getString("resume"),
                        resultSet.getInt("disponible"),
                        resultSet.getString("url")));
                // Ajout de la classe bibliothèque dans le tableau de l'application
                tabBiblio.setItems(bibliotheque);

            }
        } catch (SQLException ex) {
            System.out.println("Erreur BDD");
            // Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Permet d'afficher les données de la BDD sur le tableau
    public void loadDate() {

        connection = DbConnect.getConnect();
        refreshTable();
        // Indexation des données dans les cellules du FXML
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        colAuteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));
        colPres.setCellValueFactory(new PropertyValueFactory<>("presentation"));
        colColonne.setCellValueFactory(new PropertyValueFactory<>("colonne"));
        colRng.setCellValueFactory(new PropertyValueFactory<>("rng"));
        colParution.setCellValueFactory(new PropertyValueFactory<>("parution"));

    }

    ;

    //Connexion à la Base de données
    public void ConnexionBdD(ActionEvent actionEvent) {
        loadDate();
        connect = 1;
        System.out.println("Etat connexion : " + connect);
    }

    //Déconnexion de la base de données
    public void DeconnexionBdD(ActionEvent actionEvent) {
        connect = 0;
        System.out.println("Etat connexion : " + connect);
        bibliotheque.clear();
        tabBiblio.getItems().clear();
        startProject();

    }

    // Affichage des données de la base sur le tableau d'affichage
    private void addBdDinXML() {
        try {
            query = "SELECT * FROM `livres`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                bibliotheque.add(new Livre(
                        resultSet.getInt("id_livre"),
                        resultSet.getString("titre"),
                        resultSet.getString("auteur"),
                        resultSet.getString("presentation"),
                        resultSet.getInt("parution"),
                        resultSet.getInt("colonne"),
                        resultSet.getInt("rangee"),
                        resultSet.getString("resume"),
                        resultSet.getInt("disponible"),
                        resultSet.getString("url")));
                tabBiblio.setItems(bibliotheque);

            }


        } catch (SQLException ex) {
            System.out.println("Erreur BDD");
            //Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Ajout du fichier XML en BDD sur clic du bouton "Synchronisation XML"
    public void SynchroXML(ActionEvent actionEvent) {
        ObjectFactory factory = new ObjectFactory();
        for (int i = 0; i < tabBiblio.getItems().size(); i++) {

            try {
                //étape 1: charger la classe driver
                Class.forName("com.mysql.jdbc.Driver");
                //étape 2: créer l'objet de connexion
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/fil_rouge", "root", "");
                //étape 3: créer l'objet statement
                Statement stmt = conn.createStatement();
                //étape 4: exécuter la requête
                System.out.println("Mise à jour...");
                String sql = "INSERT INTO `livres`(`titre`, `auteur`, `presentation`, `parution`, `colonne`, `rangee`, `disponible`, `resume`, `url`) VALUES ('" + tabBiblio.getItems().get(i).getTitre() + "','" + tabBiblio.getItems().get(i).getAuteur() + "','" + tabBiblio.getItems().get(i).getPresentation() + "'," + tabBiblio.getItems().get(i).getParution() + "," + tabBiblio.getItems().get(i).getColonne() + "," + tabBiblio.getItems().get(i).getRng() + "," + tabBiblio.getItems().get(i).getDisponible() + ", '" + tabBiblio.getItems().get(i).getResume() + "','" + tabBiblio.getItems().get(i).getUrl() + "')";
                stmt.executeUpdate(sql);
                System.out.println("La table a été mis à jour avec succès");
                //étape 5: fermez l'objet de connexion
                conn.close();
                loadDate();
            } catch (Exception e) {
                System.out.println(e);
            }

    }

}
    // Synchronisation de la Bdd avec le fichier XML ouvert
    public void SynchroBdD(ActionEvent actionEvent) {
        connection = DbConnect.getConnect();
        addBdDinXML();

        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        colAuteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));
        colPres.setCellValueFactory(new PropertyValueFactory<>("presentation"));
        colColonne.setCellValueFactory(new PropertyValueFactory<>("colonne"));
        colRng.setCellValueFactory(new PropertyValueFactory<>("rng"));
        colParution.setCellValueFactory(new PropertyValueFactory<>("parution"));
    }

    // Fonction pour sauvegarder le tableau en fichier XML

    @FXML
    public void SaveXML(ActionEvent event){
        try{
            JAXBContext jc = JAXBContext.newInstance("com.filrouge.projet_filrouge.model");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Document (.xml)", ".xml"));
            File myfile = fileChooser.showSaveDialog(null);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            ObjectFactory factory = new ObjectFactory();
            Bibliotheque bibliotheque = factory.createBibliotheque();
            for (int i = 0; i < tabBiblio.getItems().size(); i++) {
                Bibliotheque.Livre livre = factory.createBibliothequeLivre();
                livre.setPresentation(tabBiblio.getItems().get(i).getPresentation());
                livre.setTitre(tabBiblio.getItems().get(i).getTitre());
                livre.setParution(Integer.parseInt(String.valueOf(tabBiblio.getItems().get(i).getParution())));
                livre.setColonne(Short.parseShort(String.valueOf(tabBiblio.getItems().get(i).getColonne())));
                livre.setRangee(Short.parseShort(String.valueOf(tabBiblio.getItems().get(i).getRng())));

                String[] s =tabBiblio.getItems().get(i).getAuteur().split(" ");

                Bibliotheque.Livre.Auteur auteur = factory.createBibliothequeLivreAuteur();
                auteur.setNom(s[1]);
                auteur.setPrenom(s[0]);
                livre.setAuteur(auteur);

                bibliotheque.getLivre().add(livre);
            }
            marshaller.marshal(bibliotheque, myfile);

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}

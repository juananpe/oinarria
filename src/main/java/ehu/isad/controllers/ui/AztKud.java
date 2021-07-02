package ehu.isad.controllers.ui;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import ehu.isad.controllers.db.URLKud;
import ehu.isad.model.Repo;
import ehu.isad.utils.Utils;
import javafx.event.EventHandler;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;
import org.apache.commons.codec.binary.Hex;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AztKud implements Initializable
{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Repo> taula;

    @FXML
    private TableColumn<Repo, String> full_name;

    @FXML
    private TableColumn<Repo, String> license;

    @FXML
    private TableColumn<Repo, String> description;
    @FXML
    private TableColumn<Repo, Integer> open_issues;

    @FXML
    private Button checkBotoia;

    @FXML
    private Label mezuak;

    @FXML
    private TextField testua;

    @FXML
    void onCheckClick(ActionEvent event)  {
        Repo repo = null;
        try {
            repo = Utils.readFromUrl(testua.getText());
            addRow(repo);
        } catch (IOException e) {
            mezuak.setText(testua.getText() + " ez da aurkitu");
        }
    }

    void addRow(Repo repo){

        boolean exists = URLKud.getInstance().getRepos(repo.full_name).size() > 0;
        if (!exists) {
            URLKud.getInstance().setVersion(repo);
            websiteList.add(repo);
            mezuak.setText("Datubasean txertatu da");
        } else {
            mezuak.setText("Datubasean zegoen");
        }
    }

    @FXML
    private FontAwesomeIconView closeButton;

    @FXML
    void onClose(MouseEvent event) {
        System.exit(0);
    }


    private ObservableList<Repo> websiteList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        full_name.setCellValueFactory(new PropertyValueFactory<>("full_name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        license.setCellValueFactory(new PropertyValueFactory<>("license"));
        open_issues.setCellValueFactory(new PropertyValueFactory<>("open_issues"));

        List<Repo> repos = URLKud.getInstance().getRepos("");
        websiteList = FXCollections.observableArrayList();
        websiteList.addAll(repos);

        open_issues.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        open_issues.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Repo, Integer>>() {
                    public void handle(TableColumn.CellEditEvent<Repo, Integer> t) {

                        Repo repo = ((Repo) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        );

                        mezuak.setText("Issue kopurua datubasean eguneratu da");
                        URLKud.getInstance().setVersion(repo);
                        // model.setVersion(t.getNewValue());
                    }
                }
        );



        taula.setEditable(true);
        taula.setItems(websiteList);
    }
}

package ehu.isad.controllers.ui;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import ehu.isad.controllers.db.URLKud;
import javafx.event.EventHandler;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import org.apache.commons.codec.binary.Hex;



import ehu.isad.model.ServerCMSModel;
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
    private TableView<ServerCMSModel> taula;

    @FXML
    private TableColumn<ServerCMSModel, String> url;

    @FXML
    private TableColumn<ServerCMSModel, String> md5;

    @FXML
    private TableColumn<ServerCMSModel, String> version;

    @FXML
    private Button checkBotoia;

    @FXML
    private Label mezuak;

    @FXML
    private TextField testua;

    @FXML
    void onCheckClick(ActionEvent event) throws NoSuchAlgorithmException, IOException {
        URL url = new URL( testua.getText() + "/README");
        InputStream is = url.openStream();
        MessageDigest md = MessageDigest.getInstance("MD5");
        String digest = getDigest(is, md, 2048);

        addRow(testua.getText(), digest);

       // System.out.println("MD5 Digest:" + digest);

    }

    void addRow(String url, String digest){
        String version = URLKud.getInstance().getVersion(digest);
        if (version.equals("")){
            mezuak.setText("Ez da datubasean aurkitu");
        } else {
            mezuak.setText("Datubasean zegoen");
        }
        websiteList.add(new ServerCMSModel(url, digest, version));
    }

    public static String getDigest(InputStream is, MessageDigest md, int byteArraySize)
            throws IOException {

        md.reset();
        byte[] bytes = new byte[byteArraySize];
        int numBytes;
        while ((numBytes = is.read(bytes)) != -1) {
            md.update(bytes, 0, numBytes);
        }
        byte[] digest = md.digest();
        String result = new String(Hex.encodeHex(digest));
        return result;
    }


    @FXML
    private FontAwesomeIconView closeButton;

    @FXML
    void onClose(MouseEvent event) {
        System.exit(0);
    }


    private ObservableList<ServerCMSModel> websiteList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        url.setCellValueFactory(new PropertyValueFactory<>("url"));
        md5.setCellValueFactory(new PropertyValueFactory<>("md5"));
        version.setCellValueFactory(new PropertyValueFactory<>("version"));

        websiteList = FXCollections.observableArrayList();


        version.setCellFactory(TextFieldTableCell.forTableColumn());

        version.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<ServerCMSModel, String>>() {
                    public void handle(TableColumn.CellEditEvent<ServerCMSModel, String> t) {

                        ServerCMSModel model = ((ServerCMSModel) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        );

                        mezuak.setText("md5 eta bertsio berria datubasean sartu dira");
                        URLKud.getInstance().setVersion(model.getMd5(), t.getNewValue());
                        model.setVersion(t.getNewValue());
                    }
                }
        );



        taula.setEditable(true);
        taula.setItems(websiteList);
    }
}

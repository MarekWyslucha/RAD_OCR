/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rad_ocr;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.CardData;

/**
 *
 * @author Marek
 */
public class FXMLDocumentController implements Initializable {

    private File imageFile;
    private OCReader oCReader;
    
    @FXML
    private Label status;
    @FXML
    private ImageView imageView;
    
    @FXML
    private TextField name;
    @FXML
    private TextField street;
    @FXML
    private TextField city;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextField website;
    @FXML
    private TextArea output;

    @FXML
    private void handleSelectImageButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Graphics files (*.gif, *.jpg, *.png)", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Open Graphic File");
        imageFile = fileChooser.showOpenDialog(RAD_OCR.getStage());

        if (imageFile != null) {
            Image img = new Image(imageFile.toURI().toString());
            imageView.setImage(img);
            status.setText("");
        }
    }

    @FXML
    private void handleReadFromImageButtonAction(ActionEvent event) {
        if(imageFile != null){
            oCReader = new OCReader(imageFile);
            output.setText(oCReader.getResult());
            
            CardData cardData = oCReader.getCardData();
            
            name.setText(cardData.getName());
            street.setText(cardData.getStreet());
            city.setText(cardData.getCity());
            phone.setText(cardData.getPhone());
            email.setText(cardData.getEmail());
            website.setText(cardData.getWebsite());
        } else {
            status.setText("Brak obrazu do odczytu");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}

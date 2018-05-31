package application.view;

import application.model.KundeModel;
import application.model.KundeModel.Art;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditViewController {
	@FXML
	private Button saveButtonPressed;
	@FXML
	private Button cancelButtonPressed; 
	@FXML private TextField txtVorname;
	@FXML private TextField txtNachname;
	@FXML private TextField txtStrasse;
	@FXML private TextField txtPlz;
	@FXML private TextField txtOrt;
	@FXML private TextField txtEmail;
	@FXML private DatePicker dpKundeSeit;
	@FXML private ChoiceBox<KundeModel.Art> cbKundeArt;
	@FXML private RadioButton rbtnNewsletterYes;
	@FXML private RadioButton rbtnNewsletterNo;
	private Stage stage;
	private boolean isSaveClicked;
	private KundeModel kunde;
	
	public void setStage(Stage s){
		this.stage = s;
	}
	
	@FXML
	private void initialize() {
		cbKundeArt.getItems().setAll(KundeModel.Art.values());
	}
	
	@FXML
	public void saveButtonPressed(ActionEvent actionEvent) {
		kunde.getVorname().setValue(txtVorname.getText());
		kunde.getNachname().setValue(txtNachname.getText());
		kunde.getStrasse().setValue(txtStrasse.getText());
		kunde.getPlz().setValue(Integer.parseInt(txtPlz.getText()));
		kunde.getOrt().setValue(txtOrt.getText());
		kunde.getEmail().setValue(txtEmail.getText());
		kunde.getArt().setValue(cbKundeArt.getValue().name());
		if (rbtnNewsletterYes.isSelected()) {
			kunde.getNewsletter().setValue(true);
		} else {
			kunde.getNewsletter().setValue(false);
		}
		isSaveClicked = true;
		stage.close();
	}
	
	@FXML
	public void cancelButtonPressed(ActionEvent actionEvent) {
		isSaveClicked = false;
		stage.close();
	}
	
	@FXML
	public void setKunde(KundeModel kunde) {
		this.kunde = kunde;
		txtVorname.setText(kunde.getVorname().getValue());
		txtNachname.setText(kunde.getNachname().getValue());
		txtStrasse.setText(kunde.getStrasse().getValue());
		txtPlz.setText(kunde.getPlz().getValue().toString());
		txtOrt.setText(kunde.getOrt().getValue());
		txtEmail.setText(kunde.getEmail().getValue());
		if(kunde.getArt().getValue() != null) {
			cbKundeArt.setValue(kunde.getArtEnum().getValue());
		} else {
			cbKundeArt.setValue(Art.Standard);
		}
		
		if(kunde.getNewsletter().getValue()) {
			rbtnNewsletterYes.setSelected(true);
		} else {
			rbtnNewsletterNo.setSelected(true);
		}
	}

	public boolean getIsSaveClicked() {
		return isSaveClicked;
	}
	
}

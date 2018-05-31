package application.view;

import java.io.IOException;
import java.util.Optional;

import application.model.KundeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainViewController {
	@FXML
	private TableView<KundeModel> tblKunde;
	@FXML
	private TableColumn<KundeModel, String> colVorname;
	@FXML
	private TableColumn<KundeModel, String> colNachname;
	@FXML private Label lblVorname;
	@FXML private Label lblNachname;
	@FXML private Label lblStrasse;
	@FXML private Label lblPlzOrt;
	@FXML private Label lblEmail;
	@FXML private Label lblKundeSeit;
	@FXML private Label lblStatus;
	@FXML private Label lblNewsletter;
	@FXML private Button btnDelete;
	@FXML private Button btnEdit;
	
	private ObservableList<KundeModel> kundenListe = FXCollections.observableArrayList();

	private Stage primaryStage;
	
	public void setPrimaryStage(Stage s){
		this.primaryStage = s;
	}
	
	public void loadData() {
		KundeModel kunde1 = new KundeModel("Savas", "Celik", "Ifangstrasse 58", 8153, "Rümlang", "mehmet.celik@gmail.com", "20.04.1337", "Premium", false);
		kundenListe.add(kunde1);
		KundeModel kunde2 = new KundeModel("Hans", "Peter", "Tösstalstrasse 87", 8400, "Winterthur", "ahns.peter@hotmail.com", "22.12.2018", "Standard", true);
		kundenListe.add(kunde2);
		KundeModel kunde3 = new KundeModel("Mehmet", "Celik", "Ifangstrasse 58", 8153, "Rümlang", "mehmet.celik@gmail.com", "20.04.1337", "Premium", false);
		kundenListe.add(kunde3);
		KundeModel kunde4 = new KundeModel("Fridrick", "Fridrickson", "Bahnhofstrasse 75", 4313, "Möhlin", "fridrick.fridrickson@bluewin.ch", "01.01.2018", "Gold", true);
		kundenListe.add(kunde4);
		tblKunde.setItems(kundenListe);
	}

	@FXML
	private void initialize() {
		colVorname.setCellValueFactory(cellData -> cellData.getValue().getVorname());
		colNachname.setCellValueFactory(cellData -> cellData.getValue().getNachname());
		tblKunde.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showDetail(newValue)
			);
	}

	@FXML
	private void showDetail(KundeModel newValue) {
		if(kundenListe.size() > 0) {
			btnDelete.setDisable(false);
			btnEdit.setDisable(false);
			lblVorname.setText(newValue.getVorname().getValue());
			lblNachname.setText(newValue.getNachname().getValue());
			lblStrasse.setText(newValue.getStrasse().getValue());
			lblPlzOrt.setText(newValue.getPlz().getValue().toString() + " " + newValue.getOrt().getValue());
			lblEmail.setText(newValue.getEmail().getValue());
			lblKundeSeit.setText(newValue.getKundeSeit().getValue());
			lblStatus.setText(newValue.getArt().getValue());
			if (newValue.getNewsletter().getValue()) {
				lblNewsletter.setText("Ja");
			} else {
				lblNewsletter.setText("Nein");
			}
		} else {
			btnDelete.setDisable(true);
			btnEdit.setDisable(true);
			lblVorname.setText("");
			lblNachname.setText("");
			lblStrasse.setText("");
			lblPlzOrt.setText("");
			lblEmail.setText("");
			lblKundeSeit.setText("");
			lblStatus.setText("");
			lblNewsletter.setText("");
		}
	}
	
	@FXML
	private void aboutMenuPressed() {
		Alert alert = new Alert(AlertType.INFORMATION, "Creator: Savas Celik"
				+ "\rWiss, Zürich");
		
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.showAndWait();
	}
	
	@FXML
	private void insertButtonPressed() {
		KundeModel neuerKunde = new KundeModel();
		boolean isSaveKlicked = showEditView(neuerKunde);
		if (isSaveKlicked) {
			kundenListe.add(neuerKunde);
		}
	}
	
	@FXML
	private void editButonPressed() {
		KundeModel selektierterKunde = tblKunde.getSelectionModel().getSelectedItem();
		showEditView(selektierterKunde);
	}
	
	@FXML
	private void deleteButtonPressed() {
		KundeModel selectedKunde =  tblKunde.getSelectionModel().getSelectedItem();
		
		if (selectedKunde != null) {
			Alert alert = new Alert(AlertType.WARNING, "Wollen Sie wirklich den Eintrag von " + selectedKunde.getVorname().getValue() + " Löschen ?", ButtonType.OK, ButtonType.CANCEL);
	
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
	
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				kundenListe.remove(selectedKunde);
			}
		}
	}
	
	@FXML
	private boolean showEditView(KundeModel selektierterKunde){
		try {
			// Die EditView laden
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("EditView.fxml"));
			Parent editView = loader.load();
			
			
			// Eine neue Stage für die EditView erstellen
			Stage editStage = new Stage();
			editStage.initOwner(this.primaryStage);
			editStage.setTitle("Kunde bearbeiten");
			editStage.initModality(Modality.WINDOW_MODAL);
			
			//Eine neue Scene mit der EditView erstellen, der EditStage übergeben
			editStage.setScene(new Scene(editView));
			
			// Dem Controller die Stage mitteilen
			EditViewController editController = loader.getController();
			editController.setStage(editStage);
			
//			// Kunde beim klick weitergeben
//			KundeModel selektierterKunde = tblKunde.getSelectionModel().getSelectedItem();
			if(selektierterKunde != null) {
				editController.setKunde(selektierterKunde);
			}
			
			// Die EditStage anzeigen
			editStage.showAndWait();
			if (editController.getIsSaveClicked()) {
				showDetail(selektierterKunde);
				return true;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}

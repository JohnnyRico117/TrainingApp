package trainingapp.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import trainingapp.SQLiteStoring;

public class MainController {

	@FXML
	private BorderPane uebersichtBorderPane;

	@FXML
	private BorderPane essenBorderPane;

	@FXML
	private BorderPane trainingBorderPane;

	@FXML
	private TextField snackNameTextField;

	@FXML
	private TextField snackMengeTextField;

	@FXML
	private TextField snackKalorienTextField;

	@FXML
	private TextField snackFettTextField;

	@FXML
	private TextField snackKohlenhydrateTextField;

	@FXML
	private TextField snackEiweissTextField;

	@FXML
	private Button addSnackButton;

	@FXML
	private Button cancelNewSnackButton;

	@FXML
	void addNewSnack(ActionEvent event) throws Exception {
		String name = snackNameTextField.getText();
		String menge = snackMengeTextField.getText();
		String kalorien = snackKalorienTextField.getText();
		String fett = snackFettTextField.getText();
		String kohlenhydrate = snackKohlenhydrateTextField.getText();
		String eiweiss = snackEiweissTextField.getText();

		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:food.db");
		SQLiteStoring sql = new SQLiteStoring();

		String[] neueZutat = { name, menge, kalorien, fett, kohlenhydrate, eiweiss };
		sql.insertInto(conn, "zutaten", neueZutat);
	}

	@FXML
	void cancelNewSnack(ActionEvent event) {

	}

	@FXML
	void initialize() {
		assert uebersichtBorderPane != null : "fx:id=\"uebersichtBorderPane\" was not injected: check your FXML file 'main.fxml'.";
		assert essenBorderPane != null : "fx:id=\"essenBorderPane\" was not injected: check your FXML file 'main.fxml'.";
		assert trainingBorderPane != null : "fx:id=\"trainingBorderPane\" was not injected: check your FXML file 'main.fxml'.";
		assert snackNameTextField != null : "fx:id=\"snackNameTextField\" was not injected: check your FXML file 'newSnack.fxml'.";
		assert snackMengeTextField != null : "fx:id=\"snackMengeTextField\" was not injected: check your FXML file 'addSnack.fxml'.";
		assert snackKalorienTextField != null : "fx:id=\"snackKalorienTextField\" was not injected: check your FXML file 'addSnack.fxml'.";
		assert snackFettTextField != null : "fx:id=\"snackFettTextField\" was not injected: check your FXML file 'addSnack.fxml'.";
		assert snackKohlenhydrateTextField != null : "fx:id=\"snackKohlenhydrateTextField\" was not injected: check your FXML file 'addSnack.fxml'.";
		assert snackEiweissTextField != null : "fx:id=\"snackEiweissTextField\" was not injected: check your FXML file 'addSnack.fxml'.";
		assert addSnackButton != null : "fx:id=\"addSnackButton\" was not injected: check your FXML file 'addSnack.fxml'.";
		assert cancelNewSnackButton != null : "fx:id=\"cancelSnackButton\" was not injected: check your FXML file 'addSnack.fxml'.";

		// Essen Tab
		VBox essenVBox = new VBox();

		Button hinzu = new Button("Hinzufügen");
		hinzu.setMaxWidth(Double.MAX_VALUE);
		Button neu = new Button("Neu");
		neu.setMaxWidth(Double.MAX_VALUE);
		Button zurueck = new Button("Zurück");
		zurueck.setMaxWidth(Double.MAX_VALUE);

		// Snack Button
		Button snackButton = new Button("Snacks");
		snackButton.setMaxWidth(Double.MAX_VALUE);
		snackButton.setOnAction((snackEvent) -> {

			essenVBox.getChildren().clear();
			hinzu.setOnAction((hinzuEvent) -> {

				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/trainingapp/views/addSnack.fxml"));
					loader.setController(this);
					GridPane pane = loader.load();

					essenBorderPane.getChildren().clear();
					essenBorderPane.setCenter(pane);
				} catch (IOException e) {
					e.printStackTrace();
				}

			});

			neu.setOnAction((neuEvent) -> {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/trainingapp/views/newSnack.fxml"));
					loader.setController(this);
					GridPane pane = loader.load();

					essenBorderPane.getChildren().clear();
					essenBorderPane.setCenter(pane);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			essenVBox.getChildren().addAll(hinzu, neu, zurueck);
		});

		// Gerichte Button
		Button gerichtButton = new Button("Gericht");
		gerichtButton.setMaxWidth(Double.MAX_VALUE);
		gerichtButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		// Zutaten Button
		Button zutatButton = new Button("Zutat");
		zutatButton.setMaxWidth(Double.MAX_VALUE);
		zutatButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		essenVBox.getChildren().addAll(snackButton, gerichtButton, zutatButton);
		essenBorderPane.setCenter(essenVBox);

	}
}

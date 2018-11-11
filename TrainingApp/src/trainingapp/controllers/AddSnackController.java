package trainingapp.controllers;

import java.sql.Connection;
import java.sql.DriverManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import trainingapp.SQLiteStoring;

public class AddSnackController {

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
	private Button cancelSnackButton;

	@FXML
	void addSnack(ActionEvent event) throws Exception {
		String menge = snackMengeTextField.getText();
		String kalorien = snackKalorienTextField.getText();
		String fett = snackFettTextField.getText();
		String kohlenhydrate = snackKohlenhydrateTextField.getText();
		String eiweiss = snackEiweissTextField.getText();

		System.out.println(menge + kalorien + fett + kohlenhydrate + eiweiss);
		System.out.println(this.getClass().getPackageName());

		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:food.db");
		SQLiteStoring sql = new SQLiteStoring();

		// stat.executeUpdate("drop table if exists zutaten;");

		// stat.executeUpdate("create table if not exists zutaten (name, menge,
		// kalorien, fett, carbs, eiweiss);");

		String[] neueZutat = new String[6];
		neueZutat[0] = "H‰‰‰hnchen";
		neueZutat[1] = "100";
		neueZutat[2] = "145";
		neueZutat[3] = "0.0";
		neueZutat[4] = "6.2";
		neueZutat[5] = "22.2";

		// insertInto(conn, "zutaten", neueZutat);

	}

	@FXML
	void initialize() {
		assert snackMengeTextField != null : "fx:id=\"snackMengeTextField\" was not injected: check your FXML file 'addSnack.fxml'.";
		assert snackKalorienTextField != null : "fx:id=\"snackKalorienTextField\" was not injected: check your FXML file 'addSnack.fxml'.";
		assert snackFettTextField != null : "fx:id=\"snackFettTextField\" was not injected: check your FXML file 'addSnack.fxml'.";
		assert snackKohlenhydrateTextField != null : "fx:id=\"snackKohlenhydrateTextField\" was not injected: check your FXML file 'addSnack.fxml'.";
		assert snackEiweissTextField != null : "fx:id=\"snackEiweissTextField\" was not injected: check your FXML file 'addSnack.fxml'.";
		assert addSnackButton != null : "fx:id=\"addSnackButton\" was not injected: check your FXML file 'addSnack.fxml'.";
		assert cancelSnackButton != null : "fx:id=\"cancelSnackButton\" was not injected: check your FXML file 'addSnack.fxml'.";

	}

}

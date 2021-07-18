package it.polito.tdp.newufosightings;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.newufosightings.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//controller turno A --> switchare al branch master_turnoB per turno B

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextField txtAnno;

    @FXML
    private Button btnSelezionaAnno;

    @FXML
    private ComboBox<String> cmbBoxForma;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private TextField txtT1;

    @FXML
    private TextField txtAlfa;

    @FXML
    private Button btnSimula;

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	if(cmbBoxForma.getValue()==null) {
    		txtResult.appendText("Seleziona una forma nella tendina\n\n");
    		return;
    	}
    	int n;
    	try {
    		n = Integer.parseInt(txtAnno.getText());
    	}catch(NumberFormatException ex) {
    		txtResult.appendText("Errore, Inserisci un anno compreso tra il 1910 e il 2014, estremi compresi!!\n");
    		cmbBoxForma.getItems().clear();
    		return;
    	}
    	if(n<1910 || n>2014) {
    		txtResult.appendText("Errore, Inserisci un anno compreso tra il 1910 e il 2014, estremi compresi!!\n");
    		cmbBoxForma.getItems().clear();
    		return;
    	}
    	String msg = model.doCreaGrafo(n, cmbBoxForma.getValue());
    	txtResult.appendText(msg);
    }

    @FXML
    void doSelezionaAnno(ActionEvent event) {
    	int n;
    	try {
    		n = Integer.parseInt(txtAnno.getText());
    	}catch(NumberFormatException ex) {
    		txtResult.appendText("Errore, Inserisci un anno compreso tra il 1910 e il 2014, estremi compresi!!\n");
    		cmbBoxForma.getItems().clear();
    		return;
    	}
    	if(n<1910 || n>2014) {
    		txtResult.appendText("Errore, Inserisci un anno compreso tra il 1910 e il 2014, estremi compresi!!\n");
    		cmbBoxForma.getItems().clear();
    		return;
    	}
    	cmbBoxForma.getItems().clear();
    	cmbBoxForma.getItems().addAll(model.doSelezionaAnno(n));
    	
    	
    }

    @FXML
    void doSimula(ActionEvent event) {
    	int anno;
    	try {
    		anno = Integer.parseInt(txtAnno.getText());
    	}catch(NumberFormatException ex) {
    		txtResult.appendText("Errore, Inserisci un numero di minuti adatto\n");
    		return;
    	}
    	int t1;
    	try {
    		t1 = Integer.parseInt(txtT1.getText());
    	}catch(NumberFormatException ex) {
    		txtResult.appendText("Errore, Inserisci un numero di minuti adatto\n");
    		return;
    	}
    	if(cmbBoxForma.getValue()==null) {
    		txtResult.appendText("Seleziona una forma nella tendina\n\n");
    		return;
    	}
    	int alfa;
    	try {
    		alfa = Integer.parseInt(txtAlfa.getText());
    	}catch(NumberFormatException ex) {
    		txtResult.appendText("Errore, Inserisci un numero di minuti adatto\n");
    		return;
    	}
    	String msg = model.doSimulator(t1, alfa, anno, cmbBoxForma.getValue());
    	txtResult.appendText(msg);
    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
        assert btnSelezionaAnno != null : "fx:id=\"btnSelezionaAnno\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
        assert cmbBoxForma != null : "fx:id=\"cmbBoxForma\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
        assert txtT1 != null : "fx:id=\"txtT1\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
        assert txtAlfa != null : "fx:id=\"txtAlfa\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
}

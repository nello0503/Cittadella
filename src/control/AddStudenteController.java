package control;

import java.util.ArrayList;
import java.util.List;

import entity.Attivita;
import entity.Studente;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;

public class AddStudenteController {

	@FXML
	private TextField txtCognome;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtMatricola;
	@FXML
	private VBox boxAttivita;

	private List<Attivita> attivitaDisponibili = new ArrayList<>();

	private SistemaIscrizioni sistema;

	private final List<CheckBox> checkBoxes = new ArrayList<>();

	public void setAttivitaDisponibili(List<Attivita> listaAttivita) {
		this.attivitaDisponibili = listaAttivita;
		creaCheckBoxAttivita();

	}

	private void creaCheckBoxAttivita() {
		boxAttivita.getChildren().clear();
		checkBoxes.clear();

		for (Attivita a : attivitaDisponibili) {
			CheckBox cb = new CheckBox(a.getNome() + " (€" +a.getCostoMensile() +")");
			checkBoxes.add(cb);
			boxAttivita.getChildren().add(cb);
		}
	}

	public List<Attivita> getListaAttivita() {
		List<Attivita> attivitaSelezionate = new ArrayList<>();
		for (int i = 0; i < checkBoxes.size(); i++) {
			if (checkBoxes.get(i).isSelected()) {
				attivitaSelezionate.add(attivitaDisponibili.get(i));
			}
		}
		return attivitaSelezionate;

	}

	@FXML
	private void onChiudi(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

	@FXML
	private void initialize() {
		
	}

	@FXML
	private void onSave() {
		// controllo che i campi inseriti siano validi
		if (txtNome.getText().isEmpty() || txtCognome.getText().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Dati non validi");
			alert.setHeaderText(null);
			alert.setContentText("Nome e cognome sono obbligatori");
			alert.showAndWait();
		} else {
			// controlla la presenza del nuovo studente invocando un Alert
			if (sistema.esisteStudente(txtNome.getText(), txtCognome.getText())) {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Studente già presente");
				alert.setHeaderText(null);
				alert.setContentText("Esiste già uno studente con lo stesso nome e cognome.");
				alert.showAndWait();
				// se lo studente non è gia presente viene salvato
			} else {
				Studente s = new Studente(txtNome.getText(), txtCognome.getText(), getListaAttivita());
				sistema.addStudente(s);
				// chiudi la finestra
				Stage stage = (Stage) txtNome.getScene().getWindow();
				stage.close();
			}
		}

	}

	public void setSistema(SistemaIscrizioni sistema) {
		this.sistema = sistema;
	}

}

package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import entity.Attivita;
import entity.Studente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class MainController {

	SistemaIscrizioni sistema = new SistemaIscrizioni();

	List<Attivita> lista = new ArrayList<Attivita>();
	List<Attivita> lista1 = new ArrayList<Attivita>();
	List<Attivita> lista2 = new ArrayList<Attivita>();
	@FXML
	private TableView<Studente> tableStudenti;
	@FXML
	private TableColumn<Studente, String> colNome;
	@FXML
	private TableColumn<Studente, String> colCognome;
	@FXML
	private TableColumn<Studente, Integer> colMatricola;
	@FXML
	private TextField txtCerca;
	@FXML
	private ChoiceBox<Attivita> choiceAttivita;

	private final ObservableList<Studente> studenti = FXCollections.observableArrayList();

	FilteredList<Studente> studentiFiltrati = new FilteredList<>(studenti, s -> true);

	// dati di test
	public void addStudente() {

		Studente s1 = new Studente("Aniello", "Matrisciano", lista);
		Studente s2 = new Studente("Pinco", "Pallino", lista1);
		Studente s3 = new Studente("Pio", "Pino", lista2);
			
		sistema.addStudente(s1);
		sistema.addStudente(s2);
		sistema.addStudente(s3);

	}

	// dati di test
	public void addAttivita() {
		Attivita a1 = new Attivita("Nuoto", 50.0);
		Attivita a2 = new Attivita("Body building", 40.0);
		Attivita a3 = new Attivita("Atletica leggera", 35.0);
		sistema.addAttivita(a1);
		sistema.addAttivita(a2);
		sistema.addAttivita(a3);

		lista.add(a1);
		lista1.add(a2);
		lista2.add(a3);
	}

	@FXML
	public void onApriMascheraAggiungi() {
		try {

			URL url = getClass().getResource("/view/AddStudenteView.fxml");
			FXMLLoader loader = new FXMLLoader(url);
			Parent root = loader.load();

			AddStudenteController ctrl = loader.getController();

			ctrl.setAttivitaDisponibili(sistema.getListaAttivita());
			ctrl.setSistema(sistema);
			Stage dialogStage = new Stage();

			dialogStage.initModality(Modality.APPLICATION_MODAL);
			dialogStage.initOwner(tableStudenti.getScene().getWindow());

			dialogStage.setScene(new Scene(root));
			dialogStage.showAndWait();
			// refresh tabella
			studenti.setAll(sistema.getListaStudenti());
			dialogStage.setTitle("Aggiungi Studente");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void initialize() {
		//addStudente();
		addAttivita();
		studenti.clear();

		// 1) carico le attività
		choiceAttivita.getItems().setAll(sistema.getListaAttivita());

		// 2) aggiungo l'opzione "tutti" come item (null)
		choiceAttivita.getItems().add(0, null);

		// 3) imposto il converter (così null diventa "Tutti gli studenti")
		choiceAttivita.setConverter(new StringConverter<Attivita>() {
			@Override
			public String toString(Attivita a) {
				if (a == null)
					return "Tutti gli studenti";
				String s = a.getNome().replace("_", " ").toLowerCase();
				return Character.toUpperCase(s.charAt(0)) + s.substring(1);
			}

			@Override
			public Attivita fromString(String string) {
				return null;
			}
		});

		choiceAttivita.setValue(null);

		// configurazione delle colonne
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
		colMatricola.setCellValueFactory(new PropertyValueFactory<>("matricola"));

		studenti.setAll(sistema.getListaStudenti());

		studentiFiltrati = new FilteredList<>(studenti, s -> true);
		tableStudenti.setItems(studentiFiltrati);

		choiceAttivita.getSelectionModel().selectedItemProperty().addListener((obs, oldA, newA) -> {
			studentiFiltrati.setPredicate(studente -> {
				if (newA == null)
					return true;
				return studente.getListaAttivita().contains(newA);
			});
		});
		
		choiceAttivita.setValue(null);
		tableStudenti.setVisible(true);

	}


	@FXML
	public void cercaStudente() {

		String text = txtCerca.getText();

		// 1) null/empty/spazi
		if (text == null || text.trim().isEmpty()) {
			showWarning("Errore", "Inserisci matricola valida");
			return;
		}

		// 2) parsing una sola volta
		int matricola;
		try {
			matricola = Integer.parseInt(text.trim());
		} catch (NumberFormatException e) {
			showWarning("Errore", "Inserisci matricola valida (solo numeri)");
			return;
		}

		// 3) ricerca
		Studente s = sistema.cercaStudente(matricola);

		if (s != null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Dati studente");
			alert.setHeaderText(s.getNome() + " " + s.getCognome());
			alert.setContentText("Matricola: " + s.getMatricola() + "\nAttività frequentate:\n" + s.printListaAttivita()
					+ "\nRetta mensile: €" + s.calcolaRetta());
			alert.showAndWait();
		} else {
			showWarning("Errore", "Matricola " + matricola + " non trovata");
		}
	}

	private void showWarning(String title, String msg) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}

}

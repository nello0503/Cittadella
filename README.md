📘 Cittadella – Gestione Studenti e Attività (JavaFX)
📌 Descrizione

Cittadella è un’applicazione desktop sviluppata in JavaFX per la gestione di studenti e delle attività da loro frequentate.
L’app consente di visualizzare, filtrare e cercare studenti, oltre ad aggiungerne di nuovi associandoli a una o più attività.

Il progetto è stato realizzato con particolare attenzione alla separazione tra interfaccia grafica e logica applicativa (FXML + Controller).

🛠 Tecnologie utilizzate

Java 8

JavaFX 8

FXML

IDE consigliato: Eclipse

🧱 Architettura del progetto

Il progetto segue una struttura MVC semplificata:

model

Studente

Attivita

Sistema (gestione dati e operazioni)

control

MainController

AddStudenteController

view

viex.fxml (vista principale)

add_studente.fxml (maschera aggiunta studente)

✨ Funzionalità principali
👥 Gestione studenti

Visualizzazione elenco studenti in una TableView

Ricerca studente per matricola

Visualizzazione dettagli studente (attività e retta)

🎯 Gestione attività

Filtro studenti tramite ChoiceBox

Opzione “Tutti gli studenti”

Aggiornamento dinamico della tabella

➕ Aggiunta studente

Inserimento:

Nome

Cognome

Matricola

Selezione di una o più attività tramite CheckBox

Validazione input (campi vuoti / valori non numerici)

🖥 Interfaccia grafica

Layout basato su BorderPane

Pannello laterale con:

Filtro per attività

Ricerca per matricola

Pulsante di aggiunta studente

Area centrale con TableView

Ricerca attivabile sia con ENTER che con pulsante

⚙️ Avvio dell’applicazione

Importare il progetto in Eclipse

Verificare che JavaFX sia correttamente configurato

Avviare la classe Main

L’interfaccia principale verrà caricata automaticamente

✅ Validazioni implementate

Matricola:

campo obbligatorio

solo numeri

Gestione input errato tramite Alert

Messaggi informativi e di errore chiari per l’utente

🚀 Miglioramenti futuri

Persistenza dati su file o database

Ordinamento colonne della tabella

Ricerca combinata (attività + matricola)

Interfaccia grafica migliorata (CSS)

Eliminazione/modifica studenti

<?php
//imposto i parametri della connessione
$dbhost="127.0.0.1";
$dbuser="root";
$dbpass="";

//connessione con il server( in questo caso in locale)
$conn = mysqli_connect($dbhost,$dbuser,$dbpass);
if(!$conn) die("Errore mysql: ".mtsqli_error($conn));

//Creazione del database
$sql="CREATE DATABASE Mensa";
			$ok=mysqli_query($conn, $sql);
			
			if(!$ok) die("Errore DB non creato: ".mysqli_error($conn));
			echo "DB creato";

//Connessione al database per la creazione delle tabelle
$sql= "USE Mensa";
$ok= mysqli_query($conn,$sql);
if (!$ok) die("impossibile selezionare DB: ".mysqli_error($conn));

//creazione tabella studente
$sql="CREATE TABLE studente (
	idStudente INT(4) PRIMARY KEY,
	nome VARCHAR(16),
	cognome VARCHAR(16),
	classe VARCHAR(4),
	telefono VARCHAR(10),
	indirizzo VARCHAR(20),
	citta VARCHAR(16)
	);";

$ok=mysqli_query($conn,$sql);
if (!$ok) die("Errore Query: ".mysqli_error($conn));

//creazione tabella menu
$sql="CREATE TABLE menu (
	idMenu INT(4) PRIMARY KEY,
	nome VARCHAR(16),
	giorno VARCHAR(16),
	etichetta VARCHAR(16)
	);";

$ok=mysqli_query($conn,$sql);
if (!$ok) die("Errore Query: ".mysqli_error($conn));

//creazione tabella piatto
$sql="CREATE TABLE piatto (
	idPiatto INT(4) PRIMARY KEY,
	nome VARCHAR(16),
	numCalorie INT(5),
	tipologia VARCHAR(16)
	);";

$ok=mysqli_query($conn,$sql);
if (!$ok) die("Errore Query: ".mysqli_error($conn));

//creazione tabella relazione N-N tra studente e piatto
$sql="CREATE TABLE ordinano (
	idStudente INT(4),
	idPiatto INT(4),
	PRIMARY KEY (idStudente,idPiatto),
	FOREIGN KEY (idStudente) REFERENCES studente(idStudente),
	FOREIGN KEY (idPiatto) REFERENCES piatto(idPiatto)
	);";

$ok=mysqli_query($conn,$sql);
if (!$ok) die("Errore Query: ".mysqli_error($conn));

//creazione tabella relazione N-N tra piatto e menu
$sql="CREATE TABLE inserito (
	idPiatto INT(4),
	idMenu INT(4),
	PRIMARY KEY (idMenu,idPiatto),
	FOREIGN KEY (idMenu) REFERENCES menu(idMenu),
	FOREIGN KEY (idPiatto) REFERENCES piatto(idPiatto)
	);";

$ok=mysqli_query($conn,$sql);
if (!$ok) die("Errore Query: ".mysqli_error($conn));
?>
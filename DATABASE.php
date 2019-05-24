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
$sql= "USE dogana";
$ok= mysqli_query($conn,$sql);
if (!$ok) die("impossibile selezionare DB: ".mysqli_error($conn));

//creazione tabella studente
$sql="CREATE TABLE studente (
	idStudente INT(4) PRIMARY KEY,
	nome VARCHAR(16),
	cognome VARCHAR(16),
	classe VARCHAR(4),
	telefono VARCHAR(10),
	indirizzo varcahr(20),
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
	 VARCHAR(16)
	);";

$ok=mysqli_query($conn,$sql);
if (!$ok) die("Errore Query: ".mysqli_error($conn));

$sql="CREATE TABLE esito (
	idEsito INT(4) PRIMARY KEY,
	nome VARCHAR(16)
	);";

$ok=mysqli_query($conn,$sql);
if (!$ok) die("Errore Query: ".mysqli_error($conn));

$sql="CREATE TABLE addetti (
	idAdd INT(4) PRIMARY KEY,
	nome VARCHAR(16),
	cognome VARCHAR(16)
	);";

$ok=mysqli_query($conn,$sql);
if (!$ok) die("Errore Query: ".mysqli_error($conn));

$sql="CREATE TABLE funzionario (
	idFunz INT(4) PRIMARY KEY,
	nome VARCHAR(16),
	cognome VARCHAR(16),
	username VARCHAR(16),
	password VARCHAR(16)
	);";

$ok=mysqli_query($conn,$sql);
if (!$ok) die("Errore Query: ".mysqli_error($conn));

$sql="CREATE TABLE controlli (
	idCtrl INT(4) PRIMARY KEY,
	idFunz INT(4),
	idAdd INT(4),
	idPass INT(4),
	provenienza VARCHAR(16),
	destinazione VARCHAR(16),
	motivo VARCHAR(16),
	inizio DATE,
	fine DATE,
	dazio INT(16),
	idEsito INT(4),
	FOREIGN KEY (idFunz) REFERENCES funzionario(idFunz),
	FOREIGN KEY (idAdd) REFERENCES addetti(idAdd),
	FOREIGN KEY (idPass) REFERENCES passeggero(idPass),
	FOREIGN KEY (idEsito) REFERENCES esito(idEsito)
	);";

$ok=mysqli_query($conn,$sql);
if (!$ok) die("Errore Query: ".mysqli_error($conn));

$sql="CREATE TABLE merce (
	idProd INT(4),
	idCtrl  INT(4),
	idCat INT(4),
	nome VARCHAR(16),
	qta INT(4),
	PRIMARY KEY(idProd,idCtrl),
	FOREIGN KEY (idCtrl) REFERENCES controlli(idCtrl),
	FOREIGN KEY (idCat) REFERENCES categoria(idCat)
	);";

$ok=mysqli_query($conn,$sql);
if (!$ok) die("Errore Query: ".mysqli_error($conn));


?>
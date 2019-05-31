<html>
<head>
	<title> Inserimento </title>
</head>
<body>
<?php
	$idPiatto = $_GET["idPiatto"];
	$nome = $_GET["nome"];
	$numcalorie = $_GET["numcalorie"];
	$tipologia = $_GET["tipologia"];
	
	//parametri della connessione
	$dbhost= "127.0.0.1";
	$dbuser= "root";
	$dbpass= "";
	
	//connessione al server
	$conn = mysqli_connect($dbhost, $dbuser, $dbpass);
	if(!$conn) die ("Errore mysql:".mysqli_error($conn));
	
	//selezione DB
	$sql = "USE Mensa";
	$ok = mysqli_query($conn, $sql);
	if(!$ok) die ("Impossibile selezionare DB:".mysqli_error($conn));
	
	//chiedo al DB se c'è un nome già presente
	$sql = "SELECT * FROM piatto WHERE idPiatto = '$idPiatto'";
	
	//guardo se nel DB c'è già quel nome
	$ok = mysqli_query($conn, $sql);
	
	//se trovo una riga allora il nome c'era già
	if(mysqli_num_rows($ok) != 0)
		echo "Il codice ".$idPiatto."è già presente nel database!";
	else{
		$sql = "INSERT INTO piatto VALUES('$idPiatto','$nome','$numcalorie','$tipologia')";
		$ok = mysqli_query($conn, $sql);
		
		echo "Il codice ".$idPiatto."è stato aggiunto al database!";
	}
mysqli_close($conn);
?>
</body>
</html>
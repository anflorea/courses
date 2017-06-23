<?php
	// Connect to the database server

	$connect = NULL;

	try {
		$connect = new PDO($DB_DSN, $DB_USER, $DB_PASSWORD);
		$connect->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	}
	catch (PDOException $pikachu) {
		echo "DB connection failed: " . $pikachu->getMessage();
	}

	// Create the database

	try {
		$sql = "CREATE DATABASE IF NOT EXISTS " . $DB_NAME;
		$connect->query($sql);
	}
	catch (PDOException $pikachu) {
		echo "DB creation faild: " . $pikachu->getMessage();
	}

	$connect->query("USE " . $DB_NAME);

	// Create table User

	try {
		$sql = "CREATE TABLE IF NOT EXISTS User (
				id INT PRIMARY KEY AUTO_INCREMENT,
				Username VARCHAR(255) NOT NULL,
				File VARCHAR(128) NOT NULL,
				FileLength INT NOT NULL,
				Admin INT(1) DEFAULT 0
		)";
		$connect->query($sql);
	}
	catch (PDOException $pikachu) {
		echo $sql . "<br>" . $pikachu->getMessage();
	}

	// Create table Picture

	try {
		$sql = "CREATE TABLE IF NOT EXISTS Picture (
				IDPicture INT PRIMARY KEY AUTO_INCREMENT,
				Filename VARCHAR(255) NOT NULL,
				Vote INT DEFAULT 0
			)";
		$connect->query($sql);
	}
	catch (PDOException $pikachu) {
		echo $sql . "<br>" . $pikachu->getMessage();
	}

?>

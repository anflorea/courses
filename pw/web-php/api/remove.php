<?php
	session_start();

	require_once '../functions/remove.php';

	$id = $_POST['id'];
	if ($id == null) {
		exit(json_encode("Error. You must provide the id"));
	}

	exit(json_encode(remove_car($id)));

?>
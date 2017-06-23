<?php

	require 'lib/includes.php';

	$_SESSION["theUser"] = null;
  $_SESSION["id"] = null;
  $_SESSION["isAdmin"] = null;
	$_SESSION["error"] = "Logout successful!";
	header('Location: index.php');

?>
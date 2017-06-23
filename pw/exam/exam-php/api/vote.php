<?php
  session_start();

  require_once '../functions/vote.php';

  $val = $_POST["grid"];

  exit(json_encode(makeVote($val)));
?>
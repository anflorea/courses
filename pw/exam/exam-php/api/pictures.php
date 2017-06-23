<?php
  session_start();

  require_once '../functions/read.php';

  $pictures = get_all_pictures();

  exit(json_encode($pictures));
?>
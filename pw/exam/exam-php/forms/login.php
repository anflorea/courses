<?php
    session_start();

    include_once '../functions/login.php';
    include_once '../lib/utils.php';

    $username = test_input($_POST["username"]);
    $file = test_input($_POST["file"]);
    $length = test_input($_POST["length"]);

    $_SESSION["error"] = null;

    if ($username == "" || $username == null || $file == "" || $file == null || $length == "" || $length == null) {
        $_SESSION['error'] = "You need to fill all the fields";
        header("Location: ../index.php");
        return ;
    }

    print_r(login($username, $file, $length));
?>

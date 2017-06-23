<?php
    session_start();

    include_once '../functions/admin.php';
    include_once '../lib/utils.php';

    $file = test_input($_POST["path"]);
    $id = test_input($_POST["id"]);

    $_SESSION["error"] = null;

    if ($id == "" || $id == null || $file == "" || $file == null) {
        $_SESSION['error'] = "You need to fill all the fields";
        header("Location: ../admin.php");
        return ;
    }

    print_r(changeImage($id, $file));
?>
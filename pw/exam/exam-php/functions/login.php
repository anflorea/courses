<?php
    function login($username, $file, $length) {
        require '../config/database.php';

        try {
            $connect = new PDO($DB_DSN, $DB_USER, $DB_PASSWORD);
            $connect->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

            $connect->query("USE " . $DB_NAME);

            $stmt = $connect->prepare("SELECT * FROM User WHERE Username=:user");
            $stmt->execute(array(":user" => $username));
            $rows = $stmt->fetchAll(PDO::FETCH_ASSOC);

            if ($stmt->rowCount() > 0 && $rows[0]["File"] == $file && $rows[0]["FileLength"] == $length) {
                $_SESSION["theUser"] = $username;
                $_SESSION["id"] = $rows[0]["id"];
                $_SESSION["isAdmin"] = $rows[0]["Admin"];
                header('Location: ../home.php');
            } else {
                $_SESSION['error'] = "Login failed.";
                header('Location: ../index.php');
                // return $rows;
            }
        }
        catch (PDOException $pikachu) {
            return $pikachu->getMessage();
        }
    }
?>
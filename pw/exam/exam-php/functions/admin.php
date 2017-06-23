<?php
    function changeImage($id, $file) {
      require '../config/database.php';


      try {
            $connect = new PDO($DB_DSN, $DB_USER, $DB_PASSWORD);
            $connect->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

            $connect->query("USE " . $DB_NAME);

            $stmt = $connect->prepare("UPDATE Picture SET Filename=:file, Vote=0 WHERE IDPicture=:id");
            $stmt->execute(array(":id" => $id, ":file" => $file));


            header("Location: ../home.php");
            
        }
        catch (PDOException $pikachu) {
            return $pikachu->getMessage();
        }
    }
?>
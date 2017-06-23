<?php

  function makeVote($values) {
    require_once '../config/database.php';

      $tab = json_decode($values);
      try {
              $connect = new PDO($DB_DSN, $DB_USER, $DB_PASSWORD);
              $connect->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

              $connect->query("USE " . $DB_NAME);

              $i = 0;
              $j = 0;
              while ($i < 3) {

                $stmt = $connect->prepare("UPDATE Picture SET Vote=:vote WHERE IDPicture=:id");
                $stmt->execute(array(":vote" => $i + $j, ":id" => $tab[$i][$j]));

                $j++;
                if ($j == 3) {
                  $j = 0;
                  $i++;
                }
              }
              $stmt->closeCursor();

              return $tab;
        }
        catch (PDOException $pikachu) {
            return "Error: " . $pikachu->getMessage();
        }
    return ("Success");
  }
?>
<?php

function get_all_pictures() {
      require_once '../config/database.php';

      try {
              $connect = new PDO($DB_DSN, $DB_USER, $DB_PASSWORD);
              $connect->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

              $connect->query("USE " . $DB_NAME);

              $stmt = $connect->prepare("SELECT * FROM Picture");
              $stmt->execute();

              $i = 0;
              $tab = null;
              while ($val = $stmt->fetch()) {
                $tab[$i]['IDPicture'] = $val['IDPicture'];
                $tab[$i]['Filename'] = $val['Filename'];
                $tab[$i]['Vote'] = $val['Vote'];

                $i++;
              }
              $stmt->closeCursor();

              return $tab;
        }
        catch (PDOException $pikachu) {
            return "Error: " . $pikachu->getMessage();
        }
    }

?>
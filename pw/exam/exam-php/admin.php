<?php
  require_once('lib/includes.php');

  if (empty($_SESSION["isAdmin"]) || $_SESSION["isAdmin"] == 0) {
    $_SESSION["error"] = "You must be an admin to access this page";
    header("Location: index.php");
  }

  include 'partials/header.php';
?>
<div class='form-container'>
  <form method="post" action="forms/add.php">
    Picture id: <br> <input type="text" name="id"> <br><br>
    Path: <br> <input type="text" name="path"> <br><br>
    <input type="submit" value="Change image">
  </form>
</div>
<?php
  include 'partials/footer.php';
?>

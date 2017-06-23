<?php
	require_once('lib/includes.php');

	include 'partials/header.php';
?>
<div class='form-container'>
	<form method="post" action="forms/login.php">
		Username: <br> <input type="text" name="username"> <br><br>
		File: <br> <input type="text" name="file"> <br><br>
    FileLength: <br> <input type="text" name="length"> <br><br>
		<input type="submit" value="Login">
	</form>
    <br>
    <span>
            <?php
            echo $_SESSION['error'];
            $_SESSION['error'] = null;
            ?>
    </span>
</div>
<?php
	include 'partials/footer.php';
?>

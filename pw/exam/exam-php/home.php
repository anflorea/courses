<?php
  require_once('lib/includes.php');

  if (empty($_SESSION["theUser"])) {
    $_SESSION["error"] = "Please login first";
    header("Location: index.php");
  }

  include 'partials/header.php';
?>

<script>
  var image = [[0, 0, 0], [0, 0, 0], [0, 0, 0]];
  var votes = [[0, 0, 0], [0, 0, 0], [0, 0, 0]];
  var srcs = [[0, 0, 0], [0, 0, 0], [0, 0, 0]];
</script>

<a href="logout.php"> Logout </a> <br>

<div id='imgDiv'>

</div>

<input type="button" onclick="voteAction()" value="Vote" /> 
<input type="button" onclick="displayImage()" value="Average Image" />

<br><br>
<div id='average'>

</div>

<script>
  $('document').ready(function() {
    getImages();
  });

  function voteAction() {
    $.ajax({
      url: "api/vote.php",
      type: "POST",
      data: {
        "grid": JSON.stringify(image)
      },
      success: function(result) {
        console.log("asdf");
        console.log(result);
      }
    });
  }

  function displayImage() {
    getImages();

    var avrg = 0;
    var mini = 100000000000;
    var theImg = "";
    var theVote = 0;

    for (var i = 0; i < 3; i++) {
      for (var j = 0; j < 3; j++) {
        avrg += parseInt(votes[i][j]);
      }
    }
   avrg /= 9;

   for (var i = 0; i < 3; i++) {
      for (var j = 0; j < 3; j++) {
        if (Math.abs(parseInt(votes[i][j] - avrg)) < mini) {
          mini = Math.abs(parseInt(votes[i][j] - avrg));
          theImg = srcs[i][j];
          theVote = parseInt(votes[i][j]);
        }
      }
    }


    $('#average').append("<img src=" + "res/images/" + theImg + " /><br>Votes: " + theVote);
  }


  function getImages() {

    $('#imgDiv').empty();
    $('#average').empty();
    $.ajax({
      url: "api/pictures.php",
      success: function(result) {
        var obj = jQuery.parseJSON(result);
        var i = 0;
        var j = 0;
        
        
        $.each( obj, function( key, value ) {
            var id = value['IDPicture'];
            var filename = value['Filename'];
            var vote = value['Vote'];
            $('#imgDiv').append("<img src=" + "res/images/" + filename + " />");
            image[i][j] = id;
            votes[i][j] = vote;
            srcs[i][j] = filename;
            j += 1;
            if (j % 3 == 0) {
              $('#imgDiv').append("<br>");
              j = 0;
              i += 1;
            }
            
        });
        console.log(image);
      }
    });
  }

</script>


<?php
  include 'partials/footer.php';
?>

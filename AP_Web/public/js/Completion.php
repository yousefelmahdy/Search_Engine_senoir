<!DOCTYPE html>
<html>
<head>
</head>
<body>
<?php
echo "hallo from php";
$q = $_REQUEST["q"];
$con = mysqli_connect('localhost','root','1234','Project');
if (!$con) {
  die('Could not connect: ' . mysqli_error($con));
}
mysqli_select_db($con);
$sql="SELECT word FROM Searched_Words ORDER BY Rank1 ";
$result = mysqli_query($con,$sql);
//echo $result
if ($q !== "") {
  $q = strtolower($q);
  $len=strlen($q);
  foreach($result as $name) {
    if (stristr($q, substr($name, 0, $len))) {
      if ($hint === "") {
        $hint = $name;
      } else {
        $hint .= ", $name";
      }
    }
  }
}

// Output "no suggestion" if no hint was found or output correct values
echo $hint === "" ? "no suggestion" : $hint;
mysqli_close($con);
?>
</body>
</html>
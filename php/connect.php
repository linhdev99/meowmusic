<?php
    $hostname = "localhost";
    $username = "id17010581_pl99_mp3music";
    $password = "{Nghenhac_Linh99}";
    $database = "id17010581_db_mp3music";

    $con = mysqli_connect($hostname, $username, $password, $database);
    if ($con) {
        echo "Success";
    } 
    else {
        echo "Failure";
    }

    $str_query = "SELECT * FROM topic";
    mysqli_query($con, $str_query);

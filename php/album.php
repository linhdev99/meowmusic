<?php
require "connect.php";

$query = "SELECT DISTINCT 
album.id as idAlbum, 
album.name as nameAlbum, 
album.image as imgAlbum, 
album.idSinger as idSinger, 
singer.name as nameSinger
FROM album 
INNER JOIN singer
ON album.idSinger = singer.id 
ORDER BY rand(".date("Ymd").") LIMIT 4";
$data = mysqli_query($con, $query);

class Album
{
    function Album($idAlbum, $nameAlbum, $imgAlbum, $idSinger, $nameSinger)
    {
        $this->idAlbum = $idAlbum;
        $this->nameAlbum = $nameAlbum;
        $this->imgAlbum = $imgAlbum;
        $this->idSinger = $idSinger;
        $this->nameSinger = $nameSinger;
    }
}

$array = array();

while ($row = mysqli_fetch_assoc($data)) {
    array_push($array, new Album(
        $row['idAlbum'],
        $row['nameAlbum'],
        $row['imgAlbum'],
        $row['idSinger'],
        $row['nameSinger']
    ));
}

echo json_encode($array);

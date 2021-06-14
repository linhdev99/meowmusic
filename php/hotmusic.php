<?php
require "connect.php";

$query = "SELECT 
song.id as idSong,
song.name as nameSong,
song.linkSong as linkSong,
song.image as imgSong,
song.reaction as reactSong,
song.idSinger as idSinger,
song.idAdvertisement as idAdvertisement,
singer.name as nameSinger
FROM song
INNER JOIN singer
ON song.idSinger = singer.id
ORDER BY song.reaction
DESC LIMIT 5";
$data = mysqli_query($con, $query);

class HotMusic
{
    function HotMusic(
        $idSong,
        $nameSong,
        $linkSong,
        $imgSong,
        $reactSong,
        $idSinger,
        $idAdvertisement,
        $nameSinger
    ) {
        $this->idSong = $idSong;
        $this->nameSong = $nameSong;
        $this->linkSong = $linkSong;
        $this->imgSong = $imgSong;
        $this->reactSong = $reactSong;
        $this->idSinger = $idSinger;
        $this->idAdvertisement = $idAdvertisement;
        $this->nameSinger = $nameSinger;
    }
}

$array = array();

while ($row = mysqli_fetch_assoc($data)) {
    array_push($array, new HotMusic(
        $row['idSong'],
        $row['nameSong'],
        $row['linkSong'],
        $row['imgSong'],
        $row['reactSong'],
        $row['idSinger'],
        $row['idAdvertisement'],
        $row['nameSinger']
    ));
}

echo json_encode($array);

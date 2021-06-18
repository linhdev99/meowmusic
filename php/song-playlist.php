<?php
require "connect.php";
$ID_PLAYLIST = $_POST["idPlaylist"];
$str_query = "SELECT 
id,
idPlaylist,
idSong 
FROM songlist 
WHERE songlist.idPlaylist = $ID_PLAYLIST";

$data = mysqli_query($con, $str_query);
class SongPlaylist
{
    function SongPlaylist(
        $id,
        $idPlaylist,
        $idSong
    ) {
        $this->id = $id;
        $this->idPlaylist = $idPlaylist;
        $this->idSong = $idSong;
    }
}
$arraySongPlaylist = array();
while ($row = mysqli_fetch_assoc($data)) {
    array_push($arraySongPlaylist, new SongPlaylist(
        $row['id'],
        $row['idPlaylist'],
        $row['idSong']
    ));
}
echo json_encode($arraySongPlaylist);

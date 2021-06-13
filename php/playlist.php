<?php
require "connect.php";
$str_query = "SELECT id, name, image, icon FROM playlist";

$data = mysqli_query($con, $str_query);
class Playlist
{
    function Playlist(
        $id,
        $name,
        $image,
        $icon
    ) {
        $this->id = $id;
        $this->name = $name;
        $this->image = $image;
        $this->icon = $icon;
    }
}
$arrayPlaylist = array();
while ($row = mysqli_fetch_assoc($data)) {
    array_push($arrayPlaylist, new Playlist(
        $row['idPlaylist'],
        $row['namePlaylist'],
        $row['imgPlaylist'],
        $row['iconPlaylist']
    ));
}
echo json_encode($arrayPlaylist);

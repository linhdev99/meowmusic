<?php
require "connect.php";
$str_query = "SELECT advertisement.id, advertisement.image, advertisement.content, song.id, song.name, song.linkSong
                    FROM song 
                    INNER JOIN advertisement 
                    ON advertisement.id = song.idAdvertisement 
                    WHERE song.idAdvertisement = advertisement.id";

$data = mysqli_query($con, $str_query);
class banner
{
    function banner($id, $image, $content, $idSong, $nameSong, $linkSong)
    {
        $this->id = $id;
        $this->image = $image;
        $this->content = $content;
        $this->idSong = $idSong;
        $this->nameSong = $nameSong;
        $this->imageSong = $linkSong;
    }
}
$arrayBanner = array();
while ($row = mysqli_fetch_assoc($data)) {
    array_push($arrayBanner, new banner(
        $row['id'],
        $row['image'],
        $row['content'],
        $row['id'],
        $row['name'],
        $row['linkSong']
    ));
}
echo json_encode($arrayBanner);

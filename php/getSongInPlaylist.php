<?php
require "connect.php";
$str_query = "SELECT 
playlist.id as idPlaylist,
playlist.name as namePlaylist,
playlist.image as imgPlaylist,
playlist.icon as iconPlaylist,
songlist.idSong as idSong,
song.name as nameSong,
song.linkSong as linkSong,
song.image as imgSong,
song.reaction as reactionSong,
category.name as nameCategory,
category.image as imgCategory,
topic.name as nameTopic,
topic.image as imgTopic,
album.name as nameAlbum,
album.image as imgAlbum,
album.nameSinger as nameSinger
FROM playlist
INNER JOIN songlist ON songlist.idPlaylist = playlist.id
INNER JOIN song ON song.id = songlist.idSong
INNER JOIN category ON category.id = song.idCategory
INNER JOIN topic ON topic.id = category.idTopic
INNER JOIN album ON album.id = song.idAlbum";

$data = mysqli_query($con, $str_query);
class playlist
{
    function playlist(
        $id,
        $name,
        $image,
        $icon,
        $idSong,
        $nameSong,
        $linkSong,
        $imgSong,
        $reactionSong,
        $nameCategory,
        $imgCategory,
        $nameTopic,
        $imgTopic,
        $nameAlbum,
        $imgAlbum,
        $nameSinger,
        $imgBanner,
        $contentBanner
    ) {
        $this->id = $id;
        $this->name = $name;
        $this->image = $image;
        $this->icon = $icon;
        $this->idSong = $idSong;
        $this->nameSong = $nameSong;
        $this->linkSong = $linkSong;
        $this->imgSong = $imgSong;
        $this->reactionSong = $reactionSong;
        $this->nameCategory = $nameCategory;
        $this->imgCategory = $imgCategory;
        $this->nameTopic = $nameTopic;
        $this->imgTopic = $imgTopic;
        $this->nameAlbum = $nameAlbum;
        $this->imgAlbum = $imgAlbum;
        $this->nameSinger = $nameSinger;
        $this->imgBanner = $imgBanner;
        $this->contentBanner = $contentBanner;
    }
}
$arrayPlaylist = array();
while ($row = mysqli_fetch_assoc($data)) {
    array_push($arrayPlaylist, new Playlist(
        $row['idPlaylist'],
        $row['namePlaylist'],
        $row['imgPlaylist'],
        $row['iconPlaylist'],
        $row['idSong'],
        $row['nameSong'],
        $row['linkSong'],
        $row['imgSong'],
        $row['reactionSong'],
        $row['nameCategory'],
        $row['imgCategory'],
        $row['nameTopic'],
        $row['imgTopic'],
        $row['nameAlbum'],
        $row['imgAlbum'],
        $row['nameSinger'],
        $row['imgBanner'],
        $row['contentBanner']
    ));
}
echo json_encode($arrayPlaylist);

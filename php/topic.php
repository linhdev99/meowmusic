<?php
require "connect.php";

$query_topic = "SELECT DISTINCT id, name, image FROM topic ORDER BY rand(" . date("Ymd") . ") LIMIT 4";
$data_topic = mysqli_query($con, $query_topic);

class Topic
{
    function Topic($id, $name, $image)
    {
        $this->id = $id;
        $this->name = $name;
        $this->image = $image;
    }
}

$arrayTopic = array();

while ($row = mysqli_fetch_assoc($data_topic)) {
    array_push($arrayTopic, new topic(
        $row['id'],
        $row['name'],
        $row['image']
    ));
}

echo json_encode($arrayTopic);

<?php
require "connect.php";

$query_category = "SELECT DISTINCT id, name, image FROM category ORDER BY rand(" . date("Ymd") . ") LIMIT 4";
$data_category = mysqli_query($con, $query_category);

class Category
{
    function Category($id, $name, $image)
    {
        $this->id = $id;
        $this->name = $name;
        $this->image = $image;
    }
}

$arrayCategory = array();

while ($row = mysqli_fetch_assoc($data_category)) {
    array_push($arrayCategory, new Category(
        $row['id'],
        $row['name'],
        $row['image']
    ));
}

echo json_encode($arrayCategory);

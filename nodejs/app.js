const express = require("express");
const http = require("http");
const app = express();
var { dbCon } = require("./connection.js");

app.use(express.json());
app.get("/", function (req, res) {
  str_query = "SELECT * FROM topic";
  dbCon.query(str_query, (err, res) => {
    if (err) {
      console.log("Error");
    } else {
      console.log("Success");
    }
  });
});

const server = http.createServer(app);

server.listen(3000, () => {
  console.log("listening on port 3000...");
});

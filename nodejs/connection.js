const mysql = require("mysql");

var dbCon = mysql.createConnection({
  host: "sql205.epizy.com",
  user: "epiz_28846843",
  password: "YsVW3hbM8GDra",
  database: "epiz_28846843_205",
  port: 3306
});

dbCon.connect(function (err) {
  if (!!err) {
    console.log(err);
  } else {
    console.log("success");
  }
});

module.exports = {
  dbCon,
};

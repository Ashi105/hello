var MongoClient = require('mongodb').MongoClient;

var url = "mongodb://localhost:27017/mydb";

document.querySelector("button").addEventListener("click", handleClick);

function handleClick(){
  alert("i got clicked");
}

function myFunction() {
  var z =  document.getElementById("num1").value;
  var x = document.getElementById("mySelect").selectedIndex;
  var p = document.getElementById("mySelect").value;
  var y = document.getElementById("mySelect").options;

  MongoClient.connect(url, function(err, db) {

    if (err) throw err;

    var dbo = db.db("mydb");

    var query = { authorname: "z" };

    dbo.collection("customers").find(query).toArray(function(err, result) {

      if (err) throw err;

      console.log(result);
    });



  alert(p+ y[x].text);
}

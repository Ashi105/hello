var MongoClient = require('mongodb').MongoClient;

var url = "mongodb://localhost:27017/mydb";



MongoClient.connect(url, function(err, db) {

  if (err) throw err;

  console.log("Database created!");

  

});

MongoClient.connect(url, function(err, db) {
  if (err) throw err;
  var dbo = db.db("mydb");
  dbo.createCollection("customers", function(err, res) {
    if (err) throw err;
    console.log("Collection created!");
    db.close();
  });
}); 



MongoClient.connect(url, function(err, db) {

  if (err) throw err;

  var dbo = db.db("mydb");

  var myobj = [

    { title: 'The Three Musketeers', authorname: 'Alexander Dumas', price: 300, publishername:'Pearson',location:'mumbai'},

    { title: 'Identity and Violence : The illusion of Destiny', authorname: 'Amartya Sen', price: 250, publishername:'Reed Elsevier ',location:'delhi'} ,

   { title: 'The Argumentative Indian', authorname: 'Amartya Sen', price: 500, publishername:'The Woodbridge Company Ltd.',location:'bangalore'} ,

   { title: 'Death of City', authorname: 'Amrita Pritam', price: 600, publishername:'The McGrawHill companies',location:'kolkata'} ,

   { title: 'Politics', authorname: 'Aristotle', price: 200, publishername:'Scholastic',location:'delhi'} ,

  

  ];

  dbo.collection("customers").insertMany(myobj, function(err, res) {

    if (err) throw err;

    console.log("Number of documents inserted: " + res.insertedCount);

    db.close();

  });

}); 







MongoClient.connect(url, function(err, db) {

  if (err) throw err;

  var dbo = db.db("mydb");

  var query = { authorname: 'Amartya Sen' };

  dbo.collection("customers").find(query).toArray(function(err, result) {

    if (err) throw err;

    console.log(result);

    

  });

});   





MongoClient.connect(url, function(err, db) {

  if (err) throw err;

  var dbo = db.db("mydb");

  var mysort = { title: 1 };

  dbo.collection("customers").find().sort(mysort).toArray(function(err, result) {

    if (err) throw err;

    console.log(result);

   

  });

}); 



MongoClient.connect(url, function(err, db) {

  if (err) throw err;

  var dbo = db.db("mydb");

  var myquery = { authorname: 'Amrita Pritam' };

  var newvalues = { $set: {price: 700, location:"chennai" } };

  dbo.collection("customers").updateOne(myquery, newvalues, function(err, res) {

    if (err) throw err;

    console.log("1 document updated");

    

  });

}); 



MongoClient.connect(url, function(err, db) {

  if (err) throw err;

  var dbo = db.db("mydb");

  dbo.collection("customers").find().limit(5).toArray(function(err, result) {

    if (err) throw err;

    console.log(result);

    db.close();

  });

}); 
var http = require("http");

http.createServer(function (req,res){
  var body = 'this is the text';
res.end(body);
}).listen(3000);

console.log("server is running");

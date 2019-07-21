const CSVToJSON = require("csvtojson");
const FileSystem = require("fs");
CSVToJSON().fromFile("./view.csv").then(source =>{
console.log(source[0].LCLid);
});

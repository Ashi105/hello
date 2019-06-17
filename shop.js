const path = require('path');

const express = require('express');
const bodyParser = require('body-parser')
const app = express();
app.use(bodyParser.urlencoded({extended: false}));

app.use('/add',(req, res, next) =>{
  console.log('in a middleware');
  res.send('<h1>hello</h1>');
});
app.use('/product',(req, res, next) =>{
  console.log(req.body);
  res.redirect('/');
});
app.use('/',(req, res, next) =>{
  console.log('<h1>hello customer<h1>');

});




app.listen(3000);

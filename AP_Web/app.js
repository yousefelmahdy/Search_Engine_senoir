
const express = require('express');
const app = express();
const ejs = require('ejs');
const path =require('path');
const bodyParser = require('body-parser');
const mysql = require('mysql');
const { get } = require('http');
const natural = require('natural');
const port = process.env.PORT || 4000;


app.use(express.static(path.join(__dirname,'public')));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended:true}));
app.set('views',path.join(__dirname,'views'));
app.set('view engine','ejs');



const conn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password:'1234',
    database: 'Project',
    port: '3306'
});

conn.connect(function(error){
    if(error)
       console.log(error);
    else console.log('Connected');
    });


app.get('/', (req, res) => {
    //res.send("<h2>Hello user from home</h2>");
    res.render('queries');
});

app.post('/links', (req, res) => {
    res.send("<h2>Hello user from home</h2>");
    var query = req.body.query;
    console.log('you have entered ' + query);
    console.log('Data received from DB:');
    var docs=[];
    var stemmed = natural.PorterStemmer.stem(query);
    console.log("word after stemming : ");
    console.log(stemmed);
    query = "SELECT DISTINCT URL , descriptions  " +
            " FROM Frequencies  f,URLs u" +
            " WHERE word = '"+stemmed+"' AND u.noOfDocument = f.noOfDocument " +
            " ORDER BY TF DESC ;";
    conn.query(query,function (err, result, fields) {
        if (err) throw err;
        result.forEach(element => {
            docs.push([element.URL,element.descriptions])
        });
        docs.forEach(element => {
            console.log(element);
        });
      });
    
});
// app.get('/home', (req, res) => {
//     res.send("<h2>Hello user from home</h2>");
// });

app.get('/profile/:personName', (req, res) => {
    const name = req.params.personName;
    res.send("<h2>Hello " + name + "</h2>");
});

app.listen(port, (err) => {
    if (err) return console.log(err);
    console.log(`Listening at port ${port}`);
});


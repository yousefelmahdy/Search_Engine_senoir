
const express = require('express');
const app = express();
const ejs = require('ejs');
const path = require('path');
const bodyParser = require('body-parser');
const mysql = require('mysql');
const { get } = require('http');
const natural = require('natural');
const port = process.env.PORT || 1212;
//const linksR = require('./routes/newlinks');
const Submitted_Links =[];

module.exports.Submitted_Links= Submitted_Links;
app.listen(port, (err) => {
    if (err) return console.log(err);
    console.log(`Listening at port ${port}`);
});

app.use(express.static(path.join(__dirname, 'public')));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');
const conn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '1234',
    database: 'Project',
    port: '3306'
});

app.get('/', (req, res) => {
    var query = "SELECT word FROM Searched_Words ORDER BY Rank1;";
    conn.query(query, function(err, result, fields) {
        if (err) console.log(err); 
        res.render('first_Page',{maryem:result });
    });

});

app.post('/', (req, res) => {  
    var query = req.body.query;
    var stemmed = natural.PorterStemmer.stem(query);
     var q = "SELECT DISTINCT URL , descriptions ,title " +
             " FROM Frequencies  f,URLs u" +
             " WHERE word = '" + stemmed + "' AND u.noOfDocument = f.noOfDocument " +
             " ORDER BY TF DESC ;";
             console.log("you word after stemming = ",stemmed);
    var query_Search_words = req.body.query;
        conn.query(q, function(err, result, fields) {
            console.log(result);
            if (err) console.log("there is an error" + err);
            res.render('demo',{list: result , uq:query}) ;  
        });
});


























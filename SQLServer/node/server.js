var mongoose = require("mongoose");
var express	= require('express');
var server		= express();
var port		= process.env.PORT || 9090;
mongoose.connect("mongodb://localhost/bup");

var ColumnMetaSchema = new mongoose.Schema(
{
	name: String,
	type: String,
	comment: String,
	excelName: String,
	siterraName: String
});

var TableMetaSchema = new mongoose.Schema(
{
	name: String,
	comment: String,
	columns: [ColumnMetaSchema]
});

var SearchSchema = new mongoose.Schema(
{
	name: String,
	table: [TableMetaSchema]
});

var Search = mongoose.model("Search", SearchSchema);

server.configure(function() {
	server.use(express.static(__dirname + '/public'));
	server.use(express.bodyParser());
});
server.listen(port);

server.all('*', function(req, res, next) {
	res.header("Access-Control-Allow-Origin", "*");
	res.header("Access-Control-Allow-Headers", "X-Requested-With, Content-Type");
	next();
});


server.post("/rest/search", function(req, res){
	console.log("SEARCH!!!!");
//	console.log(req.body);
});

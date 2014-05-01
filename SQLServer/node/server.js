var mongoose = require("mongoose");
var express	= require('express');
var server		= express();
var port		= process.env.PORT || 9090;
mongoose.connect("mongodb://localhost/bup");

var ColumnMetaSchema = new mongoose.Schema(
{
	columnName: String,
	selected: Boolean,
	type: String,
	comment: String,
	excelColumnName: String,
	siterraName: String
});

var TableMetaSchema = new mongoose.Schema(
{
	tableName: String,
	selected: Boolean,
	ignored: {type: Boolean, "default": false},
	comment: String,
	columns: [ColumnMetaSchema]
});

var SearchSchema = new mongoose.Schema(
{
	name: String,
	tables: [TableMetaSchema]
});

var Table  = mongoose.model("Table",  TableMetaSchema);
var Search = mongoose.model("Search", SearchSchema);

server.configure(function() {
	server.use(express.static(__dirname + '/public'));
	server.use(express.bodyParser());
});
server.listen(port);

server.all('*', function(req, res, next) {
	res.header("Access-Control-Allow-Origin", "*");
	res.header("Access-Control-Allow-Headers", "X-Requested-With, Content-Type");
	res.header("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
	next();
});

server.post("/rest/search", function(req, res){
	console.log("SEARCH!!!!");
	console.log(req.body);
	var search = req.body;
	var searchName = search.name;
	var tableName = search.tables[0].tableName;
	var columns = search.tables[0].columns;
	console.log(columns);
	
	Search.create(search, function(err, search){
		Search.find(function(err, searches){
			res.json(searches);
		});
	});
});

server.put("/rest/search/:id", function(req, res){
	Search.findById(req.params.id, function(err, search){
		search.name = req.body.name;
		search.save();
		res.json(search);
	});
});

server.get("/rest/search", function(req, res){
	Search.find(function(err, searches){
		res.json(searches);
	});
});

server.get("/rest/search/:id", function(req, res){
	Search.findById(req.params.id, function(err, search){
		res.json(search);
	})
});

server.delete("/rest/search/:id", function(req, res){
	Search.findById(req.params.id, function(err, search){
		search.remove(function(err, search){
			Search.find(function(err, searches){
				res.json(searches);
			});
		});
	})
});

server.put("/rest/table/ignore", function(req, res){
	console.log(req);
});

server.put("/rest/table/:id", function(req, res){
	console.log(req.params.id);
//	/*
	var table = new Table(req.body.table);
	table.tableName = req.body.name;
	table.ignored = req.body.ignored;
	var tableObj = table.toObject();
//	delete tableObj._id;
	// */
	
	Table.findById(req.params.id, function(err, existingTable){
		if(err) {
			Table.create(tableObj, function(err, table){
				res.json(table);
			});
		} else {
			if(existingTable != null) {
				existingTable.remove();
			} else {
				Table.create(tableObj, function(err, table){
					res.json(table);
				});
			}
		}
	});
	
	/*
	Table.update({_id:table._id}, tableObj, {upsert: true}, function(err, table){
		console.log(err);
		console.log(table);
		res.json(table);
	});
	*/
});


server.get("/rest/table", function(req, res){
	Table.find(function(err, tables){
		res.json(tables);
	});
});

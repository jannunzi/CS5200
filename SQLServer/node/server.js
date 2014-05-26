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

var ImageCaptionSchema = new mongoose.Schema({
	text: String,
	title: String,
	top: Number,
	left: Number,
	width: Number,
	height: Number
});

var ImageSchema = new mongoose.Schema({
	fileName: String,
	captions: [ImageCaptionSchema]
});

var Image  = mongoose.model("Image", ImageSchema);

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
	});
});

server.put("/rest/table/ignore/:ignore", function(req, res){
	var ignore = req.params.ignore;
	var tableNames = req.body.tableNames
	console.log(tableNames);

	for(var t in tableNames) {
		var table = new Table({
			tableName: tableNames[t],
			ignored: ignore
		});
		var tableData = table.toObject();
		delete tableData._id;
		Table.update({tableName: tableNames[t]}, tableData, {upsert: true}, function(err, table){
			console.log(err);
			console.log(table);
//			console.log(tableName);
//			console.log(name);
			/*
			if(table === null) {
				Table.create(table, function(err, table){
					console.log(table);
				});
			} else {
				table.ignore = true;
				table.save();
			}
			*/
		});
	}
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

server.post("/rest/image/:imageFileName", function(req, res) {
	Image.create(req.body, function(err, image){
		console.log(image);
		Image.find(function(err, images){
			res.json(images);
		});
	});
});

server.get("/rest/image", function(req, res){
	Image.find(function(err, images){
		res.json(images);
	});
});

server.get("/rest/image/:id", function(req, res){
	Image.findById(req.params.id, function(err, image){
		res.json(image);
	});
});

server.delete("/rest/image/:id", function(req, res){
	Image.findById(req.params.id, function(err, image){
		image.remove(function(err, i){
			Image.find(function(err, images){
				res.json(images);
			});
		});
	});
});

/*
 * Image Captions
 */

server.post("/rest/image/:id/caption", function(req, res){
	var caption = req.body;
	Image.findById(req.params.id, function(err, image){
		image.captions.push(caption);
		image.save(function(err, img){
			res.json(img);
		});
	});
});

server.put("/rest/image/:image_id/caption", function(req, res){
	var newCaptions = req.body;
	Image.findById(req.params.image_id, function(err, image){
		for(var c in newCaptions) {
			var newCaption = newCaptions[c];
			var caption = image.captions.id(newCaption._id);
			caption.top = newCaption.top;
			caption.left = newCaption.left;
			caption.width = newCaption.width;
			caption.height = newCaption.height;
			caption.text = newCaption.text;
			caption.title = newCaption.title;
		};
		image.save(function(err){;});
	});
});

server.delete("/rest/image/:image_id/caption/:caption_id", function(req, res){
	Image.findById(req.params.image_id, function(err, image){
		var caption = image.captions.id(req.params.caption_id);
		caption.remove();
		image.save(function(err, image){
			res.json(image.captions);
		});
	});
});

// not used yet
server.put("/rest/image/:image_id/caption/:caption_id", function(req, res){
	var newCaption = req.body;
	Image.findById(req.params.image_id, function(err, image){
		image.captions.id(req.params.caption_id, function(err, caption){
			caption.top = newCaption.top;
			caption.left = newCaption.left;
			caption.save(function(err, caption){
				Image.findById(req.params.image_id, function(err, image){
					res.json(image);
				});
			});
		});
	});
});

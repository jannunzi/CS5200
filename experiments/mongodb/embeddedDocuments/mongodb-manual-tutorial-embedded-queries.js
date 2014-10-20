/*
	These examples are taken from
	http://docs.mongodb.org/manual/tutorial/query-documents/
	data is stored in local mongodb instance, test database, embed collection
*/


var mongojs = require("mongojs");
var db = mongojs("test", ["embed"]);

db.embed.find( { 'memos.0.by': 'shipping' }, function(err, docs){
	console.log("\n\nMatch a Field in the Embedded Document Using the Array Index");
	console.log(docs);
});

db.embed.find( { 'memos.by': 'shipping' }, function(err, docs){
	console.log("\n\nMatch a Field Without Specifying Array Index");
	console.log(docs);
});


//Single Element Satisfies the Criteria

db.embed.find(
   {
     memos:
       {
          $elemMatch:
            {
               memo: 'on time',
               by: 'shipping'
            }
       }
    }
, function(err, docs){
	console.log("\n\nSingle Element Satisfies the Criteria");
	console.log(docs);
});


// Combination of Elements Satisfies the Criteria
db.embed.find(
  {
    'memos.memo': 'on time',
    'memos.by': 'shipping'
  }
, function(err, docs){
	console.log("\n\nCombination of Elements Satisfies the Criteria");
	console.log(docs);
	for(var i=0; i<docs.length; i++) {
		console.log(docs[i].memos[0].memo);
	}
});




/*
{
        "_id" : ObjectId("5433f7c47b683cb3519a2d78"),
        "type" : "food",
        "item" : "xyz",
        "qty" : 25,
        "price" : 2.5,
        "ratings" : [
                5,
                8,
                9
        ],
        "memos" : [
                {
                        "memo" : "on time",
                        "by" : "shipping"
                },
                {
                        "memo" : "approved",
                        "by" : "billing"
                }
        ]
}
{
        "_id" : ObjectId("5433f7d67b683cb3519a2d79"),
        "type" : "fruit",
        "item" : "jkl",
        "qty" : 10,
        "price" : 4.25,
        "ratings" : [
                5,
                9
        ],
        "memos" : [
                {
                        "memo" : "on time",
                        "by" : "payment"
                },
                {
                        "memo" : "delayed",
                        "by" : "shipping"
                }
        ]
}
*/
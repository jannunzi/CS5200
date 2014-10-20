var mongojs = require("mongojs");
var db = mongojs("test", ["users", "applications"]);

// Some apps:
/*
var contactApp = {
	name: "Contact App",
	developer: "alice@wonderland",
	entities: [
	{	name: "Contact",
		fields:[]},
	{	name: "Users"}
	]
};
*/
//*
var todoApp = {
	name: "Todo App",
	developer: "alice@wonderland",
	entities: [
	{	name: "Task",
		fields: [
		{	name: "Task Name", type: "TEXT", defaultValue: "Unamed Task"},
		{	name: "Date Created", type: "DATE", defaultValue: "NOW"}
		]},
	{	name: "Users",
		fields: [
		{	name: "Username", type: "TEXT"},
		{	name: "Email", type: "EMAIL"},
		{	name: "Tasks", type: "COLLECTION", collectionType: "Task"}
		]}
	]
	};	
db.applications.insert(todoApp);
//*/
// Create some developers
/*
var alice = {name: "Alice Wonderland", _id: "alice@wonderland"};
var bob = {name: "Bob Marley", _id: "bob@marley"};
db.users.insert(alice);
db.users.insert(bob);
*/

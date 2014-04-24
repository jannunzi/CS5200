var f360 = angular.module("f360", ["ngRoute"]);

f360.config(["$routeProvider", function($routeProvider, $http)
{
	$routeProvider
	.when("/",
	{
		templateUrl: "partials/login.html",
		controller: "LoginController"
	})
	.when("/login",
	{
		templateUrl: "partials/login.html",
		controller: "LoginController"
	})
	.when("/register",
	{
		templateUrl: "partials/register.html",
		controller: "RegisterController"
	})
	.when("/:username/trip/new",
	{
		templateUrl: "partials/trip/new.html",
		controller: "NewTripController"
	})
	.when("/:username/trip/list",
	{
		templateUrl: function(ewq) {
			console.log(ewq);
			return "partials/trip.html"},
		controller: "TripListController"
	})
	.when("/:username/trip/:tripId/fish/list",
	{
		templateUrl: "partials/fish/list.html",
		controller: "FishListController"
	})
	.when("/:username/trip/:tripId/fish/new",
	{
		templateUrl: "partials/fish/new.html",
		controller: "NewFishController"
	})
	.when("/:username/trip/:tripId/fish/:fishId/edit",
	{
		templateUrl: "partials/fish/edit.html",
		controller: "EditFishController"
	})
	.otherwise({
		templateUrl: "partials/login.html",
		controller: "LoginController"
	})
	;
}]);

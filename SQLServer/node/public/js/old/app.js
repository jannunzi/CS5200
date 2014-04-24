var f360 = angular.module("f360", ["ngRoute"]);

f360.config(["$routeProvider", function ($routeProvider) {
    $routeProvider.
    when("/", {
        templateUrl: "partials/login.html",
        controller: "LoginController"
    }).
    when("/login", {
        templateUrl: "partials/login.html",
        controller: "LoginController"
    }).
    when("/profile/:username", {
        templateUrl: "partials/profile.html",
        controller: "ProfileController"
    }).
    when("/profile/:username/trip/:tripId", {
        templateUrl: "partials/trip.html",
        controller: "TripController"
    }).
    when("/profile/:username/trip/:tripId/fish/:fishId", {
        templateUrl: "partials/fish.html"
    }).
    otherwise({
        templateUrl: "partials/login.html",
        controller: "LoginController"
    });
}]);

f360.controller("LoginController", function ($scope) {
    $scope.username = "user1";
    console.log("LoginController");
});

f360.controller("ProfileController", function ($scope, $routeParams) {
    $scope.addTrip = function () {
        var newTrip = {
            id: $scope.newTripName,
            name: $scope.newTripName,
            date: $scope.newTripDate,
            place: $scope.newTripPlace,
            cost: $scope.newTripCost,
            fish: {}
        };
        console.log(newTrip);
        $scope.trips[$scope.newTripName] = newTrip;
    };
    var username = $routeParams.username;
    console.log(username);
    $scope.currentUser = data[username];
    $scope.trips = data[username].trips;
});

f360.controller("TripController", function ($scope, $routeParams) {
    var username = $routeParams.username;
    var tripId = $routeParams.tripId;
    $scope.currentUser = data[username];
    $scope.trip = data[username].trips[tripId];
    $scope.fishes = data[username].trips[tripId].fish;
});
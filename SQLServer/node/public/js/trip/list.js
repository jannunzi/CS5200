
f360.controller("TripListController", function($scope, $routeParams, $http)
{
	$scope.username = $routeParams.username;
	$http.get("api/"+$scope.username+"/trip")
	.success(function(trips){
		$scope.trips = trips;
	});
});

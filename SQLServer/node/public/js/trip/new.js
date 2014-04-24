
f360.controller("NewTripController", function($scope, $routeParams, $http)
{
	$scope.username = $routeParams.username;
	$scope.create = function() {
		$http.post("api/"+$scope.username+"/trip", $scope.newTrip);
	}
});

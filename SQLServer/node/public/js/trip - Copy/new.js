
f360.controller("NewFishController", function($scope, $routeParams, $http)
{
	$scope.username = $routeParams.username;
	$scope.tripId = $routeParams.tripId;
	$scope.create = function() {
		$http.post("api/"+$scope.username+"/trip/"+$scope.tripId+"/fish", $scope.newFish);
	}
});

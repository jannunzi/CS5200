
f360.controller("FishListController", function($scope, $routeParams, $http)
{
	$scope.username = $routeParams.username;
	$scope.tripId = $routeParams.tripId;
	$http.get("api/"+$scope.username+"/trip/"+$scope.tripId+"/fish")
	.success(function(fish){
		$scope.fish = fish;
	});
});

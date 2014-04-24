
f360.controller("EditFishController", function($scope, $routeParams, $http)
{
	$scope.update = function() {
		$http.put("api/"+$scope.username+"/trip/"+$scope.tripId+"/fish/"+$scope.fishId, $scope.editFish)
		.success(function(fish){
			$scope.editFish = fish;
		});
	};
	
	$scope.remove = function() {
		$http.delete("api/"+$scope.username+"/trip/"+$scope.tripId+"/fish/"+$scope.fishId)
		.success(function(fish){
			$scope.fish = fish;
		});
	};
	
	$scope.username = $routeParams.username;
	$scope.tripId = $routeParams.tripId;
	$scope.fishId = $routeParams.fishId;

	$http.get("api/"+$scope.username+"/trip/"+$scope.tripId+"/fish/"+$scope.fishId)
	.success(function(fish){
		$scope.editFish = fish;
	});
});

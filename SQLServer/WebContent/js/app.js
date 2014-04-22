function TableController($scope, $http) {
	$http.get("api/table")
		.success(function(tables){
			$scope.tables = tables;
		});
}
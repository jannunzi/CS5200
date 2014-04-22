function TableController($scope, $http) {
	$http.get("api/table")
		.success(function(response){
			console.log(response);
		});
}
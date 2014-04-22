function TableController($scope, $http) {
	$scope.selectTable = function(index) {
		var table = $scope.tables[index];
		console.log(table);
		console.log(table.selected);
	}
	$scope.selectColumn = function(table, index) {
		var column = table.columns[index];
	}
	$scope.toggleTable = function(index) {
		var table = $scope.tables[index];
		console.log(table.show);
		if(table.columns == null) {
			$http.get("api/table/"+table.name+"/column")
			.success(function(columns){
				$scope.tables[index].columns = columns;
			});
		}
		if(table.show == true) {
			table.show = false;
		} else {
			table.show = true;
		}
	}
	$scope.exportToExcel = function() {
		$http.get("api/table/excel")
			.success(function(response){console.log(response);})
		
	}
	$http.get("api/table")
		.success(function(tables){
			$scope.tables = tables;
		});
}
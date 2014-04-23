function TableController($scope, $http) {
	$scope.selectTable = function(index) {
		var table = $scope.tables[index];
		console.log(table);
		console.log(table.selected);
	}
	$scope.selectColumn = function(table, index) {
		var column = table.columns[index];
	}
	$scope.toggleAllTables = function() {
		$(".table-name").each(function(){
			var link = $(this);
			setTimeout(function() { link.click(); }, 100);
		});
	}
	$scope.toggleTable = function(index) {
		var table = $scope.tables[index];
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
		var selected = [];
		for(var t=0; t<$scope.tables.length; t++) {
			var table = $scope.tables[t];
			if(table.selected) {
				for(var c=0; c<table.columns.length; c++) {
					var column = table.columns[c];
					var selection = {table: table.name, column: column.name, excelColumn: column.excelColumnName};
					selected.push(selection);
				}
			} else {
				if(table.columns) {
					for(var c=0; c<table.columns.length; c++) {
						var column = table.columns[c];
						if(column.selected) {
							var selection = {table: table.name, column: column.name, excelColumn: column.excelColumnName};
							selected.push(selection);
						}
					}
				}
			}
		}
		console.log(selected);
		$http.get("api/table/excel")
			.success(function(response){console.log(response);})
	}
	$http.get("api/table")
		.success(function(tables){
			$scope.tables = tables;
		});
}
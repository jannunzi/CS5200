function TableController($scope, $http) {
	$scope.selectTable = function(table) {
//		table.selected = true;
	}
	$scope.selectColumn = function(table, column) {
//		column.selected = true;
	}
	$scope.toggleAllTables = function() {
		for(var t in $scope.tables) {
			$scope.toggleTable($scope.tables[t]);
		}
	}
	$scope.toggleTable = function(table, callback) {
		$scope.loadColumns(table, callback);
		if(table.show == true) {
			table.show = false;
		} else {
			table.show = true;
			if(table.columns == null)
				table.loading = true;
		}
	}
	
	$scope.expandTable = function(table, callback) {
		if(table.columns == null) {
			table.loading = true;
			$scope.loadColumns(table, function(){
				table.loading = false;
				table.show = true;
				if(typeof callback === "function")
					setTimeout(callback, 200);
			});
		} else {
			table.show = true;
			setTimeout(callback, 200);
		}
	}
	
	$scope.loadColumns = function(table, callback) {
		if(table.columns == null) {
			$http.get("api/table/"+table.name+"/column")
			.success(function(columns){
				table.columns = columns;
				table.loading = false;
				if(typeof callback === "function")
					setTimeout(callback, 200);
			});
		}		
	}
	
	$scope.updateUiFromSearch = function(search) {
//		$http.post("api/table/excel", search.tables)
	//	.success(function(response){console.log(response);});

		
		// unselect all tables and columns
		$scope.collapseAllTables();
		$scope.expandTablesFromSearch(search);
	}
	
	$scope.selectColumnsFromSearch = function(search) {
		var tables = search.tables;
		for(var tt=0; tt<tables.length; tt++) {
			for(var t=0; t<$scope.tables.length; t++) {
				if($scope.tables[t].name === tables[tt].tableName) {
					for(var cc=0; cc<tables[tt].columns.length; cc++) {
						if(tables[tt].columns[cc].selected) {
							if($scope.tables[t].columns)
							for(var c=0; c<$scope.tables[t].columns.length; c++) {
								if(tables[tt].columns[cc].columnName === $scope.tables[t].columns[c].name) {
									$scope.tables[t].columns[c].selected = true;
									var tableId = "#"+$scope.tables[t].name;
									var columnId = tableId + "-" + $scope.tables[t].columns[c].name;
									$(columnId).click();
									break;
								}
							}
						}
					}
				}
			}
		}
	}
	
	$scope.expandTablesFromSearch = function(search) {
		var tables = search.tables;
		for(var tt=0; tt<tables.length; tt++) {
			for(var t=0; t<$scope.tables.length; t++) {
				if($scope.tables[t].name === tables[tt].tableName) {
					$scope.expandTable($scope.tables[t], function() {
						setTimeout(function(){
							$scope.selectColumnsFromSearch(search)
						}, 200);
					});
				}
			}
		}
	}
	
	$scope.collapseAllTables = function(table) {
		for(var t=0; t<$scope.tables.length; t++) {
			var table = $scope.tables[t];
			table.selected = false;
			table.show = false;
			if(table.columns) {
				for(var c=0; c<table.columns.length; c++) {
					var column = table.columns[c];
					column.selected = false;
				}
			}
		}
	}
	
	$scope.parseSelected = function() {
		var selected = [];
		for(var t=0; t<$scope.tables.length; t++) {
			var table = $scope.tables[t];
			if(table.selected === true) {
				var tableObj = {tableName: table.name, selected: true, columns: []};
				for(var c=0; c<table.columns.length; c++) {
					var column = table.columns[c];
					var columnObj = {columnName: column.name, selected: true, excelColumnName: column.excelColumnName};
					tableObj.columns.push(columnObj);
				}
				selected.push(tableObj);
			} else {
				if(table.columns) {
					var tableObj = {tableName: table.name, columns: []};
					var add = false;
					for(var c=0; c<table.columns.length; c++) {
						var column = table.columns[c];
						if(column.selected) {
							var columnObj = {columnName: column.name, selected: true, excelColumnName: column.excelColumnName};
							tableObj.columns.push(columnObj);
							add = true;
						}
					}
					if(add)
						selected.push(tableObj);
				}
			}
		}
		return selected;
	}
	
	$scope.saveQuery = function() {
		var search = { tables: $scope.parseSelected(), name: $scope.searchName };
		console.log(search);		
		$http.post("http://localhost:9090/rest/search", search)
		.success(function(searches){
			$scope.searches = searches;
		})
		.error(function(response){console.log(response);});
	}
	$scope.exportToExcel = function() {
		var selected = $scope.parseSelected();
		console.log(selected);
		$http.post("api/table/excel", selected)
			.success(function(response){console.log(response);});
	}
	$http.get("api/table")
		.success(function(tables){
			$scope.tables = tables;
		});
	$http.get("http://localhost:9090/rest/search")
	.success(function(searches){
		$scope.searches = searches;
	});
	
	$scope.selectSearch = function(search) {
		$http.get("http://localhost:9090/rest/search/"+search._id)
		.success(function(search){
			$scope.updateUiFromSearch(search);
		})
		.error(function(response){console.log("unable to remove");});
	}
	
	$scope.deleteSearch = function(search) {
		$http.delete("http://localhost:9090/rest/search/"+search._id)
		.success(function(searches){$scope.searches = searches;})
		.error(function(response){console.log("unable to remove");});
	}
}
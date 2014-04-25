function TableController($scope, $http) {
	$scope.selectTable = function(table) {

	}
	$scope.selectColumn = function(table, column) {
		
	}
	$scope.toggleAllTables = function() {
		for(var t in $scope.tables) {
			$scope.toggleTable($scope.tables[t]);
		}
	}
	$scope.toggleTable = function(table) {
		if(table.columns == null) {
			$http.get("api/table/"+table.name+"/column")
			.success(function(columns){
				table.columns = columns;
			});
		}
		if(table.show == true) {
			table.show = false;
		} else {
			table.show = true;
		}
	}
	
	$scope.updateSelected = function(search) {
		
		// jga
		
//		$http.post("api/table/excel", search.tables)
	//	.success(function(response){console.log(response);});

		
		// unselect all tables and columns
		/*
		for(var t=0; t<$scope.tables.length; t++) {
			var table = $scope.tables[t];
			table.selected = false;
			if(table.columns) {
				for(var c=0; c<table.columns.length; c++) {
					var column = table.columns[c];
					column.selected = false;
				}
			}
		}
		*/
		// expand tables in search
		var tables = search.tables;
		console.log(tables);
		for(var t=0; t<$scope.tables.length; t++) {
			for(var tt=0; tt<tables.length; tt++) {
				if($scope.tables[t].name === tables[tt].tableName) {
					$scope.toggleTable($scope.tables[t]);
					
				}
			}
		}
/*		
		for(var t=0; t<tables.length; t++) {
			var table = tables[t];
			var tableId = "#"+table.tableName;
			$scope.toggleTable(t);
//			setTimeout(function(){$(tableId).click();}, 100);
			var columns = table.columns;
			for(var c=0; c<columns.length; c++) {
				var column = columns[c];
				var columnId = tableId + "-" + column.columnName;
				console.log(columnId);
				setTimeout(function(){$(columnId).click();}, c*2000);
			}
		}
	*/
	}
	$scope.parseSelected = function() {
		var selected = [];
		for(var t=0; t<$scope.tables.length; t++) {
			var table = $scope.tables[t];
			if(table.selected === true) {
				var tableObj = {tableName: table.name, columns: []};
				for(var c=0; c<table.columns.length; c++) {
					var column = table.columns[c];
					var columnObj = {columnName: column.name, excelColumnName: column.excelColumnName};
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
							var columnObj = {columnName: column.name, excelColumnName: column.excelColumnName};
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
			$scope.updateSelected(search);
		})
		.error(function(response){console.log("unable to remove");});
	}
	
	$scope.deleteSearch = function(search) {
		$http.delete("http://localhost:9090/rest/search/"+search._id)
		.success(function(searches){$scope.searches = searches;})
		.error(function(response){console.log("unable to remove");});
	}
}
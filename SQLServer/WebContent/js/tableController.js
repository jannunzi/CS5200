function TableController($scope, $http) {
	
	$scope.exportSchema = function()
	{
		console.log("export");
		var tableNames = "ATC_REDEV_TASK_DET_TMP,ATC_REDEV_TASK_DETAILS,ATC_REDEV_TASK,ATC_REDEV_PROJECT,ATC_REDEV_OASIS_EXTRACT,ATC_REDEV_LIGHTING_TYPES,ATC_REDEV_CUSTOMERS,ATC_REDEV_CONTACTS";
		$http.get("api/table/schema/ORADEVDB1/"+tableNames);
	};
	
	$scope.maxHeight = {"max-height": "300px", "overflow-y": "scroll", "overflow-x": "hidden"};
	$scope.busy = false;
	$scope.tableFieldHeightTruncated = true;
	$scope.truncateTableFieldHeight = function() {
		if($scope.tableFieldHeightTruncated) {
			$scope.maxHeight = {"max-height": "300px", "overflow-y": "scroll", "overflow-x": "hidden"};
		} else {
			$scope.maxHeight = {};			
		}
	};
	
	// choose database
	$scope.dataSources = [
        {name: "BUPQC"},
        {name: "ATPRODQC"},
        {name: "ORADEVDB1"}
	];
	
	$scope.currentDataSource = $scope.dataSources[0];
	
	$scope.selectDataSource = function() {
		$scope.busy = true;
		$http.put("api/table/database/"+$scope.currentDataSource.name)
			.success(function(response){
				$scope.getTables($scope.currentDataSource.name);
			})
			.error(function(response){
				$scope.busy = false;
			});
	}
	
	$scope.collapseAllTables = function() {
		for(var t=0; t<$scope.tables.length; t++) {
			var table = $scope.tables[t];
			table.show = false;
		}
	};
	$scope.expandSelectedTables = function(callback) {
		for(var t=0; t<$scope.tables.length; t++) {
			var table = $scope.tables[t];
			if(table.selected)
				$scope.expandTable(table, callback);
		}
	};
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
	};
	$scope.loadColumns = function(table, callback) {
		$http.get("api/table/"+$scope.currentDataSource.name+"/"+table.name+"/column")
		.success(function(columns){
			table.columns = columns;
			if(typeof callback === "function")
				setTimeout(callback, 200);
		});
	};
	
	$scope.selectTable = function(table) {
		// table.selected = true;
	};
	$scope.selectColumn = function(table, column) {
		// column.selected = true;
	};
	$scope.selectAllColumns = function(table) {
		// column.selected = true;
	};
	$scope.toggleAllTables = function() {
		$scope.allTablesExpanded = !$scope.allTablesExpanded;
		for(var t in $scope.tables) {
			$scope.toggleTable($scope.tables[t], $scope.allTablesExpanded);
		}
	};
	$scope.toggleTable = function(table, expand, callback) {
		if(expand === true) {
			$scope.expandTable(table);
		} else if(expand === false) {
			table.show = false;
		} else {
			if(table.show == true) {
				table.show = false;
			} else {
				$scope.expandTable(table, function(){
					table.show = true;
					table.loading = false;
				});
			}
		}
	};	

	$scope.exportToExcel = function() {
		var selected = $scope.parseSelected();
		$http.post("api/table/excel/"+$scope.currentDataSource.name, selected)
			.success(function(response){console.log(response);});
	};
	$scope.getTables = function(name) {
		$http.get("api/table/"+name)
		.success(function(tables){
			$http.get("http://localhost:9090/rest/table")
			.success(function(ignoredTables){
				for(var t in tables) {
					tables[t].ignored = false;
					for(var tt in ignoredTables) {
						if(tables[t].name === ignoredTables[tt].tableName &&  ignoredTables[tt].ignored === true)
						{
							tables[t].ignored = true;
							tables[t]._id = ignoredTables[tt]._id;
						}
					}
				}
				$scope.tables = tables;
				$scope.busy = false;
			});
		});
	};
	
	$scope.getTables($scope.currentDataSource.name);

	$scope.ignoreSelectedTables = function() {
		var ignore = { tableNames: [] };
		for(var t=0; t<$scope.tables.length; t++) {
			if($scope.tables[t].selected) {
				$scope.tables[t].ignored = true;
				$scope.tables[t].selected = false;
				ignore.tableNames.push($scope.tables[t].name);
			}
		}
		$http.put("http://localhost:9090/rest/table/ignore/true", ignore)
		.success(function(responseTable){
		});
	};
	$scope.unignoreSelectedTables = function() {
		var ignore = { tableNames: [] };
		for(var t=0; t<$scope.tables.length; t++) {
			if($scope.tables[t].selected) {
				$scope.tables[t].ignored = false;
				$scope.tables[t].selected = true;
				ignore.tableNames.push($scope.tables[t].name);
			}
		}
		$http.put("http://localhost:9090/rest/table/ignore/false", ignore)
		.success(function(responseTable){
		});
	};
	
//	*/
	/*
	 * search
	 */
	$http.get("http://localhost:9090/rest/search")
	.success(function(searches){
		$scope.searches = searches;
	});
	$scope.saveQuery = function() {
		var search = { tables: $scope.parseSelected(), name: $scope.searchName };
		$http.post("http://localhost:9090/rest/search", search)
		.success(function(searches){
			$scope.searches = searches;
		})
		.error(function(response){console.log(response);});
	};
	$scope.selectSearch = function(search) {
		$http.get("http://localhost:9090/rest/search/"+search._id)
		.success(function(search){
			$scope.updateUiFromSearch(search);
		})
		.error(function(response){console.log("unable to remove");});
	};
	$scope.deleteSearch = function(search) {
		$http.delete("http://localhost:9090/rest/search/"+search._id).success(function(searches){$scope.searches = searches;}).error(function(response){console.log("unable to remove");});
	};
	$scope.updateUiFromSearch = function(search) {
//		$http.post("api/table/excel", search.tables)
	//	.success(function(response){console.log(response);});

		
		// unselect all tables and columns
		$scope.collapseAllTables();
		$scope.expandTablesFromSearch(search);
	};
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
	};
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
									$(columnId).attr("checked", "checked");
									break;
								}
							}
						}
					}
				}
			}
		}
	};
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
	};
	$scope.updateSearch = function(search, event) {
		if(event.which === 13) {
			$http.put("http://localhost:9090/rest/search/"+search._id, search)
			.success(function(response){
				console.log(response);
			})
			.error(function(response){
				console.log(response);
			});
		}
	};
	$scope.selectAllTables = function() {
		for(var t in $scope.tables) {
			if(!$scope.tables[t].ignored)
				$scope.tables[t].selected = !$scope.allTablesSelected;
		}
	};
	$scope.selectAllIgnoredTables = function() {
		for(var t in $scope.tables) {
			if($scope.tables[t].ignored)
				$scope.tables[t].selected = !$scope.allIgnoredTablesSelected;
		}
	};
}

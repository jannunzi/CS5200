function ImagesController($scope, $http) {
	$scope.addImage = function() {
		var imageFileName = $scope.imageFile.name;
		console.log(imageFileName);
		var obj = {fileName: imageFileName};
		$http.post("http://localhost:9090/rest/image/"+imageFileName, obj)
		.success(function(images) {
			$scope.images = images;
		});
	};
	$scope.deleteImage = function(id) {
		$http.delete("http://localhost:9090/rest/image/"+id)
		.success(function(images) {
			$scope.images = images;
		});
	};
	
	$http.get("http://localhost:9090/rest/image")
	.success(function(images) {
		$scope.images = images;
	});
}

function ImageController($scope, $http, $routeParams) {
	$http.get("http://localhost:9090/rest/image/"+$routeParams.id)
	.success(function(image) {
		$scope.image = image;
		$scope.captions = image.captions;
	});
	
	$scope.$watchCollection("captions", function(newValue, oldValue){
		console.log(newValue);
		console.log(oldValue);
	});
	
	$scope.addCaption = function() {
		var newCaption = {
			title: "New Caption",
			top: 0, left: 0, width: 100, height: 100
		};
		console.log(newCaption);
		$http.post("http://localhost:9090/rest/image/"+$routeParams.id+"/caption", newCaption)
		.success(function(image){
			$scope.captions = image.captions;
		});
	};
	$scope.save = function() {
		$http.put("http://localhost:9090/rest/image/"+$routeParams.id+"/caption", $scope.captions)
		.success(function(image){
			$scope.image = image;
			$scope.captions = image.captions;
		});

	}
	$scope.updateCaption = function(newCaption) {
		console.log(newCaption);
		/*
		$http.put("http://localhost:9090/rest/image/"+$routeParams.id+"/caption/"+newCaption._id, newCaption)
		.success(function(image){
			$scope.image = image;
			$scope.captions = image.captions;
		});
		*/
	}
	$scope.deleteCaption = function(caption) {
		$http.delete("http://localhost:9090/rest/image/"+$routeParams.id+"/caption/"+caption._id)
		.success(function(captions){
			$scope.captions = captions;
		});
	};
	
	$scope.showCaptionEditDialog = function(caption)
	{
		;
	};

//	$scope.captions = [
//        {name: "Caption 1", top: 0, left: 0, text: "Cation 1", width: 100, height: 50},
//        {name: "Caption 2", top: 100, left: 100, text: "Cation 2", width: 100, height: 50}
//	];
};

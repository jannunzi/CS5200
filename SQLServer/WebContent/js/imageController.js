function ImagesController($scope, $http, $upload) {
	$scope.addImage = function() {
		var imageFileName = $scope.imageFile.name;
		console.log(imageFileName);
		var obj = {fileName: imageFileName};
		$http.post("http://10.8.143.140:9090/rest/image/"+imageFileName, obj)
		.success(function(images) {
			$scope.images = images;
		});
	};
	$scope.deleteImage = function(id) {
		$http.delete("http://10.8.143.140:9090/rest/image/"+id)
		.success(function(images) {
			$scope.images = images;
		});
	};
	
	$http.get("http://10.8.143.140:9090/rest/image")
	.success(function(images) {
		$scope.images = images;
	});
	
	$scope.onFileSelect = function($files) {
	    //$files: an array of files selected, each file has name, size, and type.
	    for (var i = 0; i < $files.length; i++) {
	      var file = $files[i];
	      $scope.upload = $upload.upload({
    	    url: 'http://10.8.143.140:9090/rest/image/upload/ewq', //upload.php script, node.js route, or servlet url
	        // method: 'POST' or 'PUT',
	        // headers: {'header-key': 'header-value'},
	        // withCredentials: true,
	        data: {myObj: $scope.myModelObj},
	        file: file, // or list of files: $files for html5 only
	        /* set the file formData name ('Content-Desposition'). Default is 'file' */
	        //fileFormDataName: myFile, //or a list of names for multiple files (html5).
	        /* customize how data is added to formData. See #40#issuecomment-28612000 for sample code */
	        //formDataAppender: function(formData, key, val){}
	      }).progress(function(evt) {
	        console.log('percent: ' + parseInt(100.0 * evt.loaded / evt.total));
	      }).success(function(data, status, headers, config) {
	        // file is uploaded successfully
	    	  console.log("data");
	    	  console.log(data);
				$scope.images = data;

	        console.log(data);
	      });
	      //.error(...)
	      //.then(success, error, progress); 
	      //.xhr(function(xhr){xhr.upload.addEventListener(...)})// access and attach any event listener to XMLHttpRequest.
	    }
    }
}

function ImageController($scope, $http, $routeParams, $modal, $log) {
	
	/*
	 * get and display initial list of images
	 */
	$http.get("http://10.8.143.140:9090/rest/image/"+$routeParams.id)
	.success(function(image) {
		$scope.image = image;
		$scope.captions = image.captions;
	});
	
	$scope.$watchCollection("captions", function(newValue, oldValue){
		console.log(newValue);
		console.log(oldValue);
	});

	/*
	 * Captions
	 */
	$scope.addCaption = function() {
		var newCaption = {
			title: "New Caption",
			top: 0, left: 0, width: 100, height: 100
		};
		console.log(newCaption);
		$http.post("http://10.8.143.140:9090/rest/image/"+$routeParams.id+"/caption", newCaption)
		.success(function(image){
			$scope.captions = image.captions;
		});
	};
	$scope.save = function() {
		$http.put("http://10.8.143.140:9090/rest/image/"+$routeParams.id+"/caption", $scope.captions)
		.success(function(image){
			$scope.image = image;
			$scope.captions = image.captions;
		});
	}
	$scope.updateCaption = function(newCaption) {
		console.log(newCaption);
	}
	$scope.deleteCaption = function(caption) {
		$http.delete("http://10.8.143.140:9090/rest/image/"+$routeParams.id+"/caption/"+caption._id)
		.success(function(captions){
			$scope.captions = captions;
		});
	};
	$scope.showCaptionEditDialog = function(caption)
	{
		console.log("showCaptionEditDialog");
	};
	
	
	/*
	 * Dialog
	 */
	
	$scope.items = ['item1', 'item2', 'item3'];
	
	  $scope.open = function (size) {

		    var modalInstance = $modal.open({
		      templateUrl: 'myModalContent.html',
		      controller: ModalInstanceCtrl,
		      size: size,
		      resolve: {
		        items: function () {
		          return $scope.items;
		        }
		      }
		    });

		    modalInstance.result.then(function (selectedItem) {
		      $scope.selected = selectedItem;
		    }, function () {
		      $log.info('Modal dismissed at: ' + new Date());
		    });
		  };
};

var ModalInstanceCtrl = function ($scope, $modalInstance, items) {

	  $scope.items = items;
	  $scope.selected = {
	    item: $scope.items[0]
	  };

	  $scope.ok = function () {
	    $modalInstance.close($scope.selected.item);
	  };

	  $scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	  };
	};

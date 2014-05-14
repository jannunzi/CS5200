var siterraApp = angular.module('siterraApp', ['ngRoute']);

siterraApp.config(['$routeProvider',
function($routeProvider) {
  $routeProvider.
    when('/tables', {
      templateUrl: 'templates/tables.html',
      controller: 'TableController'
    }).
    when('/images', {
        templateUrl: 'templates/images.html',
        controller: 'ImagesController'
    }).
    when('/image/:id', {
        templateUrl: 'templates/image.html',
        controller: 'ImageController'
    }).
    otherwise({
      redirectTo: '/tables'
    });
}]);

siterraApp.directive("fileread", [function () {
	 return {
	        scope: {
	            fileread: "="
	        },
	        link: function (scope, element, attributes) {
	            element.bind("change", function (changeEvent) {
	                scope.$apply(function () {
	                    scope.fileread = changeEvent.target.files[0];
	                    // or all selected files:
	                    // scope.fileread = changeEvent.target.files;
	                });
	            });
	        }
	    }
}]);

siterraApp.directive('myDraggable', ['$document', function($document) {
  return function(scope, element, attr) {
    var startX = 0, startY = 0, x = 0, y = 0;
    console.log(scope);
//    console.log(attr);
    element.draggable({
    	stop: function(event, ui)
    	{
    		scope.caption.top = ui.position.top;
    		scope.caption.left = ui.position.left;
    	}
    });
    element.resizable({
    	stop: function(event, ui)
    	{
    		scope.caption.width= ui.size.width;
    		scope.caption.height = ui.size.height;
    	}
    });
/*
    element.css({
     position: 'relative'
    });

    element.on('mousedown', function(event) {
      // Prevent default dragging of selected content
      event.preventDefault();
      startX = event.pageX - x;
      startY = event.pageY - y;
      $document.on('mousemove', mousemove);
      $document.on('mouseup', mouseup);
    });

    function mousemove(event) {
      y = event.pageY - startY;
      x = event.pageX - startX;
      element.css({
        top: y + 'px',
        left:  x + 'px'
      });
    }

    function mouseup() {
      $document.off('mousemove', mousemove);
      $document.off('mouseup', mouseup);
    }
    */
  };
}]);
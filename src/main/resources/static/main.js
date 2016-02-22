/**
 * Created by: leafchild
 * Date: 2/22/16
 * Time: 10:45
 * Keep it as simple as possible
 * One controller, one service
 */

(function () {
    var app = angular.module('users', ['ngResource']);

    app.controller('UsersController', ['$scope', 'userService', function ($scope, userService) {
        //User I will be using for editing
        $scope.currentUser = {};
        $scope.editing = false;

        $scope.populateUsers = function () {
            //Get data from DB
            userService.getUsers().query(
                function (response) {
                    //Assign it to existingUsers
                    $scope.existingUsers = response;
                },
                function (response) {
                    $scope.message = "Error: " + response.status + " " + response.statusText;
                });
        };

        $scope.addUser = function (user) {
            //TODO: Generate ID
            //user.id = $scope.existingUsers.length + 1;

            userService.getUsers().save(user).$promise.then(
                function (response) {
                    $scope.existingUsers.push(response);
                },
                function (response) {
                    $scope.message = "Error: " + response.status + " " + response.statusText;
                });

            //Clean up the form
            $scope.currentUser = {};
            $scope.userForm.$setPristine();
        };

        $scope.removeUser = function ($index) {
            var userD = $scope.existingUsers[$index];
            userService.getUser().delete({id: userD.id}, userD).$promise.then(
                function () {
                    //Assign it to existingUsers
                    $scope.existingUsers.splice($index, 1);
                },
                function (response) {
                    $scope.message = "Error: " + response.status + " " + response.statusText;
                });

        };

        $scope.saveUser = function (user) {
            var existing = $scope.getUserByID(user.id);

            if (existing != '') {
                userService.getUser().update({id: user.id}, user).$promise.then(
                    function () {
                        //Assign it to existingUsers
                        existing.name = user.name;
                        existing.phone = user.phone;
                    },
                    function (response) {
                        $scope.message = "Error: " + response.status + " " + response.statusText;
                    });

            }
            $scope.editing = false;
            $scope.currentUser = {};
            $scope.userForm.$setPristine();
        };

        $scope.removeAllUsers = function() {
          userService.getUsers().delete().$promise.then(
              function () {
                  //Assign it to existingUsers
                  $scope.existingUsers = {};
              },
              function (response) {
                  $scope.message = "Error: " + response.status + " " + response.statusText;
              });
        };

        $scope.getUserByID = function (id) {

            var found = '';
            $scope.existingUsers.forEach(function (user) {
                if (parseInt(user.id) === parseInt(id)) found = user;;
            });

            return found;
        };

        $scope.editUser = function ($index) {
            $scope.currentUser = $scope.existingUsers[$index];
            $scope.editing = true;
        };

        $scope.existingUsers = $scope.populateUsers();
    }]);


    app.service('userService', ['$resource', 'baseURL', function ($resource, baseURL) {
        //REST Calls

        this.getUsers = function () {
            //Get All Users
            //Delete All Users
            //Create User with POST
            return $resource(baseURL + 'users', null, {'save': {method: 'POST'}});
        };

        this.getUser = function () {
            //Get a specific user
            //Update User
            //Delete User
            return $resource(baseURL + 'user/:id', null, {'update': {method: 'PUT'}});
        };
    }]);


    app.constant("baseURL", "http://localhost:8080/");
})();

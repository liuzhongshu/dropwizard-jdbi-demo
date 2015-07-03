/*global angular*/
(function () {
    "use strict";

    var app = angular.module('myApp', ['ng-admin']);

    app.controller('main', function ($scope, $rootScope, $location) {
        $rootScope.$on('$stateChangeSuccess', function () {
            $scope.displayBanner = $location.$$path === '/dashboard';
        });
    });

    app.config(function (NgAdminConfigurationProvider, RestangularProvider) {
        var nga = NgAdminConfigurationProvider;

        var admin = nga.application('dropwizard jdbi demo') 
            .baseApiUrl('http://localhost:8080/api/'); 

        var post = nga.entity('posts');
        	

        // set the application entities
        admin.addEntity(post);


        post.dashboardView() 
            .fields([
            	nga.field('create_at', 'date'),
            	nga.field('name').isDetailLink(true)]); 

        post.listView()
            .title('All blogs')
            .perPage(10)
            .fields([
                nga.field('id'), 
                nga.field('name'), 
                nga.field('create_at', 'date'), 
                nga.field('view_count', 'number')
            ])
            .listActions(['show', 'edit', 'delete']);

        post.creationView()
            .fields([
                nga.field('name') 
                    .attributes({ placeholder: 'the blog title' }) 
                    .validation({ required: true, minlength: 1, maxlength: 100 }), 
                nga.field('content', 'wysiwyg'), 
                nga.field('create_at', 'date') 
            ]);

        post.editionView()
            .title('Edit blog "{{ entry.values.name }}"') 
            .actions(['list', 'show', 'delete']) 
            .fields([
                post.creationView().fields(), 
                nga.field('view_count', 'number')
            ]);

        post.showView() 
            .fields([
                nga.field('id'),
                post.editionView().fields()
            ]);

        admin.menu(nga.menu()
            .addChild(nga.menu(post).title("posts").icon('<span class="glyphicon glyphicon-file"></span>')) 
            .addChild(nga.menu().title('Other')
                .addChild(nga.menu().title('Stats').icon('').link('/stats'))
            )
        );

        nga.configure(admin);
    });


}());
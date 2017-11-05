module.exports = function(grunt) {

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        jshint: {
            src: [
                'datepicker/**/*.js'
            ],
            options: {
                curly: true,
                immed: true,
                newcap: true,
                noarg: true,
                sub: true,
                boss: true,
                eqnull: true
            }
        },

        karma: {
            unit: {
                options: {
                    files: [
                        'vendor/jquery/jquery-2.1.0.min.js', // jQuery is included for the purposes of easier DOM selection when testing directives.
                        'vendor/angular/angular.js',
                        'vendor/angular-mocks/angular-mocks.js',
                        'vendor/angular-ui-router/release/angular-ui-router.js',
                        'tmp/templates.js',
                        'datepicker/angularUtils.js',
                        'datepicker/filters/**/*.js',
                        'datepicker/filters/**/*.spec.js',
                        'datepicker/directives/**/*.js',
                        'datepicker/directives/**/*.spec.js',
                        'datepicker/services/**/*.js',
                        'datepicker/services/**/*.spec.js'
                    ],
                    frameworks: [ 'jasmine' ],
                    plugins: [ 'karma-jasmine', 'karma-firefox-launcher', 'karma-chrome-launcher', 'karma-phantomjs-launcher' ]

                },
                singleRun: true,
                port: 9877,
                browsers: [
                    'PhantomJS'
                ]
            }
        },

        watch: {
            jssrc: {
                files: [
                    'datepicker/**/*.js'
                ],
                tasks: [ 'default' ]
            }
        },

        html2js: {
            options: {
              // custom options, see below
            },
            main: {
              src: ['datepicker/**/*.tpl.html'],
              dest: 'tmp/templates.js'
            }
          },

        copy: {
            // used to copy certain of the utils to external repo directories
            dirPagination: {
                expand: true,
                flatten: true,
                src: [
                    'datepicker/directives/pagination/dirPagination.js',
                    'datepicker/directives/pagination/dirPagination.tpl.html'],
                dest: '../angularUtils-dist/angularUtils-pagination/'

            },
            uiBreadcrumbs: {
                expand: true,
                flatten: true,
                src: [
                    'datepicker/directives/uiBreadcrumbs/uiBreadcrumbs.js',
                    'datepicker/directives/uiBreadcrumbs/uiBreadcrumbs.tpl.html'],
                dest: '../angularUtils-dist/angularUtils-uiBreadcrumbs/'

            },
            dirDisqus: {
                expand: true,
                flatten: true,
                src: ['datepicker/directives/disqus/dirDisqus.js'],
                dest: '../angularUtils-dist/angularUtils-disqus/'
            }
        }
    });

    grunt.loadNpmTasks('grunt-karma');
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-html2js');
    grunt.loadNpmTasks('grunt-contrib-copy');

    grunt.registerTask('default', ['jshint', 'html2js', 'karma']);
    grunt.registerTask('publish', ['copy']);

};
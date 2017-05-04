## How to start
    ./gradlew clean build

This will produce javascript files into `scripts-generated` folder

    cd web && gulp serve
    
Will serve the app 

## Notes

#### Installing gulp and Yoman
    sudo npm install --global yo gulp-cli bower generator-webapp
    sudo npm install gulp -g
#### Init the project
    npm init
    npm install gulp --save-dev
#### Init and scaffolding webapp
    npm init
    npm install gulp --save-dev
    yo webapp
#### Preview and watch for changes
    gulp serve
#### Install frontend dependencies
    bower install --save <package>
#### Run the tests in the browser
    gulp serve:test
#### Build your webapp for production
    gulp 
#### Preview the production build
    gulp serve:dist

### Links
- https://github.com/yeoman/generator-webapp
- https://css-tricks.com/gulp-for-beginners/
- https://www.toptal.com/front-end/webpack-browserify-gulp-which-is-better
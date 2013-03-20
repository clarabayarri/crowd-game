# Crowdsourcing game based on Dyseggxia

This is an online game developed by [Clara Bayarri](http://www.clarabayarri.com) as part of her bachelor's thesis. This platform is connected to a [crowdsourcing platform](https://github.com/clarabayarri/crowd) for user data tracking and analysis. The game can be found during its development on [heroku](http://desolate-inlet-9447.herokuapp.com/game/).

## Running the application locally

First build with:

    $mvn clean package

Then run it with:

    $java -jar target/dependency/jetty-runner.jar target/*.war

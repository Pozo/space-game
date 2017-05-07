# space-game
## What is it?

An experimental space game written in Java and ~~pure javascript~~ (Kotlin!) using canvas element.

Currenly I'm porting javascript part so It's not working right now  ¯\_(ツ)_/¯

In the future I plan to use some kind of Rx library with websocket.

![space-game](https://github.com/Pozo/space-game/blob/master/demo.gif "space-game")
## Modules

 - core - game logic
 - backend - connection specific stuffs
 - frontend - ui and connection specific stuffs
 - assets from here http://www.kenney.nl/assets?s=space

## Installation
  
`git clone https://github.com/Pozo/space-game.git && cd space-game`

##### In order to make Set-Cookie works  
  
  `sudo sed -i '1i\127.0.0.1 localhost.com' /etc/hosts`
  
##### Backend

    cd backend
    ./gradlew jar
    java -jar build/libs/backend-1.0-SNAPSHOT.jar server config.yml

##### Frontend

    cd frontend
    ./gradlew stop
    ./gradlew run
    
##### Reload the frontend after a change 
There is a [hot module replacement](https://webpack.js.org/concepts/hot-module-replacement/) function but it's not working for me.

    ./gradlew stop && ./gradlew run

## Issues

Regarding [this suggestion( on the bottom )](https://github.com/Kotlin/kotlin-frontend-plugin) I should use `gradlew -t run` but I've got this exception

java.io.IOException: User limit of inotify watches reached

This did'nt solve my problem:

https://askubuntu.com/questions/770374/user-limit-of-inotify-watches-reached-on-ubuntu-16-04

So I'm using `./gradlew stop && ./gradlew run`

## Licensing

Please see LICENSE

## Contact

  Zoltan Polgar - pozo@gmx.com

  Please do not hesitate to contact me if you have any further questions.

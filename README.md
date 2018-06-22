# [SPACE SHMUP](https://golddaniel.itch.io/shmup) - Built With [LibGDX](https://libgdx.badlogicgames.com/)

A game I wrote using a codebase I built. The base is a common structure 
I have used with a few other projects.
  * [BLAST DEM BOTS](https://github.com/goldDaniel/BLAST_DEM_BOTS)
  * [UNNAMED PLATFORMER](https://github.com/goldDaniel/platformer_project)

# Things I did poorly but learned from
 
 * At the end of the project I really started feeling the constraints
    that I set for myself in regards to my architecture. Next project
    will have a rewrite of this main codebase, and there will be a more
    layered approach.
 * Originally I had planned to have a full level with different enemies,
    and upgrades to the player ship. After I while I realized how out of 
    scope that really was and focused on making the one fight.
    
 * UI is hard, I used a free skin from [here](https://github.com/czyzby/gdx-skins) and made it work
    but it really is not pretty. I would like to try and write my own UI system sometime.
    
 * Asset managment was easy to handle in this game, there isnt a whole lot. Because of 
    this I felt it was appropriate to load it all at the beginning. What I wasn't expecting
    was the super long load times on web due to downloading resources (libGDX has cross platform deployment)
    and there was 2 back to back loading screens as well! Next time i'll separate it out more.
  
# Things I feel I did well

* I love how self contained and simple my bullet spawners were.
    you give it some variables and it would do the behaviour that 
    was expected. Thanks to the object oriented nature, I could
    then take these self contained spawners and manipulate the inputs
    to make very good effects and patterns. I will use something similar
    for my next particle system.
* The animation logic for the player ended up being a very clean
    looking state machine that worked super smoothly and never had to 
    be fiddled with ever.
  

## Running - LibGDX gradle docs [here](https://github.com/libgdx/libgdx/wiki/Gradle-on-the-Commandline)

 * compile: ./gradlew desktop:run
            ./gradlew html:superDev
            
 * dist:    ./gradlew desktop:dist
            ./gradlew html:dist


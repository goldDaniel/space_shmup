# SPACE SHMUP - Built With [LibGDX](https://libgdx.badlogicgames.com/)

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
  

## Getting Started - LibGDX gradle docs [here](https://github.com/libgdx/libgdx/wiki/Gradle-on-the-Commandline)

 * compile: ./gradlew desktop:run
            ./gradlew html:superDev
            
 * dist:    ./gradlew desktop:dist
            ./gradlew html:dist
     

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc

WAR GAME
--------

This is a toy project which simulates a battle between two armies. The game is written in java, simply textual no graphic user interface is provided.

### Description

The war is between two armies, each one consists of N units. When the war starts the soldiers are dropped from a plane on the battle area. The soldiers are randomly spread.

Rules are easy:
- if a soldier has around him more enemies then companion then it dies
- if a solder has no people around him he gets mad and suicide 
- if a soldier is close to a bomb he dies

It wins the battle the army which has more alive soldiers in the end. 

Usage
-----

```
mvn clean install
java -jar target/war-game.jar
```

Tests
-----

Just a single test has been provided since this is only a toy project.

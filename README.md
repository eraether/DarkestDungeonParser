# DarkestDungeonParser
Programatically Modify .darkest dungeon files.

## What this does

Exposes an API for reading and writing darkest dungeon files.  This allows you to make global, sweeping changes to the game without editing one file at a time.

## How to get started

    git clone https://github.com/eraether/DarkestDungeonParser.git

[Install Eclipse](https://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/mars2).  Get the Windows 64 bit version.

[Install the JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).  Get the Windows 64 bit version.

Import the project into eclipse.  (File -> Import -> Navigate to where you cloned this repo.)

Open [Runner.java](src/com/dd/Runner.java)

Change ["E:/steam/steamapps/common/DarkestDungeon/"](src/com/dd/Runner.java#L24) to the location of your darkest dungeon installation.

Run the project (Run->Run)

## How to write new mods

Look at [MultiplyAllDamageAndHealth.java](src/com/dd/MultiplyAllDamageAndHealth.java)

Your mod will need to extend GameplayChange.java and override the [makeGameplayChanges(Map&lt;File,DarkestFile&gt;)](src/com/dd/GameplayChange.java#L11) method.

Lastly, you will need to add your gameplay change to Runner.java's public void main method.  

For reference, here is how MultiplyAllDamageAndHealth.java was added:

    gameplayChanges.add(new MultiplyAllDamageAndHealth());

That's it!  After your run Runner.java your changes will be in the game!

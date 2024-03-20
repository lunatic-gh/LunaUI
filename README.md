# A Fancy menu mod, because there aren't enough already.

<h2>How to configure:</h2>

<h3>Color-Theme</h3>

- This mod replaces vanilla's "Monochrome Logo" Option with a generic "Dark/Light" Theme option, as it feels more representable of what it actually does.
- Just check your "Accessibility Options" for the option.

<h3>Wallpapers</h3>

- Create a resourcepack (or use an existing one) and create the structure `assets/lunaui/`
- Inside this folder, create a file called `config.jsonc`. it's important that it's a `jsonc` file, not `json`. This is the config that is used by the mod.
- In the file, add the following contents as a base:
```json
{
  "screen-textures-named": {
    // If you are not a developer, this section can be entirely ignored by you.
  },
  "screen-textures-intermediary": {

  },
}  
```
- Those 2 sections should contain a list of key-value pairs, containing both the class of a screen you want a wallpaper to appear in (ex. Title Screen) and the assets-path to a directory, relative from `assets/lunaui/`
- This directory should contain all the png-files to use for the wallpaper. They will play in alphabetical order (0-9-a-z).
- If you want, you can also specify a delay argument next to the directory path, being the delay between frames, measured in milliseconds.
- Here's an example that would give the title screen a wallpaper, consisting of all the files inside the directory `assets/lunaui/textures/wallpaper/title_screen`, with a delay of 100ms between the frames:
```json
{
  "screen-textures-named": {
    // If you are not a developer, this section can be entirely ignored by you.
  },
  "screen-textures-intermediary": {
    "net.minecraft.class_442": "textures/wallpaper/title_screen,100"
  },
}  
```
- Then just (re)load your resourcepack, and the wallpaper should appear, animated or not depending on the amount of images you use.

-----------------------------------------------------------------------------------------------------------------------------------------------
- The reason there are 2 sections (named & intermediary) is simply because the development-environment for fabric-mods uses different code-mappings, therefore the class names are different than on the actual client.
- To make it easier for me to test stuff, i just made 2 sections. depending on your environment (client or Development-IDE) the corresponding section is automatically being used.
- If you are not a developer (working on the actual code for this mod), the "named" section is entirely useless to you. ignore it, or even remove it from your config, it won't be read anyway.

<h4>What does the "class" of a screen mean?</h4>

This is the absolute path to the screen's code-class, that contains the init, render and other important code for this specific screen.
You can find it out on 2 ways:
- Open the screen you want to know the class of, and press Ctrl+Shift+P. This will send debug-information (title & class-name) of the current screen to the console.
- Go to [this page](https://maven.fabricmc.net/docs/yarn-24w11a+build.2/index.html). on the top-right, search for the screen you need. the names are usually self explainatory, for ex. "TitleScreen" for the title screen.
  - on the screen's page, search for the "intermediary" or "named" name. This is the class name used by whatever environment you are in (read above). Just make sure to replace the name's "/" characters with "." characters for the path.

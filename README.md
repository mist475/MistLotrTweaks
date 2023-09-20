# Mist475's Lotr Tweaks

This mod adds some QoL changes to the [Lotr mod: Legacy](https://www.curseforge.com/minecraft/mc-mods/the-lord-of-the-rings-mod-legacy) by Mevans.

## Requirements

- [UniMixins](https://github.com/LegacyModdingMC/UniMixins)
- [Lotr mod: Legacy](https://www.curseforge.com/minecraft/mc-mods/the-lord-of-the-rings-mod-legacy) or one of it's sub-mods
- If [Hodgepodge](https://modrinth.com/mod/hodgepodge) is used, the mod requires version 2.2.23 or later

## What it does

QoL changes for the lotr mod.
I've spent a lot of time playing with the lotr mod, it's the only content-adding mod I've consistently played with over the years.
Yet there's always minor annoyances, things that could be more convenient, incompatibilities with other mods, etc.
With this mod I'm trying to fix some of these.

Every feature the mod adds is controlled by the config. Making it possible to enable/disable specific parts of the mod.

If Mevans wants to incorporate any of my changes into lotr itself/ implement a similar feature he is more than welcome to do so.

Feature list:

- Make lotr compatible with java 12+ via [lwjgl3ify](https://github.com/GTNewHorizons/lwjgl3ify). Note: I added the same patches to [Hodgepodge](https://modrinth.com/mod/hodgepodge) 2.2.23+, I disable the patches here if it's detected
- Fellowship permission icons are now shown in the main fellowship screen as well as the per-fellowship screen (see image)
![2023-06-04_18 15 24](https://github.com/mist475/MistLotrTweaks/assets/70655895/819cb86e-5ff2-4fa0-895a-d598298b05a3)
- When reforging, the item tooltip is now shown permanently to make reforging items less tedious ![2023-06-07_18 54 01](https://github.com/mist475/MistLotrTweaks/assets/70655895/cd0edbb9-da0d-4102-9b6b-45aab64c53ef)
- Configurable redstone ore generation in Middle Earth with 3 modes; off, only in red mountains, everywhere. Also configurable are rarity, vein size and the minimum/maximum y level
- Fix heavy lag when both lotr & [weather2](https://legacy.curseforge.com/minecraft/mc-mods/weather-storms-tornadoes) are installed. I have not tested the mod beyond this so the chance of more things breaking is quite present.
- Add [DMod](https://www.curseforge.com/minecraft/mc-mods/dmod) foxes to middle earth biomes where foxes spawn in renewed. In the northern forests and the northlands there is a chance that snow foxes will spawn instead of regular foxes.

## Attribution

The general structure of this mod was adapted from [BugTorch](https://github.com/jss2a98aj/BugTorch).
The rendering code for the improved fellowship screen was adapted from lotr itself.

## License
Copyright 2023 Mist475

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
       http://www.apache.org/licenses/LICENSE-2.0

# BetterScoreboard

Originally written only for scoreboards, BetterScoreboard is a display plugin written for Spigot and all of its forks 
that supports 1.20.3 and above. This plugin currently includes support for both boss bars and scoreboards, offering a 
simple experience that should be functional enough for most servers.

This project makes heavy use of [PacketEvents](https://github.com/retrooper/packetevents),
[sharkbyte-bossbar](https://github.com/amnoah/sharkbyte-bossbar), and
[sharkbyte-scoreboard](https://github.com/amnoah/sharkbyte-scoreboard).

## General Features
- Unlimited character support on all lines.
- Fully packet based.
- Scoreboard updating is run fully async.
- Non-flicker updates.
- 10 built-in placeholders.
- Conditional placeholders.
- PlaceholderAPI support.
- Per-animation update speed.
- Option to randomly select animation steps.

## Boss Bar Features
- Control the color of boss bars.
- Control the health of boss bars.
- Control the division of boss bars.
- Ability to have multiple boss bars.
- Ability to activate certain boss bars via certain triggers.
- Per-world boss bars.
- Permission-based boss bars.
- Combinations of per-world and permission-based boss bars.
- Boss bar weight, so only the highest will activate.

## Scoreboard Features
- Conditional lines (will show/hide based on criteria).
- Ability align text to the left or right.
- Ability to have multiple scoreboards.
- Ability to activate certain scoreboards via certain triggers.
- Per-world scoreboards.
- Permission-based scoreboards.
- Combinations of per-world and permission-based scoreboards.
- Scoreboard weight, so only the highest will activate.
- Ability align text to the left or right.

## Limitations
- At this time the plugin does not support hex codes.

# Documentation

For information on conditions in BetterScoreboard, please check the [Conditions wiki](https://github.com/amnoah/BetterScoreboard/wiki/Conditions).

For information on how to configure BetterScoreboard, please check the [Config wiki](https://github.com/amnoah/BetterScoreboard/wiki/Config).

For information on how to use placeholders in BetterScoreboard, please check the [Placeholders wiki](https://github.com/amnoah/BetterScoreboard/wiki/Placeholders).

For information on how to create boss bars in BetterScoreboard, please check the [Boss Bars Wiki](https://github.com/amnoah/BetterScoreboard/wiki/Boss-Bar).

For information on how to create scoreboards in BetterScoreboard, please check the [Scoreboards wiki](https://github.com/amnoah/BetterScoreboard/wiki/Scoreboards).

For information on how to use triggers in BetterScoreboard, please check the [Triggers wiki](https://github.com/amnoah/BetterScoreboard/wiki/Triggers).

# Dependencies

## Required
- [PacketEvents](https://modrinth.com/plugin/packetevents) - Required to send packets to players.

## Optional
- [BetterReload](https://modrinth.com/plugin/betterreload) - Required to listen to the reload event.
- [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) - Required to use PlaceholderAPI placeholders.

# Support

For general support, please join my [Discord server](https://discord.gg/ey9uTg3hcy).

For issues with the project, please open an issue in the issues tab.
# Reborn Client

An independent Fabric client mod for Minecraft **1.21.11**, with a dark custom click GUI and original client-side PvP utilities.

## Build

Requires Java 21 and Gradle.

```bash
gradle build
```

The remapped Fabric JAR is produced in `build/libs/`.

## Controls

- **Right Shift** — open/close the Reborn click GUI.
- Click a module row to toggle it.

## Implemented modules

- **Fullbright** — raises gamma while enabled and restores the previous value when disabled.
- **Auto Sprint** — sprints while moving forward.
- **Aim Assist** — smoothly turns toward the nearest player within 4.5 blocks.
- **TriggerBot** — attacks a player under the crosshair when the attack cooldown is ready.
- **Auto Totem** — moves a totem from the inventory into the offhand when needed.
- **Crystal Breaker** — attacks an end crystal under the crosshair when the attack cooldown is ready.

These are client-side utilities. They are not guaranteed to work on every server, and server rules may prohibit automated combat. Use only where permitted. The new PvP modules have been added to source and are awaiting CI compilation and in-game testing.

## Third-party credits

Apexx Client is acknowledged as inspiration for the general concept of a feature-rich PvP client. Reborn Client does not contain Apexx source code, binaries, branding, or assets. See [CREDITS.md](CREDITS.md).

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

## Implemented modules in this build

- **Fullbright** — raises gamma while enabled and restores the previous value when disabled.
- **Auto Sprint** — sprints while moving forward.
- **Aim Assist** — smoothly turns toward the nearest player within 4.5 blocks.
- **TriggerBot** — attacks a player under the crosshair when the attack cooldown is ready.
- **Auto Totem** — moves a totem from the inventory into the offhand when needed.
- **Crystal Breaker** — attacks an end crystals under the crosshair when the attack cooldown is ready.
- **Auto Jump Reset** — jumps when grounded shortly after taking damage.
- **Auto Shield Break** — selects an axe and attacks a blocking player under the crosshair.
- **Auto Tool** — selects a tiered tool that is effective against the block under the crosshair.

These modules are original implementations, not copied Apexx code. They are not guaranteed to work on every server; automated combat may violate server rules. The current build is still being expanded and requires in-game testing.

## Planned PvP feature groups

Crystal placement/detonation, anchor automation, mace combo helpers, pearl utilities, bow aiming, hit timing, web placement, cart setups, movement helpers, ESP and configurable module settings are not yet implemented. They are not shown as working modules.

## Third-party credits

Apexx Client is acknowledged as inspiration for the general concept of a feature-rich PvP client. Reborn Client does not contain Apexx source code, binaries, branding, or assets. See [CREDITS.md](CREDITS.md).

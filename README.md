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
- **Aim Assist** — smoothly tracks the nearest player within 4.5 blocks.
- **TriggerBot** — attacks a player under the crosshair when the attack cooldown is ready.
- **Auto Totem** — moves a totem into the offhand when needed.
- **Crystal Breaker** — attacks an end crystal under the crosshair when the cooldown is ready.
- **Auto Jump Reset** — jumps when grounded shortly after taking damage.
- **Auto Shield Break** — selects an axe and attacks a blocking player under the crosshair.
- **Auto Tool** — selects the hotbar item with the best destroy speed for the targeted block.
- **Bow Aim Assist** — assists aim while drawing a bow.
- **Auto Mace** — selects a mace while aiming at a player and restores the previous slot afterward.
- **Auto W-Tap** — briefly resets sprint after a detected hit.
- **Fall Pearl** — uses an ender pearl already held in the main or offhand during a dangerous fall.

These are original implementations, not copied Apexx code. Automated combat may violate server rules and can be blocked by server-side checks. Test in single-player or on servers where these utilities are permitted.

## Not yet implemented

Full AutoCrystal placement/charge logic, AutoAnchor placement/charging, mace fall-combo automation, hit-select timing controls, AutoWeb, pearl trajectory prediction, TNT-minecart/cart PvP automation, ESP/tracers, and configurable per-module settings remain unfinished. They are deliberately not shown as working modules.

## Third-party credits

Apexx Client is acknowledged as inspiration for the general concept of a feature-rich PvP client. Reborn Client does not contain Apexx source code, binaries, branding, or assets. See [CREDITS.md](CREDITS.md).

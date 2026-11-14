# Reborn Client

An original Fabric client mod for Minecraft **1.21.11**, with a dark click GUI and modular client-side utilities.

## Build

Requires Java 21 and Gradle.

```bash
gradle build
```

The remapped Fabric JAR is produced in `build/libs/`.

## Controls

- **Right Shift** — open/close the Reborn click GUI.
- Click a supported module row to toggle it.

## Current implementation status

Implemented in this initial foundation:
- Dark custom ClickGUI
- Fullbright
- Auto Sprint
- Module registry and tick lifecycle

The other listed PvP modules are visible scaffolds only; they are not implemented or active yet. They will be added in later updates. Reborn Client uses original code and branding and does not include Apexx Client's proprietary files or assets.

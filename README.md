# Relays
Minecraft Mod to automate anything

https://www.curseforge.com/minecraft/mc-mods/relays

Based around a "Relay Box" block, that will be the connection between computer or redstone and an entity or block in the world.

The final idea is that it will be possible to have different "Interface Cards" to put into the "Relay Box", together with "Source Cards" connected to an entity or block in the world.
The "Interface Card" will then provide an API for the given source, which could be accesed throug computer (OpenComputer or ComputerCraft), and to be setup to be controlled via redstone.

## Examples of ideas of use cases
(This is just ideas for explaination)

### Entity Control
- Interface Card: Entity Control
- Source Card: Entity Link (UUID of an entity)

The API could have methods to control the entity simular to the way you control a Turtle (from CC) or Robot (from OC).

### Remote Redstone
- Interface Card: Remote Redstone
- Source Card: Block Location (XYZ cordination)

The API could have methods to set or get the restone power of the block at the specified location

### Train Control
- Interface Card: Locomotive Control
- Source Card: Locomotive (UUID of a locomotive. Could be from Immersive Railroading)

The API could have methods to control the locomitive, set throttle level, brake level, sound the horn etc.

This could even have features like cruise control, or have that feature in a seperate _interface card_

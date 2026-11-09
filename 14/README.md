# Computer Builder – Builder design pattern

## Structure (package `computerbuilder`)

| Role in the pattern | Class | Purpose |
|---|---|---|
| Product | `Computer` | Processor, RAM (GB), hard drive, graphics card, OS + accessories. `toString()` prints a formatted configuration. |
| Builder | `ComputerBuilder` | Interface: `reset`, `buildProcessor`, `buildRAM`, `buildHardDrive`, `buildGraphicsCard`, `buildOperatingSystem`, `buildAccessories`, `getComputer`. |
| Concrete builder | `GamingComputerBuilder` | Gaming parts (Ryzen CPUs, RTX GPUs, NVMe storage, gaming peripherals). |
| Concrete builder | `OfficeComputerBuilder` | Office parts (Core i3/i5/i7, integrated graphics, Windows 11 Pro, monitors/webcam). |
| Concrete builder | `CustomComputerBuilder` | Uses parts the user chose (fluent `withProcessor(...)`, `withRam(...)`, …). |
| Director | `ComputerDirector` | `constructComputer()` runs all steps; `constructBaseComputer()` builds the computer without accessories. |
| Client / demo | `Main` | Builds several computers with the same director and prints them, then offers the interactive builder. |

### Additional challenges
* **Component grades:** `Grade` enum (`BUDGET`, `STANDARD`, `PREMIUM`) passed to the gaming/office builders changes every part.
* **Accessories:** each builder has a `buildAccessories()` step; the custom builder lets the user pick any accessories from `ComponentCatalog`.
* **User interface:** `ComputerBuilderUI` is a console menu – choose a gaming/office preset and grade, or pick every component yourself, and the final configuration is shown. Invalid input is rejected and asked again.

## Running
* Demo + interactive builder: run `computerbuilder.Main`
* Interactive builder only: run `computerbuilder.ComputerBuilderUI`
* Tests (JUnit 5): `mvn test`, or run `ComputerBuilderTest` in the IDE

Written for Java 11 or newer.

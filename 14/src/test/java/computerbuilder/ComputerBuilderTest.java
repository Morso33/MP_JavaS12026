package computerbuilder;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ComputerBuilderTest {

    @Test
    void gamingBuilderAssemblesGamingComputer() {
        Computer pc = new ComputerDirector(new GamingComputerBuilder(Grade.PREMIUM)).constructComputer();

        assertEquals("Premium Gaming Computer", pc.getType());
        assertEquals("AMD Ryzen 9 9950X3D", pc.getProcessor());
        assertEquals(64, pc.getRamGb());
        assertEquals("NVIDIA GeForce RTX 5090", pc.getGraphicsCard());
        assertEquals("Windows 11 Pro", pc.getOperatingSystem());
        assertTrue(pc.getAccessories().contains("Wireless gaming headset"));
    }

    @Test
    void officeBuilderAssemblesOfficeComputer() {
        Computer pc = new ComputerDirector(new OfficeComputerBuilder(Grade.BUDGET)).constructComputer();

        assertEquals("Intel Core i3-14100", pc.getProcessor());
        assertEquals(8, pc.getRamGb());
        assertEquals("256 GB SSD", pc.getHardDrive());
        assertTrue(pc.getGraphicsCard().contains("integrated"));
        assertEquals("Windows 11 Pro", pc.getOperatingSystem());
    }

    @Test
    void baseComputerHasNoAccessories() {
        Computer pc = new ComputerDirector(new GamingComputerBuilder()).constructBaseComputer();

        assertNotNull(pc.getProcessor());
        assertTrue(pc.getAccessories().isEmpty());
    }

    @Test
    void directorReturnsNewComputerEachTime() {
        ComputerDirector director = new ComputerDirector(new OfficeComputerBuilder());
        Computer first = director.constructComputer();
        Computer second = director.constructComputer();

        assertNotSame(first, second);
        assertEquals(first.getAccessories(), second.getAccessories());
    }

    @Test
    void customBuilderUsesChosenParts() {
        CustomComputerBuilder builder = new CustomComputerBuilder()
                .withProcessor("AMD Ryzen 5 7600")
                .withRam(128)
                .withHardDrive("2 TB NVMe SSD")
                .withGraphicsCard("AMD Radeon RX 9070 XT")
                .withOperatingSystem("Ubuntu 24.04 LTS")
                .withAccessory("HD webcam");
        Computer pc = new ComputerDirector(builder).constructComputer();

        assertEquals("AMD Ryzen 5 7600", pc.getProcessor());
        assertEquals(128, pc.getRamGb());
        assertEquals("2 TB NVMe SSD", pc.getHardDrive());
        assertEquals("AMD Radeon RX 9070 XT", pc.getGraphicsCard());
        assertEquals("Ubuntu 24.04 LTS", pc.getOperatingSystem());
        assertEquals(1, pc.getAccessories().size());
    }

    @Test
    void uiBuildsCustomComputerFromUserInput() {
        // custom -> CPU 5, RAM 3 (32 GB), drive 4, GPU 6, OS 2, accessories 2 and 4, then exit
        String input = "3\n5\n3\n4\n6\n2\n2, 4\n0\n";
        String output = runUi(input);

        assertTrue(output.contains("AMD Ryzen 7 9800X3D"));
        assertTrue(output.contains("32 GB"));
        assertTrue(output.contains("2 TB NVMe SSD"));
        assertTrue(output.contains("NVIDIA GeForce RTX 5090"));
        assertTrue(output.contains("Windows 11 Pro"));
        assertTrue(output.contains("Wireless gaming headset"));
        assertTrue(output.contains("Goodbye!"));
    }

    @Test
    void uiRejectsInvalidInputAndBuildsPreset() {
        // invalid menu choice, then office preset, premium grade, no accessories, then exit
        String input = "9\nabc\n2\n3\nn\n0\n";
        String output = runUi(input);

        assertTrue(output.contains("Please enter a number between 0 and 3."));
        assertTrue(output.contains("Premium Office Computer"));
        assertTrue(output.contains("Accessories       none"));
    }

    @Test
    void uiStopsCleanlyWhenInputEnds() {
        String output = runUi("1\n");
        assertTrue(output.contains("No more input"));
    }

    private static String runUi(String input) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        new ComputerBuilderUI(scanner, new PrintStream(bytes, true)).run();
        return bytes.toString();
    }
}

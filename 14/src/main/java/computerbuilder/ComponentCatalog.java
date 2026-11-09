package computerbuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** The parts a user can choose from when designing a custom computer. */
public final class ComponentCatalog {

    public static final List<String> PROCESSORS = Collections.unmodifiableList(Arrays.asList(
            "Intel Core i3-14100",
            "Intel Core i5-14500",
            "Intel Core i7-14700",
            "AMD Ryzen 5 7600",
            "AMD Ryzen 7 9800X3D",
            "AMD Ryzen 9 9950X3D"));

    public static final List<Integer> RAM_SIZES_GB = Collections.unmodifiableList(Arrays.asList(
            8, 16, 32, 64, 128));

    public static final List<String> HARD_DRIVES = Collections.unmodifiableList(Arrays.asList(
            "256 GB SSD",
            "512 GB SSD",
            "1 TB NVMe SSD",
            "2 TB NVMe SSD",
            "4 TB NVMe SSD (PCIe 5.0)",
            "1 TB SSD + 4 TB HDD"));

    public static final List<String> GRAPHICS_CARDS = Collections.unmodifiableList(Arrays.asList(
            "Intel UHD Graphics 730 (integrated)",
            "NVIDIA T400 (multi-monitor)",
            "NVIDIA GeForce RTX 5060",
            "AMD Radeon RX 9070 XT",
            "NVIDIA GeForce RTX 5070 Ti",
            "NVIDIA GeForce RTX 5090"));

    public static final List<String> OPERATING_SYSTEMS = Collections.unmodifiableList(Arrays.asList(
            "Windows 11 Home",
            "Windows 11 Pro",
            "Ubuntu 24.04 LTS",
            "No operating system"));

    public static final List<String> ACCESSORIES = Collections.unmodifiableList(Arrays.asList(
            "Keyboard and mouse set",
            "RGB mechanical keyboard",
            "Gaming mouse",
            "Wireless gaming headset",
            "24\" 1080p monitor",
            "27\" 1440p 240 Hz OLED monitor",
            "HD webcam",
            "USB-C docking station",
            "Wi-Fi 7 card",
            "Uninterruptible power supply (UPS)"));

    private ComponentCatalog() {
    }
}

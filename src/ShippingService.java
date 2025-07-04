import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for handling shipping operations.
 */
public class ShippingService {
    private static final double BASE_SHIPPING_FEE = 20.0;
    private static final double SHIPPING_RATE_PER_KG = 10.0;

    public static double calculateShippingFee(List<Shippable> items) {
        if (items == null || items.isEmpty()) {
            return 0.0;
        }

        double totalWeight = items.stream()
                .mapToDouble(Shippable::getWeight)
                .sum();

        return BASE_SHIPPING_FEE + (totalWeight * SHIPPING_RATE_PER_KG);
    }

    public static void processShipment(List<Shippable> items) {
        if (items == null || items.isEmpty()) {
            return;
        }

        System.out.println("** Shipment notice **");

        items.stream()
                .collect(Collectors.groupingBy(
                        Shippable::getName,
                        Collectors.counting()))
                .forEach((name, count) -> {
                    double itemWeight = items.stream()
                            .filter(item -> item.getName().equals(name))
                            .findFirst()
                            .map(Shippable::getWeight)
                            .orElse(0.0);
                    System.out.printf("%dx %s %.0fg%n", count, name, itemWeight * 1000);
                });

        double totalWeight = items.stream().mapToDouble(Shippable::getWeight).sum();
        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }
}
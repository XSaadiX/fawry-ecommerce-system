// Simple shipping service with proper formatting
class ShippingService {

    static double calculateShipping(Shippable[] shippableItems, int count) {
        if (count == 0) return 0;

        // Simple shipping calculation
        return 30; // Flat rate
    }

    static void processShipment(Shippable[] shippableItems, int count) {
        if (count == 0) return;

        System.out.println("** Shipment notice **");

        // Count items by name and display
        String[] itemNames = new String[count];
        int[] itemCounts = new int[count];
        double[] itemWeights = new double[count];
        int uniqueItems = 0;

        for (int i = 0; i < count; i++) {
            String currentName = shippableItems[i].getName();
            boolean found = false;

            // Check if we already have this item
            for (int j = 0; j < uniqueItems; j++) {
                if (itemNames[j].equals(currentName)) {
                    itemCounts[j]++;
                    found = true;
                    break;
                }
            }

            // If not found, add new item
            if (!found) {
                itemNames[uniqueItems] = currentName;
                itemCounts[uniqueItems] = 1;
                itemWeights[uniqueItems] = shippableItems[i].getWeight();
                uniqueItems++;
            }
        }

        // Print grouped items
        double totalWeight = 0;
        for (int i = 0; i < uniqueItems; i++) {
            double weightInGrams = itemWeights[i] * 1000;
            System.out.println(itemCounts[i] + "x " + itemNames[i] + " " + (int)weightInGrams + "g");
            totalWeight = totalWeight + (itemWeights[i] * itemCounts[i]);
        }

        System.out.println("Total package weight " + totalWeight + "kg");
    }
}
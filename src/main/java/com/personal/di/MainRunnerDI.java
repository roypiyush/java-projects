package com.personal.di;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;

public class MainRunnerDI {

    public static void main(String... args) {
        ServiceLocator locator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
        String text = "Hello World!!!";
        Printer simplePrinter = locator.getService(Printer.class, "simple-console");
        if (simplePrinter != null) {
            simplePrinter.print(text);
        }
        Printer formattedPrinter = locator.getService(Printer.class, "formatted-console");
        if (formattedPrinter != null) {
            formattedPrinter.print(text);
        }
    }
}

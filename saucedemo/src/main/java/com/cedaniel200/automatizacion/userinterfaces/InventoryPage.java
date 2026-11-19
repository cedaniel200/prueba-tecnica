package com.cedaniel200.automatizacion.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class InventoryPage {

    private InventoryPage() {
    }

    public static final Target INVENTORY_LIST = Target.the("lista de productos")
            .locatedBy("[data-test='inventory-list']");
}

package ch.zhaw.prog2.shoppinglist_product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Testen der Methode getTotalCosts aus der Klasse Shopping
 *
 * @author bles
 */
class ShoppingListTest {
    private ShoppingList shoppingList;

    private static final double MILK_PRICE = 2.15;
    private static final int MILK_QUANTITY = 3;
    private static final double SALAD_PRICE = 2.40;
    private static final int SALAD_QUANTITY = 2;

    @Mock
    ProductMigros milk;

    @Mock
    ProductMigros salad;

    @BeforeEach
    void setUp() {
        shoppingList = new ShoppingList();
        MockitoAnnotations.initMocks(this);

        when(milk.getQuantity()).thenReturn(MILK_QUANTITY);
        when(milk.getPrice()).thenReturn(MILK_PRICE);

        when(salad.getQuantity()).thenReturn(SALAD_QUANTITY);
        when(salad.getPrice()).thenReturn(SALAD_PRICE);
    }

    @Test
    void testGetTotalCostsWithProductMock() {
        addMilkAndSaladToShoppingList();
        double expectedCosts = (MILK_PRICE * MILK_QUANTITY) + (SALAD_PRICE * SALAD_QUANTITY);
        double actualCosts = shoppingList.getTotalCosts();

        assertEquals(expectedCosts, actualCosts);
    }

    private void addMilkAndSaladToShoppingList() {
        shoppingList.addProduct(milk);
        shoppingList.addProduct(salad);
    }

}

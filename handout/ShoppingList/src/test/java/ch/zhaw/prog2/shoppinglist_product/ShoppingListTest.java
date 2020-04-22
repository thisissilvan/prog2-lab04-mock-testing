package ch.zhaw.prog2.shoppinglist_product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Testen der Methode getTotalCosts aus der Klasse Shopping
 *
 * @author bles
 */
class ShoppingListTest {
    private ShoppingList shoppingList;

    private final double milkPrice = 2.15;
    private final int milkQuantity = 3;
    private final double saladPrice = 2.40;
    private final int saladQuantity = 2;

    @Mock
    ProductMigros milk;
    ProductMigros salad;

    @BeforeEach
    void setUp() {
        shoppingList = new ShoppingList();
        MockitoAnnotations.initMocks(this);

        milk = mock(ProductMigros.class);
        when(milk.getQuantity()).thenReturn(3);
        when(milk.getPrice()).thenReturn(2.15);

        salad = mock(ProductMigros.class);
        when(salad.getQuantity()).thenReturn(2);
        when(salad.getPrice()).thenReturn(2.40);
    }

    @Test
    void testGetTotalCostsWithProductMock() {
        addMilkAndSaladToShoppingList();
        double expectedCosts = (milkPrice * milkQuantity) + (saladPrice * saladQuantity);
        double actualCosts = shoppingList.getTotalCosts();

        assertEquals(expectedCosts, actualCosts);
    }

    private void addMilkAndSaladToShoppingList() {
        shoppingList.addProduct(milk);
        shoppingList.addProduct(salad);
    }

}

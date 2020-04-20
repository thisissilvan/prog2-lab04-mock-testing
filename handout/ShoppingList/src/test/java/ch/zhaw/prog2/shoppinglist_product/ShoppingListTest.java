package ch.zhaw.prog2.shoppinglist_product;

import ch.zhaw.prog2.shoppinglist.Product;
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

    @Mock
    ProductMigros milk;
    ProductMigros salad;

    @BeforeEach
    void setUp() {
        shoppingList = new ShoppingList();
        MockitoAnnotations.initMocks(this);

        milk = mock(ProductMigros.class);
        when(milk.getName()).thenReturn("Milk");
        when(milk.getProductId()).thenReturn("MilkId");
        when(milk.getQuantity()).thenReturn(3);
        when(milk.getPrice()).thenReturn(2.15);

        salad = mock(ProductMigros.class);
        when(salad.getName()).thenReturn("Salad");
        when(salad.getProductId()).thenReturn("SaladId");
        when(salad.getQuantity()).thenReturn(2);
        when(salad.getPrice()).thenReturn(2.40);
    }

    @Test
    void testGetTotalCostsWithProductMock() {
        shoppingList.addProduct(milk);
        shoppingList.addProduct(salad);

        double expectedCosts = milk.getPrice() * milk.getQuantity();
        expectedCosts += salad.getPrice() * salad.getQuantity();

        assertEquals(expectedCosts, shoppingList.getTotalCosts());
    }

}

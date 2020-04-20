package ch.zhaw.prog2.shoppinglist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShoppingListTest {
	private ShoppingList shoppingList;
    private final Product milk = new Product("MilkId", "Milk", 3);
    private final Product salad = new Product("SaladId", "Salad", 2);

    @Mock
    PriceService priceService;

    @BeforeEach
    void setUp() throws Exception {
        shoppingList = new ShoppingList();
        priceService = mock(PriceService.class);
        when(priceService.getPrice(milk)).thenReturn(2.15);
        when(priceService.getPrice(salad)).thenReturn(2.40);
    }

    @Test
    void testGetProducts() {
        List<Product> expectedList = new ArrayList<>();
        expectedList.add(milk);
        expectedList.add(salad);
        assertNotNull(expectedList);
        assertEquals(2, expectedList.size());

        addMilkAndSaladToShoppingList();
        assertNotNull(shoppingList);

        assertEquals(expectedList, shoppingList.getProducts());
    }

    @Test
    void testAddProduct() {
        addMilkAndSaladToShoppingList();
        assertNotNull(shoppingList);
        assertEquals(2, shoppingList.getProducts().size());
    }

    @Test
    void testGetTotalCosts() {
        addMilkAndSaladToShoppingList();
        double expectedCosts = priceService.getPrice(milk) * 3;
        expectedCosts += priceService.getPrice(salad) * 2;

        shoppingList.setPriceService(priceService);

        assertEquals(expectedCosts, shoppingList.getTotalCosts());

    }

    private void addMilkAndSaladToShoppingList() {
        shoppingList.addProduct(milk);
        shoppingList.addProduct(salad);
    }

}

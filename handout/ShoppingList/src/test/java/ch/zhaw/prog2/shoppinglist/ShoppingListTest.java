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

    private final double milkPrice = 2.15;
    private final int milkQuantity = 3;
    private final String milkString = "Milk";

    private final double saladPrice = 2.40;
    private final int saladQuantity = 2;
    private final String saladString = "Salad";

    @Mock
    PriceService priceService;

    @BeforeEach
    void setUp() {
        shoppingList = new ShoppingList();
        priceService = mock(PriceService.class);
        shoppingList.setPriceService(priceService);
        when(priceService.getPrice(milk)).thenReturn(2.15);
        when(priceService.getPrice(salad)).thenReturn(2.40);
    }

    @Test
    void testGetProducts() {
        List<Product> expectedList = new ArrayList<>();
        List<Product> actualList = shoppingList.getProducts();
        expectedList.add(milk);
        expectedList.add(salad);
        addMilkAndSaladToShoppingList();

        assertNotNull(expectedList);
        assertNotNull(shoppingList);
        assertEquals(expectedList, actualList);
    }

    @Test
    void testGetProductString() {
        addMilkAndSaladToShoppingList();
        String actualMilkString = shoppingList.getProducts().get(0).getName();
        String actualSaladString = shoppingList.getProducts().get(1).getName();

        assertEquals(milkString, actualMilkString);
        assertEquals(saladString, actualSaladString);
    }

    @Test
    void testAddProduct() {
        addMilkAndSaladToShoppingList();
        int actualProductsOnList = shoppingList.getProducts().size();
        int actualProductQuantityOnList = milk.getQuantity() + salad.getQuantity();

        assertNotNull(shoppingList);
        assertEquals(2,actualProductsOnList);
        assertEquals(5,actualProductQuantityOnList);
    }

    @Test
    void testGetTotalCosts() {
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

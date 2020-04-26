package ch.zhaw.prog2.shoppinglist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShoppingListTest {
	private ShoppingList shoppingList;
    private final Product milk = new Product("MilkId", "Milk", 3);
    private final Product salad = new Product("SaladId", "Salad", 2);

    private static final double MILK_PRICE = 2.15;
    private static final int MILK_QUANTITY = 3;
    private static final String MILK_STRING = "Milk";

    private static final double SALAD_PRICE = 2.40;
    private static final int SALAD_QUANTITY = 2;
    private static final String SALAD_STRING = "Salad";

    @Mock
    PriceService priceService;

    @BeforeEach
    void setUp() {
        shoppingList = new ShoppingList();
        MockitoAnnotations.initMocks(this);
        shoppingList.setPriceService(priceService);
        when(priceService.getPrice(milk)).thenReturn(MILK_PRICE);
        when(priceService.getPrice(salad)).thenReturn(SALAD_PRICE);
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

        assertEquals(MILK_STRING, actualMilkString);
        assertEquals(SALAD_STRING, actualSaladString);
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
        double expectedCosts = (MILK_PRICE * MILK_QUANTITY) + (SALAD_PRICE * SALAD_QUANTITY);
        double actualCosts = shoppingList.getTotalCosts();

        assertEquals(expectedCosts, actualCosts);
        verify(priceService).getPrice(milk);
        verify(priceService).getPrice(salad);
    }

    private void addMilkAndSaladToShoppingList() {
        shoppingList.addProduct(milk);
        shoppingList.addProduct(salad);
    }

}

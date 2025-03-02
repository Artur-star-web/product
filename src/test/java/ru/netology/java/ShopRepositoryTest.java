package ru.netology.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShopRepositoryTest {

    @Test
    public void shouldRemoveExistingProduct() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Apple", 100);
        Product product2 = new Product(2, "Banana", 50);
        repo.add(product1);
        repo.add(product2);

        repo.removeById(1);

        Product[] expected = {product2};
        assertArrayEquals(expected, repo.findAll());
    }

    @Test
    public void shouldThrowExceptionWhenRemovingNonExistingProduct() {
        ShopRepository repo = new ShopRepository();
        repo.add(new Product(1, "Apple", 100));

        Exception exception = assertThrows(NotFoundException.class, () -> repo.removeById(2));

        assertEquals("Element with id: 2 not found", exception.getMessage());
    }

    @Test
    public void shouldAddProductSuccessfully() {
        ShopRepository repo = new ShopRepository();
        Product product = new Product(1, "Apple", 100);

        repo.add(product);

        Product[] expected = {product};
        assertArrayEquals(expected, repo.findAll());
    }

    @Test
    public void shouldThrowExceptionWhenAddingDuplicateId() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Apple", 100);
        Product product2 = new Product(1, "Banana", 200);

        repo.add(product1);

        Exception exception = assertThrows(AlreadyExistsException.class, () -> repo.add(product2));

        assertEquals("Element with id: 1 already exists", exception.getMessage());
    }
}

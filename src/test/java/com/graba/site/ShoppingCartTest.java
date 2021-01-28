/*
 * package com.graba.site;
 * 
 * import static org.junit.Assert.assertTrue; import static
 * org.junit.jupiter.api.Assertions.assertEquals;
 * 
 * import java.util.List;
 * 
 * import org.junit.Ignore; import org.junit.Test; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
 * import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
 * import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
 * import org.springframework.test.annotation.Rollback;
 * 
 * import com.graba.entity.CartItem; import com.graba.entity.Customer; import
 * com.graba.entity.Product; import com.graba.repository.CartItemRepository;
 * 
 * @Ignore
 * 
 * @DataJpaTest
 * 
 * @AutoConfigureTestDatabase
 * 
 * @Rollback(false) public class ShoppingCartTest {
 * 
 * 
 * @Autowired private CartItemRepository cartRepo;
 * 
 * @Autowired TestEntityManager entityManager;
 * 
 * @Test public void testAddoneCartItem() { Product product =
 * entityManager.find(Product.class, 1); Customer customer =
 * entityManager.find(Customer.class, 1);
 * 
 * CartItem newItem = new CartItem(); newItem.setCustomerId(1l);
 * newItem.setProductId(1l); newItem.setQuantity(10);
 * 
 * CartItem savedCartItem = cartRepo.save(newItem);
 * 
 * assertTrue(savedCartItem.getId() > 0);
 * 
 * }
 * 
 * @Test public void testGetCartItemsByCustomer() { Customer customer = new
 * Customer(); //customer.setId(1);
 * 
 * List<CartItem> cartItems = cartRepo.findByCustomerId(customer.getId());
 * 
 * assertEquals(2, cartItems.size());
 * 
 * } }
 */
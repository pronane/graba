/*
 * package com.graba.hazelcast;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.ApplicationArguments; import
 * org.springframework.boot.ApplicationRunner; import
 * org.springframework.stereotype.Component;
 * 
 * import com.graba.repository.ProductRepository; import
 * com.graba.repository.UserRepository;
 * 
 * @Component public class DataLoader implements ApplicationRunner {
 * 
 * @Autowired private UserRepository userRepository;
 * 
 * @Autowired private ProductRepository productRepository;
 * 
 * @Autowired public DataLoader(UserRepository userRepository, ProductRepository
 * productRepository) { this.userRepository = userRepository;
 * this.productRepository = productRepository; }
 * 
 * public void run(ApplicationArguments args) { //userRepository.save(new
 * User(1l, "ola", "1111")); //userRepository.save(new User(2l, "uber",
 * "2222")); //userRepository.save(new User(3l, "lyft", "3333"));
 * 
 * 
 * 
 * Product product1 = new Product(); product1.setId(1l);
 * product1.setName("Biryani"); product1.setShortDescription("nice dish");
 * 
 * Product product2 = new Product(); product2.setName("Korma");
 * product2.setShortDescription("nice mild dish"); product2.setId(2l);
 * List<Product> products = productRepository.saveAll(Sets.newHashSet(product1,
 * product2));
 * 
 * System.out.println(products);
 * 
 * } }
 */
/*
 * package com.example.springboot;
 * 
 * import static org.assertj.core.api.Assertions.*;
 * 
 * import java.net.URL;
 * 
 * import org.junit.Ignore; import org.junit.jupiter.api.BeforeEach; import
 * org.junit.jupiter.api.Test; import org.junit.runner.RunWith; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
 * import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
 * import org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.boot.test.web.client.TestRestTemplate; import
 * org.springframework.boot.web.server.LocalServerPort; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.test.annotation.Rollback; import
 * org.springframework.test.context.junit4.SpringRunner;
 * 
 * import com.graba.Application;
 * 
 * @Ignore
 * 
 * @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
 * classes = Application.class) public class HelloControllerIT {
 * 
 * @LocalServerPort private int port;
 * 
 * private URL base;
 * 
 * @Autowired private TestRestTemplate template;
 * 
 * @BeforeEach public void setUp() throws Exception { this.base = new
 * URL("http://localhost:" + port + "/"); }
 * 
 * @Test public void getHello() throws Exception { ResponseEntity<String>
 * response = template.getForEntity(base.toString(), String.class);
 * assertThat(response.getBody()).isEqualTo("Greetings from Spring Boot!"); } }
 */
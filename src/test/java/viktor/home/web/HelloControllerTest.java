package viktor.home.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class HelloControllerTest {
    HelloController sut = new HelloController();

    @Test
    void get_hello_should_return_hello() {

      String result = sut.getHello();

      assertThat(result).isEqualTo("Hello");
    }
}
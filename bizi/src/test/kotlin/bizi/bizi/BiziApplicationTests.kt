package bizi.bizi

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.EnabledIf

@SpringBootTest
@EnabledIf(expression = "#{false}", reason = "Desabilitar todos os testes")
class BiziApplicationTests {

	@Test
	fun contextLoads() {
	}

}

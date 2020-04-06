package br.com.cavallaro.enderecoService;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class EnderecoServiceApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(EnderecoServiceApplication.class);

	@InjectMocks
	EnderecoServiceApplication service;

	String cep;
	int estadoId;

    @BeforeEach
    void initContas() {
        this.cep = "05577902";
        this.estadoId = 35;
    }
    
	@Test
    @DisplayName("Retorna o getEndereco com sucesso")
    void validExecuteGetEndereco() {
        // given

        // when
        Object obj = service.getEndereco(this.cep);

        // then
        assertThat(obj, instanceOf(LinkedHashMap.class));
        LOGGER.info("\nRetorna o getEndereco com sucesso:\n"+obj+"\n");
    }
	
	@Test
    @DisplayName("Retorna o getEstados com sucesso")
    void validExecuteGetEstados() {
        // given

        // when
        Object obj = service.getEstados();

        // then
        assertThat(obj, instanceOf(ArrayList.class));
        LOGGER.info("\nRetorna o getEstados com sucesso:");
        for (Object obj1 : (ArrayList) obj) {
        	LOGGER.info(""+obj1);
		} 
    }
	
	@Test
    @DisplayName("Retorna o getMunicipios com sucesso")
    void validExecuteGetMunicipios() {
        // given

        // when
        Object obj = service.getMunicipios(this.estadoId);

        // then
        assertThat(obj, instanceOf(ArrayList.class));
        LOGGER.info("\nRetorna o getMunicipios com sucesso:");
        for (Object obj1 : (ArrayList) obj) {
        	LOGGER.info(""+obj1);
		}     
    }
}

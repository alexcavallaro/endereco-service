package br.com.cavallaro.enderecoService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class EnderecoServiceApplication {

    private static final String SAO_PAULO = "São Paulo";
    private static final String RIO = "Rio de Janeiro";
    
	
    
	public static void main(String[] args) {
		SpringApplication.run(EnderecoServiceApplication.class, args);
	}
	
	@GetMapping("/endereco/{cep}")
	public Object getEndereco(@PathVariable String cep) {
	    return (new RestTemplate()).getForObject("https://viacep.com.br/ws/"+cep+"/json", Object.class);
	}

	@GetMapping("/estados")
	public Object getEstados() {
		ArrayList<LinkedHashMap<String, String>> estados = (new RestTemplate()).getForObject("https://servicodados.ibge.gov.br/api/v1/localidades/estados/", ArrayList.class);
		
		ArrayList<LinkedHashMap<String, String>> clone = (ArrayList<LinkedHashMap<String, String>>) estados.clone();
		ArrayList<LinkedHashMap> estadosOrdenados = new ArrayList<LinkedHashMap>(){{add(null);add(null);}};
		for (LinkedHashMap<String, String> linkedHashMap : clone) {
			if (linkedHashMap.get("nome").equals(SAO_PAULO)) {
				estadosOrdenados.set(0, linkedHashMap);
				estados.remove(linkedHashMap);
			} else if (linkedHashMap.get("nome").equals(RIO)) {
				estadosOrdenados.set(1, linkedHashMap);
				estados.remove(linkedHashMap);
			}
		}
		
		estados.sort(Comparador);
		estadosOrdenados.addAll(estados);
		
		return estadosOrdenados;
	}
	
	public static Comparator<LinkedHashMap<String, String>> Comparador = new Comparator<LinkedHashMap<String, String>>() {

		public int compare(LinkedHashMap<String, String> s1, LinkedHashMap<String, String> s2) {
		   String name1 = s1.get("nome");
		   String name2 = s2.get("nome");

		   return name1.compareTo(name2);
	    }
	};
	//@GetMapping(path = "/endereco/{cep}", produces = "application/json")
	
	@GetMapping("/municipios/{estadoId}")
	public Object getMunicipios(@PathVariable int estadoId) {
	    return (new RestTemplate()).getForObject("https://servicodados.ibge.gov.br/api/v1/localidades/estados/"+estadoId+"/municipios", Object.class);
	}

}

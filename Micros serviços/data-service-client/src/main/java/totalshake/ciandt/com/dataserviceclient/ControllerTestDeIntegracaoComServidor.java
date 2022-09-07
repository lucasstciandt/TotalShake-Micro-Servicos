package totalshake.ciandt.com.dataserviceclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTestDeIntegracaoComServidor {

    @Value("${test.config}")
    private String config;

    @GetMapping("config")
    public String testandoConfigCompartilhada(){
        return config;
    }
}

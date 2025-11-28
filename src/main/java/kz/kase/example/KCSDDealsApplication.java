package kz.kase.example;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import jakarta.ws.rs.core.Application;

//@formatter:off
@OpenAPIDefinition(
   info = @Info(
      title="KCSD Deals API",
      description = "Программные интерфейсы KASE для передачи информации о внебиржевых сделках в виде REST-сервиса.",
      version = "2.2.0",
      contact = @Contact(
         name = "KCSD Deals API Support",
         url = "https://kase.kz/",
         email = "v.pyankov@kase.kz"
      )
   ),
   servers = {
      @Server(url = "https://irisapi.kase.kz/kcsd/rest/", description = "TEST"),
      @Server(url = "http://localhost:8080/", description = "LOCAL")
   }
)
//@formatter:on
public class KCSDDealsApplication extends Application {
}

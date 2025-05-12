package kz.kase.example;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import jakarta.ws.rs.core.Application;

//@formatter:off
@OpenAPIDefinition(
    info = @Info(
        title="KCSD Deals API",
        description = "Программные интерфейсы KCSD для передачи информации о внебиржевых сделках в виде REST-сервиса.",
        version = "1.0.0",
        contact = @Contact(
            name = "KCSD Deals API Support",
            url = "https://kcsd.kz/",
            email = "support@kcsd.kz")
        )
)
//@formatter:on
public class KCSDDealsApplication extends Application {
}

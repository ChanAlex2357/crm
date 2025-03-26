package site.easy.to.build.crm.service;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

@Service
public class FakeDataService {

    private final Faker faker;

    public FakeDataService() {
        this.faker = new Faker();
    }

    public String generateFakeName() {
        return faker.name().fullName();
    }

    public String generateFakeEmail() {
        return faker.internet().emailAddress();
    }

    public String generateFakeAddress() {
        return faker.address().fullAddress();
    }

    // Ajoutez d'autres méthodes pour générer des données factices selon vos besoins
}

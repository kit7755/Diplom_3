package praktikum.jsons.generators;

import com.github.javafaker.Faker;
import praktikum.jsons.CreateUserRequestJson;

public class UserJsonCreator {

    Faker faker = new Faker();

    public CreateUserRequestJson random() {
        return new CreateUserRequestJson(
                faker.internet().emailAddress(),
                faker.internet().password(),
                faker.name().username()
        );
    }
}

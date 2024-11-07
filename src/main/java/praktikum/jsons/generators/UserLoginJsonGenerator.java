package praktikum.jsons.generators;

import praktikum.jsons.CreateUserRequestJson;
import praktikum.jsons.UserRequestJson;

public class UserLoginJsonGenerator {

    public UserRequestJson from(CreateUserRequestJson user) {
        return new UserRequestJson(
                user.getEmail(),
                user.getPassword()
        );
    }
}
package UsersLib;

public class AdminBuilder extends UserBuilder {
    @Override
    public User buildPart() {
        return new Admin(_login, _password);
    }
}

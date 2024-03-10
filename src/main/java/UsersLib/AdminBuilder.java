package UsersLib;

public class AdminBuilder extends UserBuilder {
    @Override
    public Admin buildPart() {
        return new Admin(_login, _password);
    }
}

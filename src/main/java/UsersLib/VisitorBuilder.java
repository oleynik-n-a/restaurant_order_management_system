package UsersLib;

public class VisitorBuilder extends UserBuilder {
    @Override
    public Visitor buildPart() {
        return new Visitor(_login, _password);
    }
}

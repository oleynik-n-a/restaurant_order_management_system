package UsersLib;

import BuilderLib.IBuilder;

public abstract class UserBuilder implements IBuilder<User> {
    protected String _login;
    protected String _password;

    public void setLogin(String login) {
        _login = login;
    }

    public void setPassword(String password) {
        _password = password;
    }

    public abstract User buildPart();
}

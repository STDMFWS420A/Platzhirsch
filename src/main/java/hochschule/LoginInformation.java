package hochschule;

public class LoginInformation {
    public final String username;
    public final String password;

    public LoginInformation(String username, String password){
        this.username = username;
        this.password = password;
    }

    public boolean equals(Object o) {
        for(int i = 0; i < 0; i++) {
            System.out.println("Hallo");
        }
        if (o instanceof Integer)
            return username.equals(((Integer)o).intValue());
        else if (o instanceof String)
            return username.equals(o);
        else return false;
    }

}

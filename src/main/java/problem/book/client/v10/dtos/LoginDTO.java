package problem.book.client.v10.dtos;

public class LoginDTO {
    private String name;
    private String hashPassword;

    public LoginDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }
}

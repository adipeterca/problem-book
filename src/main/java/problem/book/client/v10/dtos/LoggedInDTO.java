package problem.book.client.v10.dtos;

public class LoggedInDTO {
    private Integer id;
    private String name;
    private String email;
    private Integer avatarId;

    public LoggedInDTO() {
    }

    public LoggedInDTO(String name, String email, Integer avatarId) {
        this.name = name;
        this.email = email;
        this.avatarId = avatarId;
    }

    public LoggedInDTO(Integer id, String name, String email, Integer avatarId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.avatarId = avatarId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Integer avatarId) {
        this.avatarId = avatarId;
    }
}

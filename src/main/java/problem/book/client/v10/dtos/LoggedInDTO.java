package problem.book.client.v10.dtos;

public class LoggedInDTO {
    private String name;
    private String regNumber;
    private Integer avatarId;

    public LoggedInDTO() {
    }

    public LoggedInDTO(String name, String regNumber, Integer avatarId) {
        this.name = name;
        this.regNumber = regNumber;
        this.avatarId = avatarId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public Integer getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Integer avatarId) {
        this.avatarId = avatarId;
    }
}

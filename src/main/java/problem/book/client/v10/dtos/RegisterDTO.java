package problem.book.client.v10.dtos;

/**
 * Class that represent information received upon registering.
 * Work for both Student and Teacher registration.
 */
public class RegisterDTO {
    private String name;
    // This can either be a Registration Number (for a student) or an Email (for a teacher)
    private String specialField;
    private String hashPassword;
    private Integer avatarId;

    public RegisterDTO() {
    }

    public RegisterDTO(String name, String specialField, String hashPassword, Integer avatarId) {
        this.name = name;
        this.specialField = specialField;
        this.hashPassword = hashPassword;
        this.avatarId = avatarId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialField() {
        return specialField;
    }

    public void getSpecialField(String registrationNumber) {
        this.specialField = registrationNumber;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public Integer getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Integer avatarId) {
        this.avatarId = avatarId;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", specialField='" + specialField + '\'' +
                ", hashPassword='" + hashPassword + '\'' +
                ", avatarId=" + avatarId +
                '}';
    }
}

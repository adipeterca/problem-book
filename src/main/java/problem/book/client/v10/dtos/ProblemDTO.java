package problem.book.client.v10.dtos;

public class ProblemDTO {

    private Integer id;

    private Integer teacherId;

    private String teacherEmail;

    private String hint1;

    private String hint2;

    private String content;

    public ProblemDTO() {
    }

    public ProblemDTO(Integer teacherId, String teacherEmail, String hint1, String hint2, String content) {
        this.teacherId = teacherId;
        this.teacherEmail = teacherEmail;
        this.hint1 = hint1;
        this.hint2 = hint2;
        this.content = content;
    }

    public ProblemDTO(Integer id, Integer teacherId, String teacherEmail, String hint1, String hint2, String content) {
        this.id = id;
        this.teacherId = teacherId;
        this.teacherEmail = teacherEmail;
        this.hint1 = hint1;
        this.hint2 = hint2;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getHint1() {
        return hint1;
    }

    public void setHint1(String hint1) {
        this.hint1 = hint1;
    }

    public String getHint2() {
        return hint2;
    }

    public void setHint2(String hint2) {
        this.hint2 = hint2;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

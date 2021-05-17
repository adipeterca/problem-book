package problem.book.client.v10.dtos;

public class ProblemDTO {

    private Integer teacherId;

    private String hint1;

    private String hint2;

    private String content;

    public ProblemDTO() {
    }

    public ProblemDTO(Integer teacherId, String hint1, String hint2, String content) {
        this.teacherId = teacherId;
        this.hint1 = hint1;
        this.hint2 = hint2;
        this.content = content;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
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

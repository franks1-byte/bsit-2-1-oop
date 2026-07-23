public class Student {
    private String studentId;
    private String fullName;
    private String program;
    private int yearLevel;

    // Constructor: initialize all fields with 'this'
    public Student(String studentId, String fullName,
                   String program, int yearLevel) {
        this.studentId = studentId;
        this.fullName  = fullName;
        this.program   = program;
        this.yearLevel = yearLevel;
    }


    // Getters (one shown - add the rest)
    public String getStudentId() { return studentId; }
    // TODO: getFullName(), getProgram(), getYearLevel()

    public String getFullName() {
        return fullName;

    }

    public String getProgram() {
        return program;
    }

    public int getYearLevel() {
        return yearLevel;
    }



    public String describe() {
        return studentId + " | " + fullName + " | "
                + program + " | Year " + yearLevel;
    }

}

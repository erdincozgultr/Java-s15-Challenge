package Person;

public enum MemberType {
    STUDENT(7, "A student of the university."),
    FACULTY(10, "A faculty member of the university."),
    STANDARD_MEMBER(5, "A regular library member from the public.");

    private final int limit;
    private final String description;

    MemberType(int limit, String description) {
        this.limit = limit;
        this.description = description;
    }

    public int getLimit() {
        return limit;
    }

    public String getDescription() {
        return description;
    }
}

package entity;

public enum TaskType {
    CODE(1, "Code"),
    TEST(2, "Test"),
    DESIGN(3, "Design"),
    REVIEW(4, "Review");

    private final int id;
    private final String name;

    private TaskType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static TaskType getById(int id) {
        for (TaskType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }
}

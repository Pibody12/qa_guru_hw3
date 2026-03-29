package tests.testData;

public enum UserName {
    Russian("Иван", "Иванов"),
    English("Morris", "Hoffman"),
    Japan("江藤", "颯真");

    public final String firstName;
    public final String lastName;

    UserName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

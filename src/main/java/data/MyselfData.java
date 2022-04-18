package data;

public enum MyselfData {

    NAME("Тест"),
    NAMELATIN("Test"),
    LNAME("Тестовый"),
    LNAMELATIN("Testoviy"),
    BLOGNAME("TestUiAuto"),
    BITHDATE("19.05.1990"),
    TELEGA("@testtelega"),
    COMPANY("APPLE"),
    POSITION("Java QA Auto");

    private String param;

    MyselfData(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return param;
    }

}

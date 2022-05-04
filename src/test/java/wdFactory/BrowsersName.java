package wdFactory;

public enum BrowsersName {

    CHROME("chrome"),
    OPERA("opera");

    private String param;

    BrowsersName(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return param;
    }
}

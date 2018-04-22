public enum Time {
    NEXTMORNING(" следующее утро "),
    ALLDAY(" весь день "),
    ALLEVENING(" весь вечер ");

    private String name;

    Time(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
}



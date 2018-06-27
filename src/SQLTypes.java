public enum SQLTypes {

    INT("integer"),
    BOOLEAN("boolean"),
    STRING("text"),
    SHORT("smallint"),
    LONG("bigint"),
    BYTE("smallint"),
    FLOAT("real"),
    DOUBLE("double precision"),
    CHAR("char[1]");

    private String name;

    SQLTypes(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
}

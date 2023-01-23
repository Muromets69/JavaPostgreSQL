package DataBase;

public enum Fields {

    VARCHAR("varchar"),
    FLOAT("float"),
    MEDIUMINT("mediumint"),
    BIGINT("bigint"),
    DATE("date"),
    DATETIME("datetime"),
    TIME("time"),
    SMALLINT("smallint"),
    INTEGER("int");
    public final String field;
    Fields(String s){
        this.field = s;
    }
}

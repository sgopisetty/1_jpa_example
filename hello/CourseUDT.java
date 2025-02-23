package hello;

import java.sql.SQLData;
import java.sql.SQLOutput;
import java.sql.SQLInput;
import java.sql.SQLException;

public class CourseUDT implements SQLData {
    private int id;
    private String code;
    private String title;

    public CourseUDT() {}

    public CourseUDT(int id, String code, String title) {
        this.id = id;
        this.code = code;
        this.title = title;
    }

    @Override
    public String getSQLTypeName() throws SQLException {
        return "CourseUDT";
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        this.id = stream.readInt();
        this.code = stream.readString();
        this.title = stream.readString();
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeInt(id);
        stream.writeString(code);
        stream.writeString(title);
    }

    // Getters and setters
}

package fr.polytech.marechal.libs.database.query.builders.enums;

/**
 * @author Robin
 * @date 15/06/2017
 */
public enum Functions
{
    COUNT("COUNT"),
    AVG("AVG"),
    MIN("MIN"),
    MAX("MAX"),
    SUM("SUM")
    ;

    private String value;

    Functions(String value)
    {
        this.value = value;
    }

    @Override
    public String toString ()
    {
        return value;
    }
}

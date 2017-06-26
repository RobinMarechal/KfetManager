package fr.polytech.marechal.libs.database.query.builders.enums;

/**
 * @author Robin
 * @date 14/06/2017
 */
public enum OrderBy
{
    ASC("ASC"),
    DESC("DESC"),
    ;

    private String value;

    OrderBy (String value)
    {
        this.value = value;
    }

    @Override
    public String toString ()
    {
        return value;
    }
}

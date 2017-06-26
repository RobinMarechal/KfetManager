package fr.polytech.marechal.libs.api;

/**
 * @author Robin
 * @date 14/06/2017
 */
public enum OrderBy
{
    ASC,
    DESC,;

    @Override
    public String toString ()
    {
        return name();
    }
}

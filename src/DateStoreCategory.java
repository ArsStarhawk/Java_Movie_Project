/**
 * File Name: DateStoreCategory.java
 * Coder: Evan Somers, Sion Young, James Kidd, James Scully
 * Date: 8/1/2020
 * Description: A wrapper class to wrap up dates, category and store data for generate report panel
 */

public class DateStoreCategory
{
    public String from;
    public String to;
    public String store;
    public String category;

    public DateStoreCategory(String from, String to, String store, String category)
    {
        this.from = from;
        this.to = to;
        this.store = store;
        this.category = category;
    }
}

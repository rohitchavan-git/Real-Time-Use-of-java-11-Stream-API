package src.util;

public class DashboardFilter {

    private final String searchColumnName;
    private final String searchValue;


    public DashboardFilter(String searchColumnName, String searchValue) {
        this.searchColumnName = searchColumnName;
        this.searchValue = searchValue;
    }

    public String getSearchColumnName() {
        return searchColumnName;
    }

    public String getSearchValue() {
        return searchValue;
    }
}

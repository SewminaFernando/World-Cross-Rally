package code.programmingcw_test1;

/**
 * The CSVHandler class provides a common interface for loading and saving data from CSV files.
 * Subclasses of CSVHandler can be created to handle specific types of data.
 */
public abstract class CSVHandler {
    protected String fileName;

    /**
     * Creates a new CSVHandler object with the specified file name.
     * @param fileName the name of the CSV file to load or save data from
     */
    public CSVHandler(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Loads data from the CSV file and stores it in an Array List.
     * This method should be implemented by subclasses to handle specific types of data.
     */
    public abstract void loadData();

    /**
     * Saves data to the CSV file from an Array List.
     * This method should be implemented by subclasses to handle specific types of data.
     */
    public abstract void saveData();
}

package ser.jint.persistence;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Razorback on 20/07/2015.
 */
public class CsvPersistence {
    public static final String SEPARATOR = ";";
    public static final String LINE_SEPARATOR = "\n";
    public static final String FILE_NAME_ITEM = "persistenceItems.csv";
    public static final String FILE_NAME_ORDER = "persistenceOrders.csv";

    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;

    public CsvPersistence() {
    }

    public void persistObjects(List<? extends Persistable> toSave, String fileName) throws IOException {
        this.bufferedWriter = new BufferedWriter(new FileWriter((new File(fileName))));
        Iterator<? extends Persistable> iterator = toSave.iterator();
        while (iterator.hasNext()) {
            this.bufferedWriter.write(iterator.next().persistenceString());
        }

        this.bufferedWriter.close();
    }

    public List<? extends Persistable> recreateObjects(String fileName) throws IOException {
        List<? extends Persistable> objectList = new LinkedList<Persistable>();
        this.bufferedReader = new BufferedReader(new FileReader(new File(fileName)));

        String out = "Start";

        while (out != null) {
            out = this.bufferedReader.readLine();
            System.out.println("Output: " + out);
        }

        this.bufferedReader.close();

        return objectList;
    }
}

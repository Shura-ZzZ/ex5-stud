import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadConf {
    private static File conf = new File("src/conf.txt");
    public static String getPath()
    {
        String datapath ="";
        try(BufferedReader reader = new BufferedReader(new FileReader(conf))){
            datapath = reader.readLine().trim();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return datapath;
    }
    public static ArrayList<String> getFilters()
    {
        ArrayList<String> filters = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(conf))){
            reader.readLine().trim();
            String str= reader.readLine();
            while (str != null) {

                    filters.add(str);
                    str = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filters;

    }
}

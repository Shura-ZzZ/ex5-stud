import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Catalog {
    private ArrayList<Product> catalog;
    private ArrayList<String> filter;
    private File file;

    public Catalog() {
        catalog = new ArrayList<Product>();
    }

    public void setFilter(ArrayList<String> filter) {
        this.filter = filter;
    }


    public Catalog(String path, ArrayList<String> filter) {
        catalog = new ArrayList<>();
        file = new File(path);
        this.filter=filter;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String str;
            str = reader.readLine();
        while (str != null)
            {
                try {
                    addProduct(str);
                    str = reader.readLine();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @Override
    public String toString() {
        String str = "List of products";
        for (Product p : catalog) {
            str += "\n" + p.toString() + " rub.";

        }
        return str;

    }

    public String FileString() {
        String str = "";
        for (Product p : catalog) {
            str += p.toString() + "\n";

        }
        return str;

    }

    public boolean contains(Product p) {
        return catalog.contains(p);


    }

    public boolean contains(String name) {

        long c = catalog.stream().filter(p -> p.getName().equals(name.trim())).count();
        if (c == 0) return false;
        return true;
    }

    public Product searchProduct(String name) {

        Product x = new Product();
        try {
            x = catalog.stream().filter(p -> p.getName().equals(name.trim())).findFirst().orElse(new Product());
        } catch (Exception e) {

        }
        return x;

    }

    private boolean checkFilters(String str) {
        for (String f : filter) {
            Pattern pt = Pattern.compile(f);
            Matcher mt = pt.matcher(str);
            if (mt.find()) return true;
        }
        return false;

    }

    public void saveInFile() {
        try (FileWriter writer = new FileWriter(file, false)) {
            String text = this.FileString();
            writer.write(text);
            writer.flush();
        } catch (Exception e) {
            System.out.println("Error save");
        }

    }

    public boolean addProduct(String str) throws Exception {
        if (checkFilters(str))
            return false;
        String[] a = str.trim().split(" +");
        Product p = this.searchProduct(a[0]);
        if (p.getName().equals("None")) {
            p = new Product(str);

            catalog.add(p);
            return true;

        } else
            return false;
    }

    public boolean deleteProduct(String str) throws Exception {
        Product p = this.searchProduct(str);
        if (!p.getName().equals("None")) {
            catalog.remove(p);
            return true;
        } else
            return false;
    }


}

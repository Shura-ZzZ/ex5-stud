import java.util.Objects;

public class Product {
    private String name;
    private double prise;

    public Product() {
        name = "None";
        prise = 0;

    }

    public Product(String name, double prise) throws Exception {

        this.name = name;
        if (prise < 0)
            throw new Exception("Prise error");
        this.prise = prise;

    }

    public Product(String str) throws Exception {
        String[] a = str.trim().split(" +");
        try {


            this.name = a[0];
            double priseStr = Double.parseDouble(a[1]);
            if (priseStr < 0)
                throw new Exception("Prise error");
            this.prise = priseStr;
        } catch (Exception e) {
            throw new Exception("Line entry error");

        }

    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " " + prise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.prise, prise) == 0 && Objects.equals(name, product.name);
    }
}

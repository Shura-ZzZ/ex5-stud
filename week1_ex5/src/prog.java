import java.util.Scanner;

public class prog {
    public static void main(String[] args) {

        Catalog catalog = new Catalog(LoadConf.getPath(), LoadConf.getFilters());
        int command =-1;
        String  answer;
        Scanner sc = new Scanner(System.in);
        System.out.println("1 - add product\n"
                +"2 - delete product\n"
                +"3 - search product\n"
                +"4 - catalog\n" +
                "5 - save" +
                "\nother - exit\n");
        if(sc.hasNextInt()) {
             command= sc.nextInt();
        }
        while (true){

            switch (command){
                case (1):
                    try {
                        System.out.println("Enter the name and price:");
                        Scanner in = new Scanner(System.in);
                        answer = in.nextLine();
                        if(catalog.addProduct(answer))
                            System.out.println("Product added");
                        else
                            System.out.println("Prohibited product!");
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case (2):
                    try {
                        System.out.println("Enter the name:");
                        Scanner in = new Scanner(System.in);
                        answer = in.nextLine();
                    if(catalog.deleteProduct(answer))
                        System.out.println("Product removed");
                    else
                        System.out.println("Product not found");}
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                    break;
                case(3):
                    System.out.println("Input name:");
                    Scanner in = new Scanner(System.in);
                    answer = in.nextLine();
                    Product p = catalog.searchProduct(answer);
                    if(p.getName().equals("None"))
                        System.out.println("Product not found");
                    else
                        System.out.println(p);
                    break;
                case(4):
                    System.out.println(catalog);
                    break;
                case (5):
                    catalog.saveInFile();
                    break;
                default:
                    System.out.println("End");
                    System.exit(0);
            }
            try {
                command = sc.nextInt();

            }
            catch (Exception e)
            {
                System.out.println("End");
                System.exit(0);
            }


        }
    }
}

import java.sql.*;
import java.util.Scanner;

public class Project_IMS 
{
    public static void main(String[] args) throws Exception 
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/IMS", "root", "idkmypasssry01@");
        Scanner x = new Scanner(System.in);

        while (true) 
        {
            System.out.println("-----Inventory Management System-----");
            System.out.println("1. Manage Stock");
            System.out.println("2. Sale");
            System.out.println("3. Manage Product");
            System.out.println("4. View Profit Details");
            System.out.println("5. Exit");

            int z = x.nextInt();

            switch (z) 
            {
                case 1:
                    System.out.println("1. Update Qty");
                    System.out.println("2. View Stock");
                    int y = x.nextInt();

                    switch (y) 
                    {
                        case 1:
                            System.out.print("ID: ");
                            int id = x.nextInt();
                            System.out.print("Quantity : ");
                            int q = x.nextInt();
                            PreparedStatement p = c.prepareStatement("UPDATE product SET prductQty = prductQty + ? WHERE product_id = ?");
                            p.setInt(1, q);
                            p.setInt(2, id);
                            p.executeUpdate();
                            System.out.println("Updated");
                            break;

                        case 2:
                            Statement s = c.createStatement();
                            ResultSet r = s.executeQuery("SELECT * FROM product");
                            while (r.next()) 
                            {
                                System.out.println(r.getInt(1) + " " + r.getString(2) + " " + r.getInt(5));
                            }
                            break;

                        default:
                            System.out.println("Wrong option");
                            break;
                    }
                    break;

                case 2:
                    System.out.println("1. Insert Sale");
                    System.out.println("2. View Sale");
                    int a = x.nextInt();

                    switch (a) 
                    {
                        case 1:
                            System.out.print("Enter product id: ");
                            int pid = x.nextInt();
                            System.out.print("Quantity sold: ");
                            int sq = x.nextInt();

                            PreparedStatement ps1 = c.prepareStatement("UPDATE product SET prductQty = prductQty - ? WHERE product_id = ?");
                            ps1.setInt(1, sq);
                            ps1.setInt(2, pid);
                            ps1.executeUpdate();

                            PreparedStatement ps2 = c.prepareStatement("INSERT INTO sales (product_id) VALUES (?)");
                            ps2.setInt(1, pid);
                            ps2.executeUpdate();

                            System.out.println("Sale Done");
                            break;

                        case 2:
                            Statement s2 = c.createStatement();
                            ResultSet r2 = s2.executeQuery("SELECT s.sale_id, p.productName FROM sales s JOIN product p ON s.product_id = p.product_id");
                            while (r2.next()) 
                            {
                                System.out.println(r2.getInt(1) + " - " + r2.getString(2));
                            }
                            break;

                        default:
                            System.out.println("Wrong choice");
                            break;
                    }
                    break;

                case 3:
                    System.out.println("1. Add Product");
                    System.out.println("2. View Products");
                    System.out.println("3. Delete Product");
                    int o = x.nextInt();

                    switch (o) 
                    {
                        case 1:
                            System.out.print("ID: ");
                            int i = x.nextInt();
                            x.nextLine();
                            System.out.print("Name of the product : ");
                            String n = x.nextLine();
                            System.out.print("Purchase price : ");
                            double pp = x.nextDouble();
                            System.out.print("Selling price: ");
                            double sp = x.nextDouble();
                            System.out.print("Quantity: ");
                            int qt = x.nextInt();

                            PreparedStatement ps = c.prepareStatement("INSERT INTO product VALUES (?, ?, ?, ?, ?)");
                            ps.setInt(1, i);
                            ps.setString(2, n);
                            ps.setDouble(3, pp);
                            ps.setDouble(4, sp);
                            ps.setInt(5, qt);
                            ps.executeUpdate();

                            System.out.println("Product added");
                            break;

                        case 2:
                            Statement st = c.createStatement();
                            ResultSet rs = st.executeQuery("SELECT * FROM product");
                            while (rs.next()) 
                            {
                                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getDouble(3) + " " + rs.getDouble(4) + " " + rs.getInt(5));
                            }
                            break;

                        case 3:
                            System.out.print("Enter ID: ");
                            int pid2 = x.nextInt();
                            PreparedStatement psd = c.prepareStatement("DELETE FROM product WHERE product_id = ?");
                            psd.setInt(1, pid2);
                            psd.executeUpdate();
                            System.out.println("Deleted");
                            break;

                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                    break;

                case 4:
                    Statement stp = c.createStatement();
                    ResultSet rsp = stp.executeQuery("SELECT salePrce, purchasePrice FROM product");
                    double profit = 0;
                    while (rsp.next()) 
                    {
                        profit += rsp.getDouble(1) - rsp.getDouble(2);
                    }
                    System.out.println("Total profit (per unit): " + profit);
                    break;

                case 5:
                    System.out.println("Bye");
                    c.close();
                    return;

                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
    }
}

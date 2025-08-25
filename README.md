# Inventory Management System (IMS)

The **Inventory Management System (IMS)** is a Java-based console application that uses **MySQL** as its backend database. It provides core features for managing products, stock, sales, and profit calculation in a simple menu-driven interface.

---

## Features

- **Product Management**
  - Add new products with ID, name, purchase price, selling price, and quantity
  - View all products
  - Delete products by ID

- **Stock Management**
  - Update product quantity
  - View stock details

- **Sales Management**
  - Insert a sale transaction (auto updates stock and records sale)
  - View sales history with product details

- **Profit Details**
  - Calculate profit as the difference between selling price and purchase price

---

## Tech Stack

- **Programming Language**: Java
- **Database**: MySQL
- **Connector**: JDBC

---

## Database Structure

### Product Table (`product`)
| Column        | Type        | Description               |
|---------------|-------------|---------------------------|
| product_id    | INT (PK)    | Unique product ID         |
| productName   | VARCHAR     | Name of the product       |
| purchasePrice | DOUBLE      | Cost price per unit       |
| salePrce      | DOUBLE      | Selling price per unit    |
| prductQty     | INT         | Available quantity        |

### Sales Table (`sales`)
| Column    | Type      | Description                     |
|-----------|-----------|---------------------------------|
| sale_id   | INT (PK)  | Unique sale transaction ID      |
| product_id| INT (FK)  | References product table        |

---

## How to Run

1. Clone the repository or copy the source code.
2. Create a MySQL database named **IMS**.
3. Create the required tables (`product`, `sales`) using the schema above.
4. Update your MySQL credentials inside the code:
   ```java
   Connection c = DriverManager.getConnection(
       "jdbc:mysql://localhost:3306/IMS", "root", "your_password_here"
   );
   ```
5. Compile and run the project:
   ```bash
   javac Project_IMS.java
   java Project_IMS
   ```

---

## Future Enhancements

- Add user authentication (admin/staff roles)
- Include detailed profit reports (daily, monthly, yearly)
- Implement GUI for better usability
- Add backup and restore functionality

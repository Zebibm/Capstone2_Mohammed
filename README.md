 THE BURGER LAB








 Overview

The Burger Lab is a Java console-based ordering system that simulates a real burger restaurant.
Users can build custom orders, add burgers, drinks, and sides, and complete checkout with payment options.

This project is designed for students learning Java OOP, arrays, and file handling.

 Features
 Build custom burgers (size, bun, stuffed option)
 Add drinks with different sizes and flavors
 Add side items (fries, nuggets, onion rings)
 Automatic receipt generation
 Cash & Card payment system
 Change calculation for cash payments
 Customer name support
 Auto-generated order ID
 Save receipts to file system
 Colored terminal UI (ANSI colors)
 Loading animation effect
 OOP Concepts Used
Classes & Objects
Encapsulation
ArrayLists
Methods & Constructors
StringBuilder (for receipts)
File Handling
Static variables (Order ID system)
 Project Structure
org.example
│
├── Main.java
├── UserInterface.java
├── Order.java
├── Burger.java
├── Drink.java
├── Side.java
└── receipts/   (auto-generated folder)
 How to Run
1. Clone the project
git clone https://github.com/your-username/burger-lab.git
2. Open in IntelliJ / Eclipse / VS Code
3. Run Main.java
 Example Output
Order ID: 1
Customer: Zebib

BURGERS:
Medium Burger
Bun: Wheat
Stuffed: Yes
Burger Price: $11.00

TOTAL: $11.00
 Payment System
Cash payment → calculates change
Card payment → instant approval
 Receipt System

After checkout:

Receipt is saved automatically in /receipts
File name format: yyyyMMdd-HHmmss.txt
 Preview
Welcome to THE BURGER LAB!
1) New Order
0) Exit
 Future Improvements
 Add toppings system
 Combo meals
 Discounts & coupons
 GUI version (JavaFX / Swing)
 Database storage (MySQL)
 Author

Student Project – Learning Java OOP

License

This project is for educational purposes only.

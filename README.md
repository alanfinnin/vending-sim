Files:
    - Driver.java
    - GUI.java
    - CLI.java
    - Methods.java
    - LoadFile.java
    - SaveFile.java
    - Stock.txt
    - Money.txt
    - Admin.txt

Methods.java:               CLI.java/GUI.java:              Driver.java:
    - buyProduct()              - accountChoice()               - methods.load()
    - insertCoin()              - login()                       - GUI/CLI.run()
    - showProducts()            - userMenu()                    - methods.save()
    - addProduct()              - adminMenu()                   - exit()
    - removeMoney()
    - createAccount()       
    - load()
    - save()

The driver would contain the main method which chooses whether the user would like a CLI or a GUI and runs the choesen menu. All
needed methods would be contained inside the methods file for easy access by either of the menu classes. The driver class would
also load the Stock, Money and Admin csv files into three different global ArrayLists that can be accessed from anywhere. After 
the menus are finished the program would clear these files and save all the data from the ArrayLists to the file, beofre exiting.
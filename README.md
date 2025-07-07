# BookManagerV3
## UI Layout
I used a BorderLayout to have distinct areas for each aspect of my application. The North panel contains the SearchPanel and is used to search for a keyword in either author or title. I used a FlowLayout for this so that they align to the left and then fit. 
The center panel houses the table and will highlight which book is selected. The south panel is where the information about the selected book is able to be updated. This panel is the user input location where 
books can be added as well. There are buttons and input text boxes that follow a girdLayout as I wanted to try and use each of the 3 highlighted in class. This gives equal space to each "box" on the grid.

## How to Run the Program
To add a book to the table, type valid strings for title and author, valid double for price, and a valid integer for quantity. Then, press the "Add Book" button.
![image](https://github.com/user-attachments/assets/0d231822-145a-4b9c-af80-e6f911148a06)

To update a book in the table, select which book you'd like to change by clicking on it with the mouse. Then, change the values in the input panel to the desired values and select the "Update Book" button. 
![image](https://github.com/user-attachments/assets/25c41cda-dd96-4adf-9686-c4ba9b830b9a)

To delete a book in the table, select which book you'd like to remove by clicking on it with the mouse. Then select the "Delete Book" button. Confirm that you want to delete the book. 
![image](https://github.com/user-attachments/assets/6db84377-ddcc-4cd6-ac41-337ce4dde300)

The Clear Fields button clears anything in the input text boxes in the south panel. Additionally, you can perform a search for author or title inthe top panel by typing your keyword in the box and pressing search. 
![image](https://github.com/user-attachments/assets/ac188e2c-67b0-4187-81e0-5cca170a5d54)

Even when restarting the program, your changes will be saved. 

## Package Structure
Main.java

### Model
Book.java
BookModel.java

### Controller
BookController.java

### View
BookView.java

## Additional Validation Screenshots
Empty Title
![image](https://github.com/user-attachments/assets/8bf60d83-c117-41fc-a577-e520601ca798)

Empty Author
![image](https://github.com/user-attachments/assets/ccfbad78-d0b9-4f3f-ab21-ae13ce5dd7e7)

Quantity >= 0
![image](https://github.com/user-attachments/assets/d72b6ee2-0554-482f-9040-68a992e0626b)
![image](https://github.com/user-attachments/assets/c2a39cea-68f6-4cda-b774-263a80559f73)

Price > 0
![image](https://github.com/user-attachments/assets/c3c8c153-f495-477e-aae0-28e1293666a9)
![image](https://github.com/user-attachments/assets/fb8813cd-a42f-4b10-b244-af69723b16c2)

Delete Confirmation
![image](https://github.com/user-attachments/assets/cb54966a-ebe7-4450-ab64-34fbcf27acd2)

![image](https://github.com/user-attachments/assets/f3d5f4ff-a87c-4d71-9fb2-dd0dc4abad78)


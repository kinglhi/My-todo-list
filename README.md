# My-todo-list

A very simple to-do list application.
A SQL database is integrated so as to allow for data persistence whenever the app is closed. ROOM library is used to simplify the CRUD queries with the database.
It only contains one fragment and an alert dialog that will take user input, store the contents in the database and display it in the fragment.

Fragment 1
User is displayed an empty fragment at first or a list of to-do tasks previously added to the database. Ther is a Floating Action Button at the bottom to add a new to-do.

<img width="182" alt="image" src="https://user-images.githubusercontent.com/38509636/193653371-2ca2786b-3050-4327-8570-ea49e51919f6.png">

Alert Dialog
Once the FAB is clicked, an alert dialog is displayed and an option to input text and select the priority radiobutton is displayed.
Priority levels are: Urgent, High, Medium and Low each with a different color code.
Clicking on the ADD button will add the input text into the database and displayed on the Recyclerview in the main fragment.
We'll be adding a new to-do with medium priority level:

<img width="185" alt="image" src="https://user-images.githubusercontent.com/38509636/193654460-e584a936-a7e1-40f5-b62b-7972371fee42.png">

New to-do displayed in the specified priority level color code:

<img width="182" alt="image" src="https://user-images.githubusercontent.com/38509636/193654680-fb4d09a9-f3cd-48e2-9ec5-714bbd90a712.png">

Clicking on the checkbox at the side will delete that to-do:

<img width="182" alt="image" src="https://user-images.githubusercontent.com/38509636/193653371-2ca2786b-3050-4327-8570-ea49e51919f6.png">





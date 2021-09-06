# Students_in_library
An example usage of threads in java

                                           Operating Systems Project 
                                              Books in the Library
A group of students need to use the same books to perform their term project. There are 40 students in the class 
(they have consecutive school numbers from 1 to 40) and all need the same 6 books from the library. 
The students arrive at the library in random order, and they compete to talk one of the 3 librarians (Students may try to catch one of the librarians randomly). 
When a student catches one of the librarians, he/she asks the book he/she needs. The book needed is generated randomly. 
The librarian should check if the book was borrowed by any other student. If not, that book is assigned to that student for a random time period in milliseconds.
If the book is borrowed by someone else the student should leave, wait for a random duration and try to talk to one of the librarians once more.
Each librarian should be able to see the states of the books even if they were borrowed by another librarian.
Each student needs to read all of the 6 books to complete his/her term project.
The project will be implemented either with C or Java but using threads is a must.

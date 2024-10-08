# Introduction
## What is this?
This repository provides a solution to the iteration of BUAA OOP course in 2024.
## What can it do?
We want to create an academy platform to help us with handling education stuff. Generally speaking, in this platform, we have **Student, Teacher and Administrator**. 
- Student can **list courses in the platform(create by teacher), select course, cancel course he or she selected, list his or her own course schedule and upload it**. 
- Teacher can **create course, list his or her own courses, cancel course he or she created, list those who selected his or her course, remove some of them(kick them out of his of her course!) and output or input the basic information of couses in batch.**
- Administrator can do A LOT, but to be general, administrator could **see the information of everyone and everything casually and do everything Student and Teacher can do EXCEPT for creating something new.**
## How does the code work?
In short, when the system get a command, it would do **Command-Analyze and Command-Execute(Two classes)**, Command-Analyze would check the **existence and legality** of the command, and Command-Execute would do what the command what to do. 

What they will call to help finish their tasks are **Validators and Executors**. Validators would do everything to check whether the command can be executed, and Executors would exactly execute the command.

Validators and Executors would call **Manipulators** to get data and then operates on data. Manipulators would provide with very **BASIC** interfaces just like **Mapper** layer in Springboot.

Finally, since students haven't learnt database management technique yet, **XXX Data** would serve as tables in a relation database.
## What else should you know?
Although this is a homework, it concentrates my intelligence to design the code structrue as good as possible. When it comes to design pattern, there probably includes **Bridge Pattern, Singleton Pattern, Command Pattern**. The **State Pattern** is excluded while it is the main design pattern when I write the code, but as time passes by, it can be verified that it is not suitable.

However, this version of solution is a **superset** of a standard solution, since another OOP assistant has changed the test case, and the command OpenFile and UploadCourseSchedule are **deleted**.

## How to run this code?
First, the work directory should be the **father directory** of src.

Second, if you want to use input and output file rather than standard I/O stream, you just need to go to src/enums/PATH,.java, and change **IN** and **OUT** to the path of input file and output file, or **null** to use standard I/O stream.

Third, my code can be run with JDK 17, **BUT** I didn't test other version of JDK. Therefore, if something goes wrong when compiling, then change the version of JDK first.

Forth, click the "Run" button and RUN!!
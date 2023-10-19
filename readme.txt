Software Engineering - CS 305

MidSem-Project

The UI is build for three types of users namely Student, Faculty and Admin.


Student User:

1. Student is allowed to view all of his grades he has gotten so far.
2. Student can compute his cgpa upto the the semester that has been graded.
3. Student can view all the courses in the catalog as well as all the courses offered.
4. Student can enroll in any of the courses as long as the student fulfils all the necessary conditions.
5. Student can view all of his current registrations in the running semester
7. Student can change his password in security breach cases.

Faculty User:

1. Faculty can see all the courses offered along with only the courses he has offered.
2. Faculty can view the catalog in order to see which all courses he can offer.
3. Faculty can offer and de-offer a course as during the Course Offering Period.
4. Faculty can view all the students and there grades in his course.
5. Faculty can grade manually or through a csv file.
6. Faculty can also change password.

Admin User:

1. Admin can change the current status of the semester which are written below in the readme.
2. Admin can add or remove a student or faculty or a course in Pre Semester Begin Period.
3. Admin can view the list of all students, Faculty.
4. Admin can view the course catalog and offered courses.
5. Admin can view the Student all courses taken and grades.
6. Admin can check if a student can Graduate or not. The student can only graduate during the Semester Ended Period.
7. Admin can create each student transcript with the name as Entry_Number_transcript.txt.


There are 7 status during a semester.

1. Pre Semester Begin Period - (Admin can Add or Remove Courses,Student or Faculty)
2. Course Offering Period - (Faculty can offer or de-offer the courses in the semester)
3. Course Enrollment Period - (Students can enroll or drop from the offered courses)
4. Semester Period - (The running semester period)
5. Grading Period - (The faculty can grade their courses)
6. Updating Student Record Period - (Admin updates the Student credits, cgpa and place the current semester courses along with all courses)
7. Semester Ended Period - (Student eligible to graduate can graduate and the calender is updates to next Academic Semester)


CSV File:

1. Students, faculty and courses can be added through CSV file.
2. Faculty can grade the course through CSV file

Student CSV File Formate:
Entry_Number,Name,Email,BatchID,Login_key,CGPA,credits

Faculty CSV File Format
Faculty_ID,Faculty_Name,Email,Department,Login_key

Course CSV File Format
CourseID, Course_Name, L,T,P,C,Department,Category,Pre-requisites

Here category means 
PCE i.e. Core for department and Elective for other
PE i.e. Elective for all
BTP i.e. Capstone Courses

For a student to graduate he should have atleast 60 Core Courses Credit, 30 Elective Courses Credit and 6 BTP Credits.

The UI is self explanatory for the flow of movement and User Ease.


HOW TO RUN THE PROJECT

To run the project Download the Zipped file and open the Folder using and IDE.
Go to Softie and then softie_proj directory
Run gradle build command 
Then run gradle run --console=plain command



Order of Commands:

cd Softie
cd softie_proj
gradle build
gradle run --console=plain







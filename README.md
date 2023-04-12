# Soccer League Tracker
## What will this application do?
This application will allow you to track all the matches in a soccer league in three ways :

* ***Match-wise***; the application will allow you to add matches between the teams in the league.


* ***Team-wise***; the application will allow you to add new teams to the league, and it can produce the match history of a specific team you are interested. 


* ***League-wise***; the application will produce the standings of all the teams according to the recorded match scores,
where a win will result in 3 points, a draw 1 point, and a loss 0 points. In addition, it will also display the W-D-L record of teams for the season to know how the team is performing.


### Who will use it?
Soccer league organizers can keep track of all the matches that are happening in the
league and update the league standing accordingly.

### Why is this project of interest to me?
In high school, I was part of the soccer team, where we would play weekly in the school league. The match scores and
other stats were all recorded on paper.

The pen and paper method was  adequate in the short-term but as
more games were played, this method was **error-prone** as some match records
were lost, and **updating the league standing was challenging**.




## User Stories
* As a user, I want to be able to *create a new team and add it to a league consisting of arbitrary number of teams*.


* As a user, I want to be able to *create a new match score between the league teams and add it to the list of match history*.


* As a user, I want to be able to *select a team and view its complete match history*.


* As a user, I want to be able to *view all the matches recorded and all the teams added to the league*.


* As a user, I want to be able to *create a league standing to see how many points each team has*.


* As a user, I want to be able to *save the match scores and teams added I added to the file (if I choose to)*.


* As a user, I want to be able to *load all the match scores and teams I saved in the file(if I choose to)*.

## Instructions For Grader

- You can add multiple teams to a league by pressing team button followed by the add team button in the new panel.
- You can view all the teams added to the league, by pressing the team button followed by the list of teams 
button in the new panel.
- You can generate the first required action related to adding Xs to a Y by pressing league button where the teams
are arranged to the number of points they have.
- You can generate the second required action related to adding Xs to a Y by pressing the team button followed by
the team records button where you can see the match record of a specific team.
- You can locate my visual component when starting my application, a splash screen will appear and will 
disappear when you click anywhere on the screen
- You can save the state of my application by pressing the save button
- You can reload the state of my application by pressing the load button.

## Phase 4: Task 2
The User loaded the saved league

Wed Apr 12 13:47:03 PDT 2023

team1 was added to the league

Wed Apr 12 13:47:03 PDT 2023

team3 was added to the league

Wed Apr 12 13:47:03 PDT 2023

team4 was added to the league

Wed Apr 12 13:47:03 PDT 2023

team2 was added to the league

Wed Apr 12 13:47:03 PDT 2023

team5 was added to the league

Wed Apr 12 13:47:03 PDT 2023

The User loaded the saved match records

Wed Apr 12 13:47:03 PDT 2023

new match between team1 and team2 was added

Wed Apr 12 13:47:03 PDT 2023

new match between team3 and team4 was added

Wed Apr 12 13:47:03 PDT 2023

new match between team5 and team1 was added

Wed Apr 12 13:47:18 PDT 2023

Team6 was added to the league

Wed Apr 12 13:47:25 PDT 2023

The User looked at the match records for team1

Wed Apr 12 13:47:40 PDT 2023

new match between Team6 and team3 was added

Wed Apr 12 13:47:42 PDT 2023

The User looked at the match records

Wed Apr 12 13:47:48 PDT 2023

The User looked at the league standing

Wed Apr 12 13:47:51 PDT 2023

The User saved the current league

Wed Apr 12 13:47:51 PDT 2023

The User saved the current match records


## Phase 4: Task 3

<img src="UML_Design-Diagram.png">

To improve the organization of my code, I plan to make changes to my SoccerLeagueGUI class. 
As it currently stands, the class is responsible for performing multiple tasks, resulting in low cohesion.
To address this issue, I aim to divide the functionality of the class into several smaller, more focused classes.
By assigning each class a specific task, I will increase the cohesion of my code, making it more readable, manageable, 
and reusable.

Currently, my code saves and reads two separate lists, "League" and "MatchRecords", 
into two distinct JSON files. However, I can  optimize the program by refactoring the code to use a single file for 
both saving and reading data. By adopting this approach, the program will require less memory and run faster, 
since it only needs to access a single file to complete the save and load operations.



### Reference 
Used code from Teller application to develop my UI
https://github.students.cs.ubc.ca/CPSC210/TellerApp

Learned how to create the league table format from stackoverflow
https://stackoverflow.com/questions/61115674/java-how-to-create-basic-text-entry-and-output-for-football-league-standings
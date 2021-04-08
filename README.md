# CPSC 210 Project
## Movie Tracker App

For my personal project, I plan to make a **movie tracker app.** This app will be helpful for people who want to keep a 
list of movies they have watched, as well as keep leave ratings on each movie on a scale of one to five stars. 
Some user stories to describe this app include:

- As a user, I want to be able to add a movie to a list of movies I have seen
- As a user, I want to be able to view a list of movies titles I have seen
- As a user, I want to be able to select a movie and view the year it was released
- As a user, I want to be able to be able to select a movie and rate it on a scale of 5 stars 
- As a user, I want an option to save my movie list.
- As a user, when I start the movie app i want to have to option to load my movie list from a file.
- As a user, I want to be able to filter my list and view only 5 star rated movies.

**Phase 4: Task 2**

In the Movie class, i removed the requires clause for setRating() method. If the rating is not 
between 1 and 5 an exception is thrown. In the MovieAppGUI class the exception is caught and 
an error message pops up, and no rating is set for that movie. 

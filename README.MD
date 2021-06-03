
# Exercise
# How to try it
* Clone the repository
* Configure the input. For example If you want to run this input:
    * Grid -> 5 5
  * Rover 1 starting point -> 1 2 N
  * Rover 1 commands -> LMLMLMLMM
  * Rover 2 starting point ->3 3 E
  * Rover 2 commands -> MMRMMRMRRM 
    Please, format the arguments in the following way:
gradle run --args="5 5 1 2 N LMLMLMLMM 3 3 E MMRMMRMRRM"
    
The above arguments are valid ones.
Try as many inputs you want to


#Caveats
##Happy path. Expected use case.
The happy path for this exercise happens when a user introduces a valid input.
This exercise is customized to this input:
* Grid
Refers to a square grid, the first two arguments should be positive integers
* Rover info
** Position: 
Coordinates and orientation should be inside the grid. 
If there are more rovers in the grid, then this position should be available.
** Commands:
The rover can understand the commands L,R,M.
The introduced commands should allow the rover to move inside the grid and withoud collided with other rovers.
## Invalid input
The input is validated to check that the happy path can be done. Otherwise, the system fail to build and generate an IllegalArgumentException
The exception will contain the message saying why is not working.

If the system fails to be built because an error in the logic, a Runtime Exception is thrown. 

If the input is valid, the system will be launched to planet Mars.

# Journey solving the exercise
## [Hand Tracing solution](/Handtracing.md)

## Coding
#C) What I learnt


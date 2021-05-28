# MarsRover

Analysis:

Created excel to simulate inputs/output
https://docs.google.com/spreadsheets/d/1Tgi21XyNALctiZoDoq12OxXhYpa3hetB5niTpG4PtGI/edit?usp=sharing

Using mathematical concept to hand trace and create sample inputs/output.

Main concepts:

From 360 degrees in a circunference, the cardinal point can be translate to the degree domain:

North -> 90  
West -> 180
South -> 270
East -> 0 and 360. 

We use the remainder 



The commands behaviour are:
L-> +90 in the rover angle
R-> -90 in the rover angle, 

but coordinates are unaffected  in the Rover(0,0)

When we use "M" command, the angle is unaffected but the coordinates changed depending on the rover cardinal position, 
then, in the mathematical domain:

Switch:
CASE North:  xIncrement =0 and yIncrement = 1
CASE West: xIncrement = -1, and yIncrement = 0
Case South: xIncrement = 0 and yIncrement = -1,
case East: xIncrement = 1, yIncrement = 0

Regarding the area where the Rover will be exploring, before updating the rover position, we need to check that the new position is inside 
the area, then, we model it using the "function domain"
In the case of the plateau, the domain would be x>0 and x<= Plateau UpperRight X Coordinate
y>0 and y<=Plateau UpperRight Y coordinate

If the Plateu had another shape, we have to get the function domain.

The excel will be useful to write the test cases before implementing the code






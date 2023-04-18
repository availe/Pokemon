# Pokemon
 
 A text-based Pokemon game that utilizes reflection in order to instantiate Pokemon classes. The reflection occurs inside the calculateDamage() method within the Attack.java. The program will attempt to instantiate the class, and if it exists will be able to retrieve the list of superEffective and notEffective attacks from said class. Otherwise, the program will check to see if the attackType exists in the Pokemon games (even if it wasn't implemented yet). If it is a valid type that wasn't implemented yet, then it would be treated as a default attack with no modifier. Otherwise the attackType is treated as invalid and ends the game.
 
 The approach allows the program to be flexible and scalabale, as new Pokemon classes (and by extension attackTypes) can be added without changing any of the program's base code.

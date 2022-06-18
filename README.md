Chess
![Snímek obrazovky (40)](https://user-images.githubusercontent.com/80472796/174432466-83ba6fed-0e09-463c-969c-50f0c2f93d5d.png)
Control:
* Menu and whole game is controlled with the mouse

Functionalities:
* Checking and visibility of possible moves
![Snímek obrazovky (32)](https://user-images.githubusercontent.com/80472796/174432298-f47a5ee2-bb96-4a8a-88c0-578209850110.png)

* Manual placement of figures in cooperative mode
![Snímek obrazovky (36)](https://user-images.githubusercontent.com/80472796/174432313-3dd8fd22-f180-48f8-98d4-34655ada3aa1.png)

* Selection of pieces after reaching the end of the board by a pawn
![Snímek obrazovky (38)](https://user-images.githubusercontent.com/80472796/174432344-8731d572-b02c-498e-be4d-ed88e47273e0.png)

* Game against computer
* Chess timer 
* Loading and saving games to xml files
* Loading, saving and viewing games in pgn format

Structure of program:
* The project is structured into 5 main packages.
* The AI package is used for different levels of AI that can be played against. Currently contains only a random stroke generator.
* The Chessloader package contains classes for loading and saving games in xml and pgn format.
* The Figures package contains the abstract Figure class, on which all Figure classes depend.
* The Game package contains all classes needed to play a game. To start the game, a new instance of ChessGame class must be created, which will then create a scene and start game.
* The GUI package contains all the classes used for the graphical appearance of the game. Scene menus are loaded from fxml files. The class contains a main fxml controller that switches between scenes

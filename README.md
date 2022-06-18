Chess

Control:
* Menu and whole game is controlled with the mouse

Functionalities:
* Checking and visibility of possible moves
* Manual placement of figures in cooperative mode
* Game against computer
* Chess timer 
* Loading and saving games to xml files
* Loading, saving and viewing games in pgn format
* Selection of pieces after reaching the end of the board by a pawn

Structure of program:
* The project is structured into 5 main packages.
* The AI package is used for different levels of AI that can be played against. Currently contains only a random stroke generator.
* The Chessloader package contains classes for loading and saving games in xml and pgn format.
* The Figures package contains the abstract Figure class, on which all Figure classes depend.
* The Game package contains all classes needed to play a game. To start the game, a new instance of ChessGame class must be created, which will then create a scene and start game.
* The GUI package contains all the classes used for the graphical appearance of the game. Scene menus are loaded from fxml files. The class contains a main fxml controller that switches between scenes

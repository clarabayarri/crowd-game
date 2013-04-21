/**
    A controller for the game
    @class
*/
function PausedGameController()
{

    /**
        Initialises this object
        @return A reference to the initialised object
    */
    this.startupPausedGameController = function(/**ApplicationManager*/applicationManager)
    {
        this.startupGameController(applicationManager, null);
        
        this.loadView();
        
        return this;        
    }
    
    this.loadView = function() {
        var continueOrigin = new Point().init(0, 0);
        var continueBounds = new Bounds().init(continueOrigin, this.canvas.width, this.canvas.height);
        this.dialog = new StartDialog().startupDialog(continueBounds);
        this.gameObjects.push(this.dialog);
    }
    
}
PausedGameController.prototype = new GameController;
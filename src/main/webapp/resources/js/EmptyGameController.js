/**
    A controller for the game
    @class
*/
function EmptyGameController()
{
    /**
        Initialises this object
        @return A reference to the initialised object
    */
    this.startupEmptyGameController = function(/**ApplicationManager*/applicationManager)
    {
        this.startupGameController(applicationManager, null);
        
        this.addLoading();
        
        return this;        
    }
    
    this.addLoading = function() {
    	var loadingOrigin = new Point().init(this.canvas.width * 0.5 - 100, this.canvas.height * 0.5 - 25);
    	var loadingBounds = new Bounds().init(loadingOrigin, 200, 50);
    	var loading = new LoadingSignal().startupLoadingSignal(loadingBounds);
    	this.gameObjects.push(loading);
    }
    
}
EmptyGameController.prototype = new GameController;
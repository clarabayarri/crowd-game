/**
    A controller for the game
    @class
*/
function SeparationGameController()
{
    /**
        Initialises this object
        @return A reference to the initialised object
    */
    this.startupSeparationGameController = function(/**ApplicationManager*/applicationManager, /**Problem*/ problem)
    {
        this.startupGameController(applicationManager, problem);
        
        this.loadChildren();
        this.draw();
        
        return this;        
    }
    
    this.loadChildren = function() {
    	var wordLayoutOrigin = new Point().init(this.canvas.width * 0.025, this.canvas.height * 0.40);
    	var wordLayoutBounds = new Bounds().init(wordLayoutOrigin, this.canvas.width * 0.95, this.canvas.height * 0.25);
    	var wordLayout = new WordLayout().init(this.problem.displayText, wordLayoutBounds);
    	this.gameObjects.push(wordLayout);
    	this.wordLayout = wordLayout;
    }
    
    this.onClickInternal = function(/**Point*/ clickPoint) {
    	var answer = this.wordLayout.getCutIndexForPoint(clickPoint);
    	this.moving = false;
    	if (answer != null) {
    		this.wordLayout.addSpaceAtIndex(answer);
    		this.draw();
    		
    		this.checkForSuccess();
    	}
    }
    
    this.onMouseDownInternal = function(/**Point*/ clickPoint) {
    	var touchedTile = this.wordLayout.getClickedTile(clickPoint);
    	if (touchedTile != null) {
    		this.initialClickPointDifference = clickPoint;
    		this.moving = true;
    	}
    }
    
    this.onMouseMoveInternal = function(/**Point*/ clickPoint) {
    	
    }
    
    this.onMouseUpInternal = function(/**Point*/ point) {
    	if (this.moving) {
    		this.moving = false;
    		var midPoint = new Point().init(this.initialClickPointDifference.x + (point.x - this.initialClickPointDifference.x)/2, this.initialClickPointDifference.y + (point.y - this.initialClickPointDifference.y)/2);
    		var answer = this.wordLayout.getCutIndexForPoint(midPoint);
    		if (answer != null) {
    			this.wordLayout.addSpaceAtIndex(answer);
    			this.draw();
    			
    			this.checkForSuccess();
    		}
    	}
    }
}
SeparationGameController.prototype = new GameController;
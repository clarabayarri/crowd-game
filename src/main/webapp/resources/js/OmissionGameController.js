/**
    A controller for the game
    @class
*/
function OmissionGameController()
{
    /**
        Initialises this object
        @return A reference to the initialised object
    */
    this.startupOmissionGameController = function(/**ApplicationManager*/applicationManager, /**Problem*/ problem)
    {
        this.startupGameController(applicationManager, problem);
        
        this.loadChildren();
        this.draw();
        
        return this;        
    }
    
    this.loadChildren = function() {
    	var wordLayoutOrigin = new Point().init(this.canvas.width * 0.05, this.canvas.height * 0.40);
    	var wordLayoutBounds = new Bounds().init(wordLayoutOrigin, this.canvas.width * 0.9, this.canvas.height * 0.25);
    	var wordLayout = new WordLayout().init(this.problem.displayText, wordLayoutBounds);
    	this.gameObjects.push(wordLayout);
    	this.wordLayout = wordLayout;
    }
    
    this.onClickInternal = function(/**Point*/ clickPoint) {
    	var answer = this.wordLayout.getClickedTile(clickPoint);
    	this.moving = false;
    	if (answer != null) {
    		this.wordLayout.hideChildWithId(answer.id);
    		this.draw();
    		
    		this.checkForSuccess();
    	}
    }
    
    this.onMouseDownInternal = function(/**Point*/ clickPoint) {
    	var touchedTile = this.wordLayout.getClickedTile(clickPoint);
    	if (touchedTile != null) {
    		this.initialClickPointDifference = new Point().init(clickPoint.x - touchedTile.bounds.origin.x, clickPoint.y - touchedTile.bounds.origin.y);
    		this.moving = true;
    	}
    }
    
    this.onMouseMoveInternal = function(/**Point*/ clickPoint) {
    	if (this.moving) {
    		if (!this.movingTile) {
    			var touchedTile = this.wordLayout.getClickedTile(clickPoint);
    			if (touchedTile != null) {
    				this.gameObjects.push(touchedTile);
    				this.movingTile = touchedTile;
    				this.movingTile.moveTo(new Point().init(clickPoint.x - this.initialClickPointDifference.x, clickPoint.y - this.initialClickPointDifference.y));
    				this.wordLayout.hideChildWithId(this.movingTile.id);
    			}
    		} else {
    			this.movingTile.moveTo(new Point().init(clickPoint.x - this.initialClickPointDifference.x, clickPoint.y - this.initialClickPointDifference.y));
    		}
    		this.draw();
    	}
    }
    
    this.onMouseUpInternal = function(/**Point*/ point) {
    	if (this.moving && this.movingTile) {
    		this.gameObjects.pop();
    		
    		this.moving = false;
    		this.movingTile = null;
    		this.draw();
    		
    		this.checkForSuccess();
    	}
    }
}
OmissionGameController.prototype = new GameController;
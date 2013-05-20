/**
    A controller for the game
    @class
*/
function TranspositionGameController()
{

    this.instructions = "Reordena las letras";

    /**
        Initialises this object
        @return A reference to the initialised object
    */
    this.startupTranspositionGameController = function(/**ApplicationManager*/applicationManager, /**Problem*/ problem)
    {
        this.startupGameController(applicationManager, problem);
        
        this.maxMovesAllowed = 3;
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
    			var touchedTile = this.wordLayout.getClickedTile(clickPoint);
    			if (touchedTile.id != this.movingTile.id) {
    				this.wordLayout.exchangeChildrenWithIds(this.movingTile.id, touchedTile.id);
    				this.movingTile.id = touchedTile.id;
    			}
    		}
    		this.draw();
    	}
    }
    
    this.onMouseUpInternal = function(/**Point*/ point) {
    	if (this.moving && this.movingTile) {
    		this.gameObjects.pop();
    		
    		this.moving = false;
    		this.movingTile = null;
    		this.wordLayout.showAllChildren();
    		this.draw();
    		
    		this.checkForSuccess();
    	}
    }
}
TranspositionGameController.prototype = new GameController;
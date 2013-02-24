/**
    A controller for the game
    @class
*/
function SubstitutionGameController()
{
    /**
        Initialises this object
        @return A reference to the initialised object
    */
    this.startupSubstitutionGameController = function(/**ApplicationManager*/applicationManager, /**Problem*/ problem)
    {
        this.startupGameController(applicationManager, problem);
        
        this.loadChildren();
        this.draw();
        
        return this;        
    }
    
    this.loadChildren = function() {
    	var wordLayoutOrigin = new Point().init(this.canvas.width * 0.05, this.canvas.height * 0.20);
    	var wordLayoutBounds = new Bounds().init(wordLayoutOrigin, this.canvas.width * 0.9, this.canvas.height * 0.25);
    	var wordLayout = new WordLayout().init(this.problem.displayText, wordLayoutBounds);
    	this.gameObjects.push(wordLayout);
    	this.wordLayout = wordLayout;
    	
    	var answerLayoutOrigin = new Point().init(this.canvas.width* 0.15, this.canvas.height * 0.6);
    	var answerLayoutBounds = new Bounds().init(answerLayoutOrigin, this.canvas.width * 0.7, this.canvas.height * 0.2);
    	var answerLayout = new AnswerLayout().init(this.problem.displayAnswers, answerLayoutBounds);
    	this.gameObjects.push(answerLayout);
    	this.answerLayout = answerLayout;
    }
    
    this.onClick = function(e) {
    	
    }
    
    this.onMouseDown = function(e) {
    	var clickPoint = this.getEventPosition(e);
    	var touchedTile = this.answerLayout.getClickedTile(clickPoint);
    	if (touchedTile != null) {
    		this.initialClickPointDifference = new Point().init(clickPoint.x - touchedTile.bounds.origin.x, clickPoint.y - touchedTile.bounds.origin.y);
    		this.moving = true;
    	}
    }
    
    this.onMouseMove = function(e) {
    	if (this.moving) {
    		var clickPoint = this.getEventPosition(e);
    		if (!this.movingTile) {
    			var touchedTile = this.answerLayout.getClickedTile(clickPoint);
    			if (touchedTile != null) {
    				this.gameObjects.push(touchedTile);
    				this.movingTile = touchedTile;
    				this.movingTile.moveTo(new Point().init(clickPoint.x - this.initialClickPointDifference.x, clickPoint.y - this.initialClickPointDifference.y));
    				this.answerLayout.hideChildWithId(touchedTile.id);
    			}
    		} else {
    			this.movingTile.moveTo(new Point().init(clickPoint.x - this.initialClickPointDifference.x, clickPoint.y - this.initialClickPointDifference.y));
    		}
    		this.draw();
    	}
    }
    
    this.onMouseUp = function(e) {
    	if (this.moving && this.movingTile) {
    		var clickPoint = this.getEventPosition(e);
    		var answer = this.movingTile.letter;
    		this.gameObjects.pop();
    		
    		this.wordLayout.setChosenAnswerForTileUnderPoint(clickPoint, answer);
    		this.moving = false;
    		this.movingTile = null;
    		this.draw();
    		
    		this.checkForSuccess();
    	}
    }
}
SubstitutionGameController.prototype = new GameController;
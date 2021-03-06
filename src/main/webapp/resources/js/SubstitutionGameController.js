/**
    A controller for the game
    @class
*/
function SubstitutionGameController()
{

    this.instructions = "Cambia una letra";
    this.color = RIBBON_COLOR_PURPLE;

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
    	var wordLayoutOrigin = new Point().init(this.canvas.width * 0.05, this.canvas.height * 0.25);
    	var wordLayoutBounds = new Bounds().init(wordLayoutOrigin, this.canvas.width * 0.9, this.canvas.height * 0.25);
    	var wordLayout = new WordLayout().init(this.problem.displayText, wordLayoutBounds);
    	this.gameObjects.push(wordLayout);
    	this.wordLayout = wordLayout;
    	
    	var answerLayoutOrigin = new Point().init(this.canvas.width* 0.15, this.canvas.height * 0.65);
    	var answerLayoutBounds = new Bounds().init(answerLayoutOrigin, this.canvas.width * 0.7, this.canvas.height * 0.23);
    	var answerLayout = new AnswerLayout().init(this.problem.displayAnswers, answerLayoutBounds);
    	this.gameObjects.push(answerLayout);
    	this.answerLayout = answerLayout;
    }
    
    this.onClickInternal = function(/**Point*/ clickPoint) {
    	
    }
    
    this.onMouseDownInternal = function(/**Point*/ clickPoint) {
    	var touchedTile = this.answerLayout.getClickedTile(clickPoint);
    	if (touchedTile != null) {
    		this.initialClickPointDifference = new Point().init(clickPoint.x - touchedTile.bounds.origin.x, clickPoint.y - touchedTile.bounds.origin.y);
    		this.moving = true;
    	}
    }
    
    this.onMouseMoveInternal = function(/**Point*/ clickPoint) {
    	if (this.moving) {
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
    
    this.onMouseUpInternal = function(/**Point*/ point) {
    	if (this.moving && this.movingTile) {
    		var answer = this.movingTile.letter;
    		this.gameObjects.pop();
    		
    		this.wordLayout.setChosenAnswerForTileUnderPoint(point, answer);
    		this.moving = false;
    		this.movingTile = null;
    		this.draw();
    		
    		this.checkForSuccess();
    	}
    }
}
SubstitutionGameController.prototype = new GameController;
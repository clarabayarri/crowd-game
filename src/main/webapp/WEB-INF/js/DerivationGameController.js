/**
    A controller for the game
    @class
*/
function DerivationGameController()
{
    /**
        Initialises this object
        @return A reference to the initialised object
    */
    this.startupDerivationGameController = function(/**ApplicationManager*/applicationManager, /**Problem*/ problem)
    {
        this.startupGameController(applicationManager, problem);
        
        this.loadChildren();
        this.draw();
        
        return this;        
    }
    
    this.loadChildren = function() {
    	var wordLayoutOrigin = new Point().init(this.canvas.width * 0.05, this.canvas.height * 0.4);
    	var wordLayoutBounds = new Bounds().init(wordLayoutOrigin, this.canvas.width * 0.6, this.canvas.height * 0.25);
    	var wordLayout = new WordLayout().init(this.problem.displayText, wordLayoutBounds);
    	this.gameObjects.push(wordLayout);
    	this.wordLayout = wordLayout;
    	
    	var answerLayoutOrigin = new Point().init(this.canvas.width* 0.7, this.canvas.height * 0.1);
    	var answerLayoutBounds = new Bounds().init(answerLayoutOrigin, this.canvas.width * 0.25, this.canvas.height * 0.8);
    	var answerLayout = new VerticalAnswerLayout().init(this.problem.displayAnswers, answerLayoutBounds);
    	this.gameObjects.push(answerLayout);
    	this.answerLayout = answerLayout;
    }
    
    this.onClick = function(e) {
    	var clickPoint = this.getEventPosition(e);
    	var answer = this.answerLayout.getClickedTile(clickPoint);
    	this.moving = false;
    	if (answer != null) {
    		this.answerLayout.hide();
    		this.wordLayout.bounds.width = this.canvas.width * 0.9;
    		this.wordLayout.appendChosenAnswer(answer.letter);
    		this.draw();
    		
    		this.checkForSuccess();
    	}
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
    		var answer = this.movingTile.letter;
    		this.gameObjects.pop();
    		
    		this.answerLayout.hide();
    		this.wordLayout.bounds.width = this.canvas.width * 0.9;
    		this.wordLayout.appendChosenAnswer(answer);
    		
    		this.moving = false;
    		this.movingTile = null;
    		this.draw();
    		
    		this.checkForSuccess();
    	}
    }
    
    this.reloadInternal = function() {
    	this.wordLayout.bounds.width = this.canvas.width * 0.6;
    }
}
DerivationGameController.prototype = new GameController;
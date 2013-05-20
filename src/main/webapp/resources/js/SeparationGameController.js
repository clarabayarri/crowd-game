/**
    A controller for the game
    @class
*/
function SeparationGameController()
{

    this.instructions = "Corta en palabras";

    /**
        Initialises this object
        @return A reference to the initialised object
    */
    this.startupSeparationGameController = function(/**ApplicationManager*/applicationManager, /**Problem*/ problem)
    {
        this.startupGameController(applicationManager, problem);
        
        this.maxMovesAllowed = this.numSpacesInProblem();
        this.loadChildren();
        this.draw();
        
        return this;        
    }

    this.numSpacesInProblem = function() {
        var count = 0;
        var word = this.problem.word;
        for (var i = 0; i < word.length; i++) {
            if (word.substr(i,1) == " ") {
                count++;
            }
        }
        return count;
    }
    
    this.loadChildren = function() {
    	var wordLayoutOrigin = new Point().init(this.canvas.width * 0.025, this.canvas.height * 0.40);
    	var wordLayoutBounds = new Bounds().init(wordLayoutOrigin, this.canvas.width * 0.95, this.canvas.height * 0.25);
    	var wordLayout = new WordLayout().init(this.problem.displayText, wordLayoutBounds);
    	this.gameObjects.push(wordLayout);
    	this.wordLayout = wordLayout;
    }

    this.checkPartialSolution = function() {
        var index = 0;
        var word = this.problem.word;
        var current = this.wordLayout.getDisplayedWord();
        for (var i = 0; i < word.length; ++i) {
            var currentLetter = current.substr(index,1);
            var problemLetter = word.substr(i,1);
            if (!(currentLetter == problemLetter) && !(problemLetter == " ")) {
                return false;
            } else if (!(problemLetter == " ") || (problemLetter == " " && currentLetter == " ")) {
                ++index;
            }
        }
        return true;
    }
    
    this.onClickInternal = function(/**Point*/ clickPoint) {
    	var answer = this.wordLayout.getCutIndexForPoint(clickPoint);
    	this.moving = false;
    	if (answer != null) {
    		this.wordLayout.addSpaceAtIndex(answer);
    		this.draw();
    		
            this.moving = false;
    		this.checkForSuccess();
    	}
    }
    
    this.onMouseDownInternal = function(/**Point*/ clickPoint) {
    	var touchedTile = this.wordLayout.getClickedTile(clickPoint);
    	if (touchedTile != null) {
    		this.initialClickPointDifference = clickPoint;
    		
    	}
    }
    
    this.onMouseMoveInternal = function(/**Point*/ clickPoint) {
    	
    }
    
    this.onMouseUpInternal = function(/**Point*/ point) {
    	if (this.moving && this.initialClickPointDifference) {
    		this.moving = false;
    		var midPoint = new Point().init(this.initialClickPointDifference.x + (point.x - this.initialClickPointDifference.x)/2, this.initialClickPointDifference.y + (point.y - this.initialClickPointDifference.y)/2);
    		var answer = this.wordLayout.getCutIndexForPoint(midPoint);
    		if (answer != null) {
    			this.wordLayout.addSpaceAtIndex(answer);
    			this.draw();
    			
    			this.checkForSuccess();
                this.initialClickPointDifference = null;
    		}
    	}
    }

}
SeparationGameController.prototype = new GameController;
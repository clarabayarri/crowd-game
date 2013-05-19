/**
    A controller for the game
    @class
*/
function GameController()
{
    /** An array of game objects 
        @type Array
    */
    this.gameObjects = new Array();
    /** A reference to the ApplicationManager instance  
        @type ApplicationManager
    */
    this.applicationManager = null;
    /** A reference to the canvas element  
        @type HTMLCanvasElement
    */
    this.canvas = null;
    this.canvasOffsetX = 0;
    this.canvasOffsetY = 0;
    /** A reference to the 2D context of the canvas element
        @type CanvasRenderingContext2D
    */
    this.context2D = null;
    /** A reference to the in-memory canvas used as a back buffer 
        @type HTMLCanvasElement
    */
    this.backBuffer = null;
    /** A reference to the backbuffer 2D context 
        @type CanvasRenderingContext2D
    */
    this.backBufferContext2D = null;
    /** The problem to be displayed
    	@type Problem
    */
    
    this.touchEnabled = true;
    
    this.problem = null;
    
    this.attempts = 0;

    this.numMoves = 0;
    this.maxMovesAllowed = 1;
    
    this.wrongAnswers = new Array();
    
    this.initialClickPointDifference = new Point();
    this.moving = false;
    
    this.wordLayout = null;
    this.answerLayout = null;
    this.movingTile = null;
    this.dialog = null;

    this.instructions = null;

    /**
        Initialises this object
        @return A reference to the initialised object
    */
    this.startupGameController = function(/**ApplicationManager*/applicationManager, /**Problem*/ problem)
    {
        // get references to the canvas elements and their 2D contexts
        this.canvas = document.getElementById('game-canvas');
        this.context2D = this.canvas.getContext('2d');
        this.backBuffer = document.createElement('canvas');
        this.backBuffer.width = this.canvas.width;
        this.backBuffer.height = this.canvas.height;
        this.backBufferContext2D = this.backBuffer.getContext('2d');
        this.problem = problem;
        this.applicationManager = applicationManager;
        
        this.canvasOffsetX = this.canvas.getBoundingClientRect().left;
        this.canvasOffsetY = this.canvas.getBoundingClientRect().top;
         
        this.gameObjects = new Array();
        this.attempts = 0;
        this.wrongAnswers = new Array();

        if (this.problem != null && this.instructions != null) {
            this.loadInstructions();
        }
        
        return this;        
    }

    this.loadInstructions = function() {
        var instrOrigin = new Point().init(30, 30);
        var instrBounds = new Bounds().init(instrOrigin, 400, 50);
        var instr = new VisualButton().startupVisualButton(instrBounds);
        instr.text = this.instructions;
        this.gameObjects.push(instr);
    }
    
    
    /**
        The render loop
    */
    this.draw = function ()
    {
        // clear the drawing contexts
        this.backBufferContext2D.clearRect(0, 0, this.backBuffer.width, this.backBuffer.height);
        this.context2D.clearRect(0, 0, this.canvas.width, this.canvas.height);
        
        drawScrabbleBackground(this.backBufferContext2D, 0, 0, this.canvas.width, this.canvas.height); 
              
        // then draw the game objects
        for (x in this.gameObjects) {
            if (this.gameObjects[x].draw) {
           		this.gameObjects[x].draw(this.backBufferContext2D);
            }
        }
        
        // copy the back buffer to the displayed canvas
        this.context2D.drawImage(this.backBuffer, 0, 0);
    };
    
    this.checkForSuccess = function() {
        ++this.numMoves;
    	if (this.wordLayout.getDisplayedWord() == this.problem.word) {
    		this.success();
    	} else if (this.numMoves >= this.maxMovesAllowed || 
            !this.checkPartialSolution()) {
    		this.fail();
            this.numMoves = 0;
    	}
    }

    this.checkPartialSolution = function() {
        return true;
    }
    
    this.onClick = function(e) {
    	var clickPoint = this.getEventPosition(e);
    	
    	if (this.dialog) {
    		this.checkForContinue(clickPoint);
    	} else if (this.touchEnabled) {
    		this.onClickInternal(clickPoint);
    	}
    }
    
    this.onClickInternal = function(/**Point*/ clickPoint) {}
    
    this.onMouseDown = function(e) {
    	if (this.touchEnabled) {
    		var clickPoint = this.getEventPosition(e);
    		this.onMouseDownInternal(clickPoint);
    	}
    }
    
    this.onMouseDownInternal = function(/**Point*/ clickPoint) {}
    
    this.onMouseMove = function(e) {
    	if (this.touchEnabled) {
    		var clickPoint = this.getEventPosition(e);
    		this.onMouseMoveInternal(clickPoint);
    	}
    }
    
    this.onMouseMoveInternal = function(/**Point*/ clickPoint) {}
    
    this.onMouseUp = function(e) {
    	if (this.touchEnabled) {
    		var clickPoint = this.getEventPosition(e);
    		this.onMouseUpInternal(clickPoint);
    	}
    }
    
    this.onMouseUpInternal = function(/**Point*/ clickPoint) {}
    
    this.getEventPosition = function(e) {
    	var x = e.pageX - this.canvasOffsetX;
    	var y = e.pageY - this.canvasOffsetY;
    	return new Point().init(x, y);
    }
    
    this.checkForContinue = function(/**Point*/ clickPoint) {
    	if (this.dialog.onClick(clickPoint)) {
    		this.applicationManager.onContinue();
    	}
    }
    
    this.reload = function() {
    	this.reloadInternal();
    	this.touchEnabled = true;
    	if (this.wordLayout) {
    		this.wordLayout.loadOriginalWord();
    	}
    	if (this.answerLayout) {
    		this.answerLayout.showAllChildren();
    	}
    	//this.draw();
    }
    
    this.reloadInternal = function() {}
    
    this.success = function() {
    	this.touchEnabled = false;
    	this.applicationManager.onSuccess(this.attempts,this.wrongAnswers);
    	
    	var _this = this;
    	this.wordLayout.animateSuccess(function() {
    		_this.showSuccessDialog();
    	});
    };
    
    this.showSuccessDialog = function() {
    	var continueOrigin = new Point().init(0, 0);
    	var continueBounds = new Bounds().init(continueOrigin, this.canvas.width, this.canvas.height);
    	
    	this.dialog = new ContinueDialog().startupContinueDialog(this.problem.word, continueBounds);
    	this.gameObjects.push(this.dialog);
    }
    
    this.fail = function() {
    	var _this = this;
    	this.wrongAnswers.push(this.wordLayout.getDisplayedWord());
    	this.touchEnabled = false;
    	this.attempts += 1;
    	
    	var _this = this;
    	this.wordLayout.animateFail(function() {
    		_this.failAnimationEnded();
    		_this.reload();
    	});
    };
    
    this.failAnimationEnded = function() {
    	this.touchEnabled = true;
    }
}
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
    
    this.wrongAnswers = new Array();
    
    this.initialClickPointDifference = new Point();
    this.moving = false;
    
    this.wordLayout = null;
    this.answerLayout = null;
    this.movingTile = null;
    this.continueDialog = null;

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
        
        return this;        
    }
    
    
    /**
        The render loop
    */
    this.draw = function ()
    {
        // clear the drawing contexts
        this.backBufferContext2D.clearRect(0, 0, this.backBuffer.width, this.backBuffer.height);
        this.context2D.clearRect(0, 0, this.canvas.width, this.canvas.height);
        
        this.drawBackground(this.context2D);
              
        // then draw the game objects
        for (x in this.gameObjects)
        {
            if (this.gameObjects[x].draw)
            {
                this.gameObjects[x].draw(this.backBufferContext2D);
            }
        }
        
        // copy the back buffer to the displayed canvas
        this.context2D.drawImage(this.backBuffer, 0, 0);
    };
    
    this.drawBackground = function(/**CanvasRenderingContext2D*/ context) {
    	// draw background
    	this.backBufferContext2D.fillStyle = COLOR_GREEN1;
    	this.backBufferContext2D.fillRect(0, 0, this.canvas.width, this.canvas.height);
    	
    	// draw lines
    	var numSquaresX = 10;
    	var lineWidth = this.canvas.width/numSquaresX;
    	var offsetX = lineWidth / 2;
    	var numSquaresY = Math.floor(this.canvas.height / lineWidth);
    	var offsetY = (this.canvas.height - (numSquaresY * lineWidth)) / 2;
    	
    	// Draw colored tiles
    	this.backBufferContext2D.fillStyle = COLOR_BLUE1;
    	for (var i = -1; i < numSquaresX; ++i) {
    		for (var j = -1; j <= numSquaresY; ++j) {
    			if ((i == j+1) || ((i + j) == numSquaresX - 3)) {
    				this.backBufferContext2D.fillRect(offsetX + i*lineWidth, offsetY + j*lineWidth, lineWidth, lineWidth);
    			}
    		}
    	}
    	
    	// Draw vertical lines
    	this.backBufferContext2D.strokeStyle = COLOR_DARK_GREEN1;
    	this.backBufferContext2D.lineWidth = 3;
    	for (var i = 0; i < numSquaresX; ++i) {
    		this.backBufferContext2D.beginPath();
    		this.backBufferContext2D.moveTo(offsetX + i*lineWidth, 0);
    		this.backBufferContext2D.lineTo(offsetX + i*lineWidth, this.canvas.height);
    		this.backBufferContext2D.stroke();
    	}
    	
    	// Draw horizontal lines
    	for (var i = 0; i <= numSquaresY; ++i) {
    		this.backBufferContext2D.beginPath();
    		this.backBufferContext2D.moveTo(0, offsetY + i*lineWidth);
    		this.backBufferContext2D.lineTo(this.canvas.width, offsetY + i*lineWidth);
    		this.backBufferContext2D.stroke();
    	}
    	
    }
    
    this.checkForSuccess = function() {
    	if (this.wordLayout.getDisplayedWord() == this.problem.word) {
    		this.success();
    	} else {
    		this.fail();
    	}
    }
    
    this.onClick = function(e) {
    	var clickPoint = this.getEventPosition(e);
    	
    	if (this.continueDialog) {
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
    	if (this.continueDialog.onClick(clickPoint)) {
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
    	
    	this.continueDialog = new ContinueDialog().init(this.problem.word, continueBounds);
    	this.gameObjects.push(this.continueDialog);
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
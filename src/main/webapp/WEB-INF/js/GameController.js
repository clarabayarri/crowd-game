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
    this.problem = null;
    
    this.attempts = 0;
    
    this.wrongAnswers = new Array();
    
    this.initialClickPointDifference = new Point();
    this.moving = false;
    
    this.wordLayout = null;
    this.answerLayout = null;
    this.movingTile = null;

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
        
        // draw background
        
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
    
    this.checkForSuccess = function() {
    	if (this.wordLayout.getDisplayedWord() == this.problem.word) {
    		this.success();
    	} else {
    		this.fail();
    	}
    }
    
    this.getEventPosition = function(e) {
    	var x = e.pageX - this.canvas.getBoundingClientRect().left;
    	var y = e.pageY - this.canvas.getBoundingClientRect().top;
    	return new Point().init(x, y);
    }
    
    this.reload = function() {
    	this.reloadInternal();
    	if (this.wordLayout) {
    		this.wordLayout.loadOriginalWord();
    	}
    	if (this.answerLayout) {
    		this.answerLayout.showAllChildren();
    	}
    	this.draw();
    }
    
    this.reloadInternal = function() {}
    
    this.success = function() {
    	this.applicationManager.onSuccess(this.attempts,this.wrongAnswers);
    	this.context2D.lineWidth=1;
    	this.context2D.fillStyle="#000000";
    	this.context2D.lineStyle="#000000";
    	this.context2D.font="bold 66px sans-serif";
    	this.context2D.textAlign = "center";
    	this.context2D.textBaseline = "middle";
    	this.context2D.fillText("BIEN!", this.canvas.width / 2, this.canvas.height / 2);
    };
    
    this.fail = function() {
    	var _this = this;
    	this.wrongAnswers.push(this.wordLayout.getDisplayedWord());
    	setTimeout(function() {_this.reload();}, 1000);
    	this.attempts += 1;
    	
    };
}
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
        
        this.loadChildren();
        
        this.draw();
        
        return this;        
    }
    
    this.loadChildren = function() {
    	this.gameObjects = new Array();
    	this.attempts = 0;
    	this.wrongAnswers = new Array();
    	
    	var wordLayoutOrigin = new Point().init(this.canvas.width * 0.05, this.canvas.height * 0.20);
    	var wordLayoutBounds = new Bounds().init(wordLayoutOrigin, this.canvas.width * 0.9, this.canvas.height * 0.25);
    	var wordLayout = new WordLayout().init(this.problem.displayText, wordLayoutBounds);
    	this.gameObjects.push(wordLayout);
    	
    	var answerLayoutOrigin = new Point().init(this.canvas.width* 0.15, this.canvas.height * 0.6);
    	var answerLayoutBounds = new Bounds().init(answerLayoutOrigin, this.canvas.width * 0.7, this.canvas.height * 0.2);
    	var answerLayout = new AnswerLayout().init(this.problem.displayAnswers, answerLayoutBounds);
    	this.gameObjects.push(answerLayout);
    }
    
    
    /**
        The render loop
    */
    this.draw = function ()
    {
        // clear the drawing contexts
        this.backBufferContext2D.clearRect(0, 0, this.backBuffer.width, this.backBuffer.height);
        this.context2D.clearRect(0, 0, this.canvas.width, this.canvas.height);
        
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
    
    this.onClick = function(e) {
    	var x = e.pageX - this.canvas.getBoundingClientRect().left;
    	var y = e.pageY - this.canvas.getBoundingClientRect().toprecision;
    	var answer = this.gameObjects[1].getClickedAnswer(x,y);
    	if (answer != null) {
    		this.gameObjects[0].setChosenAnswer(answer);
    		this.draw();
    		
    		if (this.gameObjects[0].getDisplayedWord() == this.problem.word) {
    			this.success();
    		} else {
    			this.fail();
    		}
    	}
    }
    
    this.reload = function() {
    	this.gameObjects[0].loadOriginalWord();
    	this.draw();
    }
    
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
    	this.wrongAnswers.push(this.gameObjects[0].getDisplayedWord());
    	setTimeout(function() {_this.reload();}, 1000);
    	this.attempts += 1;
    };
}
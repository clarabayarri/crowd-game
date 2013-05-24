/**
    The ApplicationManager is used to manage the application itself.
    @class
*/
function ApplicationManager()
{
	
	this.gameController = new GameController();
	
	this.problem = null;
	
	this.startTime = null;
	
	this.continue = false;

    this.newScore = 0;

    this.loopStarted = false;

    this.canvasOrigin = null;
    
    
	this.findProblem = function() {
		var _this = this;
        this.getRequest("/game/problem",function(req) {_this.loadProblem(req);});
	};
	
	this.loadProblem = function(req) {
        this.problem = req;
		
		if (this.continue) this.loadGameController();
	};
	
	this.loadGameController = function() {
		if (this.problem.type == 'insertion1') {
			this.gameController = new InsertionGameController().startupInsertionGameController(this, this.problem);
		} else if (this.problem.type == 'omission') {
			this.gameController = new OmissionGameController().startupOmissionGameController(this, this.problem);
		} else if (this.problem.type == 'substitution') {
			this.gameController = new SubstitutionGameController().startupSubstitutionGameController(this, this.problem);
		} else if (this.problem.type == 'derivation') {
			this.gameController = new DerivationGameController().startupDerivationGameController(this, this.problem);
		} else if (this.problem.type == 'separation') {
			this.gameController = new SeparationGameController().startupSeparationGameController(this, this.problem);
		} else if (this.problem.type == 'insertion') {
			this.gameController = new HardInsertionGameController().startupHardInsertionGameController(this, this.problem);
		} else if (this.problem.type == 'transposition') {
			this.gameController = new TranspositionGameController().startupTranspositionGameController(this, this.problem);
		}
		
    if (!this.loopStarted) {
        this.loopStarted = true;
        this.animLoop();        
    }

		this.continue = false;
		this.startTime = new Date().getTime();
	}

    this.loadPause = function() {
        this.gameController = new PausedGameController().startupPausedGameController(this);
        this.gameController.draw();
    }

    this.loadEmpty = function() {
        this.gameController = new EmptyGameController().startupEmptyGameController(this);
    }
	
	
    /**
        Initialises this object
        @return A reference to the initialised object
    */
    this.startupApplicationManager = function()
    {
    	var canvas = document.getElementById('game-canvas');
    	var _this = this;
    	canvas.addEventListener('click', function(e) {_this.onClick(e);}, false);
    	canvas.addEventListener('mousedown', function(e) {_this.onMouseDown(e);}, false);
    	canvas.addEventListener('mousemove', function(e) {_this.onMouseMove(e);}, false);
    	canvas.addEventListener('mouseup', function(e) {_this.onMouseUp(e);}, false);
        this.loadPause();
        this.findProblem();
        this.canvasOrigin = findPos(canvas);
        return this;
    }

    function findPos(obj) {
        var curleft = 0, curtop = 0;
        if (obj.offsetParent) {
            do {
                curleft += obj.offsetLeft;
                curtop += obj.offsetTop;
            } while (obj = obj.offsetParent);
            return new Point().init(curleft, curtop);
        }
        return undefined;
    }
    
    this.onClick = function(e) {
    	if (this.gameController != null) {
    		this.gameController.onClick(e);
    	}
    }
    
    this.onMouseDown = function(e) {
    	if (this.gameController != null) {
    		this.gameController.onMouseDown(e);
    	}
    }
    
    this.onMouseMove = function(e) {
    	if (this.gameController != null) {
    		this.gameController.onMouseMove(e);
    	}
    }
    
    this.onMouseUp = function(e) {
    	if (this.gameController != null) {
    		this.gameController.onMouseUp(e);
    	}
    }
    
    this.onSuccess = function(/**Number*/intents, /**Array*/answers) {
    	var time = (new Date().getTime()) - this.startTime;
    	this.sendResults(time, intents, answers);
    	this.problem = null;
    	
    	var _this = this;
    	setTimeout(function() {
            _this.findProblem();
        }, 2000);
    }

    this.onSkip = function() {
        this.problem = null;
        this.continue = true;
        this.loadEmpty();
        this.findProblem();
    }
    
    this.onContinue = function() {
    	this.continue = true;
        if (this.newScore > 0) {
            $('#score').text("" + this.newScore + " puntos");
        }
        if (this.problem) {
    		this.loadGameController();
    	} else {
    		this.loadEmpty();
    	}
    }
    
    this.sendResults = function(/**Number*/time, /**Number*/intents, /**Array*/answers) {
    	var result = new Object();
    	result.taskId = this.problem.taskId;
        result.batchId = this.problem.batchId;
    	result.timeSpent = time;
    	result.failedAttempts = intents;
    	result.wrongAnswers = answers;
    	var coded = JSON.stringify(result);
    	
        var _this = this;
    	postRequest(coded, "/game/results", function(score) {
            _this.newScore = score;
        });
    }

    this.getRequest = function(reqUri, callback) {
        $.getJSON(reqUri, callback);
    }
    
    function postRequest(contents, reqUri, callback) {
    	$.ajax({
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            'type': 'POST',
            'url': reqUri,
            'data': contents,
            'dataType': 'json',
            'success': callback
        });
    }
    
    // shim layer with setTimeout fallback
    window.requestAnimFrame = (function(){
        return  window.requestAnimationFrame       ||
            window.webkitRequestAnimationFrame ||
            window.mozRequestAnimationFrame    ||
            function( callback ){
                window.setTimeout(callback, 1000 / 30);
            };
    })();
    
    
    this.animLoop = function(){
    	this.gameController.draw();
        var _this = this;
      	requestAnimFrame(function(){
      		_this.animLoop();
      	});
    };
    
}
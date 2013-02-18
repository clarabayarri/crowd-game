/**
    The ApplicationManager is used to manage the application itself.
    @class
*/
function ApplicationManager()
{
	
	this.gameController = new GameController();
	
	
	this.problem = null;
	
	this.startTime = null;
	
    this.findProblemTemp = function() {
        var req = new Object();
        req.responseText = '{"id":299,"type":"insertion","word":"palabra","displayText":["p","a","l"," ","b","r","a"],"displayAnswers":["e","a","b","r"]}';
        
        this.loadProblem(req);
    };
    
    
	this.findProblem = function() {
		var _this = this;
        this.getRequest("http://desolate-inlet-9447.herokuapp.com/game/problem",function() {_this.loadProblem;});
	};
	
	this.loadProblem = function(req) {
        console.log(req.responseText);
		this.problem = JSON.parse(req.responseText);
		
		this.gameController.startupGameController(this, this.problem);
		console.log("Start Game Controller");
		this.startTime = new Date().getTime();
	};
	
	
    /**
        Initialises this object
        @return A reference to the initialised object
    */
    this.startupApplicationManager = function()
    {
        this.findProblem();
        return this;
    }
    
    this.onClick = function(e) {
    	if (this.gameController != null) {
    		this.gameController.onClick(e);
    	}
    }
    
    this.onSuccess = function(/**Number*/intents, /**Array*/answers) {
    	var time = (new Date().getTime()) - this.startTime;
    	this.sendResults(time, intents, answers);
    	
    	var _this = this;
    	setTimeout(function() {_this.findProblemTemp();}, 2000);
    }
    
    this.sendResults = function(/**Number*/time, /**Number*/intents, /**Array*/answers) {
    	var result = new Object();
    	result.id = this.problem.id;
    	result.timeSpent = time;
    	result.failedAttempts = intents;
    	result.wrongAnswers = answers;
    	var coded = JSON.stringify(result);
    	
    	postRequest(result, "http://desolate-inlet-9447.herokuapp.com/game/results", null);
    }

    this.getRequest = function(reqUri, callback) {
        var req = new XMLHttpRequest();
        req.open("GET", reqUri, true);
    
        req.onload = function() {
            if (callback) {
                try {
                    callback(req);
                } catch(e) {
                    throw 'Req failed:\n' + reqUri + '\nException: ' + e + '\n';
                }
            }
        };
    
        req.send();
    }
    
    function postRequest(contents, reqUri, callback) {
    	var req = new XMLHttpRequest();
    	req.open("POST", reqUri, true);
    	
    	req.onload = function() {
    		if (callback) {
    			try {
    				callback(req);
    			} catch(e) {
    				throw 'Req failed:\n' + reqUri + '\nException: ' + e + '\n';
    			}
    		}
    	};
    	
    	req.send(contents);
    }
    
}
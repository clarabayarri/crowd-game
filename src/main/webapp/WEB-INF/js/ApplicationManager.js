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
        //req.responseText = '{"id":299,"type":"insertion","word":"palabra","displayText":["p","a","l"," ","b","r","a"],"displayAnswers":["a","i","o","u"]}';
        //req.responseText = '{"id":299,"type":"omission","word":"palabra","displayText":["p","a","l","a","r","b","r","a"],"displayAnswers":[]}';
        //req.responseText = '{"id":299,"type":"substitution","word":"palabra","displayText":["p","a","l","e","b","r","a"],"displayAnswers":["a","o","u","i"]}';
        //req.responseText = '{"id":299,"type":"derivation","word":"palabra","displayText":["p","a","l","a"],"displayAnswers":["bra","bre","es","ria"]}';
        req.responseText = '{"id":299,"type":"separation","word":"no ves","displayText":["n","o","v","e","s"],"displayAnswers":[]}';
        this.loadProblem(req);
    };
    
    
	this.findProblem = function() {
		var _this = this;
        this.getRequest("http://desolate-inlet-9447.herokuapp.com/game/problem",function(req) {_this.loadProblem(req);});
	};
	
	this.loadProblem = function(req) {
        this.problem = JSON.parse(req.responseText);
		
		if (this.problem.type == 'insertion') {
			this.gameController = new InsertionGameController().startupInsertionGameController(this, this.problem);
		} else if (this.problem.type == 'omission') {
			this.gameController = new OmissionGameController().startupOmissionGameController(this, this.problem);
		} else if (this.problem.type == 'substitution') {
			this.gameController = new SubstitutionGameController().startupSubstitutionGameController(this, this.problem);
		} else if (this.problem.type == 'derivation') {
			this.gameController = new DerivationGameController().startupDerivationGameController(this, this.problem);
		} else if (this.problem.type == 'separation') {
			this.gameController = new SeparationGameController().startupSeparationGameController(this, this.problem);
		}
		
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
    	
    	var _this = this;
    	setTimeout(function() {_this.findProblem();}, 2000);
    }
    
    this.sendResults = function(/**Number*/time, /**Number*/intents, /**Array*/answers) {
    	var result = new Object();
    	result.id = this.problem.id;
    	result.timeSpent = time;
    	result.failedAttempts = intents;
    	result.wrongAnswers = answers;
    	var coded = JSON.stringify(result);
    	
    	postRequest(coded, "http://desolate-inlet-9447.herokuapp.com/game/results", null);
    	console.log(this.problem.id);
    	console.log(coded);
    }

    this.getRequest = function(reqUri, callback) {
        var req = new XMLHttpRequest();
        req.open("GET", reqUri, true);
    	//req.open("GET", "task.php", true);
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
    	$.ajax({
    	    type: "POST",
    	    url: reqUri,
    	    data: contents,
    	    contentType: "application/json",      
    	    error: function(xhr, status, error) { 
    	        console.log("Error processing your request: \n" + status + " : " + error);
    	    },
    	    success: function(response){
    	        if (callback) {
    	        	callback(response);
    	        }
    	    }
    	});
    }
    
}
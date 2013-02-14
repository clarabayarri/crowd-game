<script type="text/javascript">

setup = function() {
	findProblem();
};

findProblem = function() {
	request("http://desolate-inlet-9447.herokuapp.com/game/problem",loadProblem);
};

loadProblem = function(req) {
	var problem = JSON.parse(req.responseText);
	var paragraph = document.createElement("p");
	paragraph.textContent = req.responseText;
	document.getElementById("game-container").appendChild(paragraph);
};

function request(reqUri, callback) {
	var caller = request.caller;
	
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

setup();

</script>
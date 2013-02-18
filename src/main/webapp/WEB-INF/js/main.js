
var applicationManager = null;


$('#game-canvas').click(function(e)
{
	// Mouse down location
	applicationManager.onClick(e);
});

function init() {
	applicationManager = new ApplicationManager().startupApplicationManager();
}

// The entry point of the application is set to the init function
window.onload = init;

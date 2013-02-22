
var applicationManager = null;


$('#game-canvas').click(function(e)
{
	applicationManager.onClick(e);
});

$('#game-canvas').mousedown(function(e)
{
	applicationManager.onMouseDown(e);
});

$('#game-canvas').mousemove(function(e)
{
	applicationManager.onMouseMove(e);
});

$('#game-canvas').mouseup(function(e)
{
	applicationManager.onMouseUp(e);
});

function init() {
	applicationManager = new ApplicationManager().startupApplicationManager();
}

// The entry point of the application is set to the init function
window.onload = init;

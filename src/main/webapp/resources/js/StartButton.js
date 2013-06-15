/**
    A continue button
*/
function StartButton()
{
    this.text = "empezar";
    
    this.startupStartButton = function(/**Bounds*/ bounds) {
    	this.startupVisualButton(bounds);
    	
    	return this;
    }
}
StartButton.prototype = new VisualButton;
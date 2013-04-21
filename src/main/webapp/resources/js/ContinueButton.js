/**
    A continue button
*/
function ContinueButton()
{
    this.text = "continuar";
    
    this.startupContinueButton = function(/**Bounds*/ bounds) {
    	this.startupVisualButton(bounds);
    	
    	return this;
    }
}
ContinueButton.prototype = new VisualButton;
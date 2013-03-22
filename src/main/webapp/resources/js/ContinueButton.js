/**
    A continue button
*/
function ContinueButton()
{
    this.text = "continuar";
    
    this.startupContinueButton = function(/**Bounds*/ bounds) {
    	this.startupGameObject(bounds);
    	
    	return this;
    }
    
    /**
        The render loop
    */
    this.draw = function (/**CanvasRenderingContext2D*/ context)
    {
    	if (this.visible) {
    		// draw tile
    		drawRibbon(context, this.bounds.origin.x, this.bounds.origin.y, this.bounds.width, this.bounds.height);
    		
    		// draw text
    		var center = this.bounds.center();
    		drawText(context, this.text, center.x, center.y, FONT_BOLD_24, COLOR_WHITE);
    	}
        
    };
}
ContinueButton.prototype = new GameObject;
/**
    A loading signal
*/
function LoadingSignal()
{
    this.text = "loading";
    
    this.bounceDistance = 0;
    this.currentDistance = 0;
    
    this.startupLoadingSignal = function(/**Bounds*/ bounds) {
    	this.startupGameObject(bounds);
    	
    	this.bounceDistance = this.bounds.width * 0.1;
    	
    	return this;
    }
    
    /**
        The render loop
    */
    this.draw = function (/**CanvasRenderingContext2D*/ context)
    {
    	if (this.visible) {
    		var nextDistance = this.currentDistance;
    		if (nextDistance > this.bounceDistance / 2) {
    			nextDistance = this.bounceDistance - nextDistance;
    		}
    		// draw tile
    		drawRibbon(context, this.bounds.origin.x + nextDistance, this.bounds.origin.y, this.bounds.width - 2 * nextDistance, this.bounds.height);
    		
    		// draw text
    		var center = this.bounds.center();
    		drawText(context, this.text, center.x, center.y, FONT_BOLD_24, COLOR_WHITE);
    		
    		this.currentDistance = this.currentDistance + 0.25;
    		if (this.currentDistance == this.bounceDistance) {
    			this.currentDistance = 0;
    		}
    	}
    };
}
LoadingSignal.prototype = new GameObject;
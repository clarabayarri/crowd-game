/**
    A manager for the letters in the word
*/
function StartDialog()
{   
    this.loadInternalViews = function() {
    	var center = this.tileBounds.center();
    	var buttonOrigin = new Point().init(center.x - 100, center.y - 25);
    	var buttonBounds = new Bounds().init(buttonOrigin, 200, 50);
    	this.button = new VisualButton().startupVisualButton(buttonBounds, "empezar");
    }
}

StartDialog.prototype = new Dialog;
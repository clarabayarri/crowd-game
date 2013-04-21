/**
    A manager for the letters in the word
*/
function ContinueDialog()
{
    this.word = null;
     
    this.startupContinueDialog = function(/**String*/ word, /**Bounds*/ bounds) {
    	this.startupDialog(bounds);
    	
    	this.word = word;

    	return this;
    }
    
    this.loadInternalViews = function() {
    	var center = this.tileBounds.center();
    	var buttonOrigin = new Point().init(center.x - 100, this.tileBounds.origin.y + this.tileBounds.height * 0.7);
    	var buttonBounds = new Bounds().init(buttonOrigin, 200, 50);
    	this.button = new ContinueButton().startupContinueButton(buttonBounds);
    }
    
    this.drawInternal = function (/**CanvasRenderingContext2D*/ context) {
    	// draw text
    	var center = this.tileBounds.center();
    	drawText(context, this.word, center.x, this.tileBounds.origin.y + this.tileBounds.height*0.3, FONT_BOLD_46, COLOR_DARK_GREY);
    }
}

ContinueDialog.prototype = new Dialog;
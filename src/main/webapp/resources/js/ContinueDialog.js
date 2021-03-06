/**
    A manager for the letters in the word
*/
function ContinueDialog()
{
    this.word = null;

    this.dialogCenter = null;
    this.dialogTextOriginY = null;
     
    this.startupContinueDialog = function(/**String*/ word, /**Bounds*/ bounds) {
    	this.startupDialog(bounds);
    	
    	this.word = word;

    	return this;
    }
    
    this.loadInternalViews = function() {
    	this.dialogCenter = this.tileBounds.center();
        this.dialogTextOriginY = this.tileBounds.origin.y + this.tileBounds.height*0.3;
    	var buttonOrigin = new Point().init(this.dialogCenter.x - 100, this.tileBounds.origin.y + this.tileBounds.height * 0.7);
    	var buttonBounds = new Bounds().init(buttonOrigin, 200, 50);
    	this.button = new VisualButton().startupVisualButton(buttonBounds, "continuar");
    }
    
    this.drawInternal = function (/**CanvasRenderingContext2D*/ context) {
    	// draw text
    	drawText(context, this.word, this.dialogCenter.x, this.dialogTextOriginY, FONT_BOLD_46, COLOR_DARK_GREY);
    }
}

ContinueDialog.prototype = new Dialog;
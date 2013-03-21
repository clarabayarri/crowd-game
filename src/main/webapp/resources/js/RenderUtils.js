var COLOR_GREEN1 = "#33ac7b";
var COLOR_GREEN2 = "rgba(13, 114, 98, 1.0)";
var COLOR_GREEN3 = "rgba(3, 85, 71, 1.0)";
var COLOR_BLUE1 = "#c0fcff";
var COLOR_DARK_GREEN1 = "#102f22";
var COLOR_RIBBON_BACKGROUND = "rgba(122, 146, 98, 1.0)";
var COLOR_RIBBON_LINES = "rgba(108, 135, 80, 0.7)";
var COLOR_RIBBON_GLOW = "rgba(149, 183, 128, 1.0)";
var COLOR_RIBBON_OUTLINE = "rgba(108, 135, 80, 1.0)";
var COLOR_SHADOW_COLOR = "#000000";

function roundRect(context, x, y, width, height, radius, fill, stroke) {
	context.beginPath();
	context.moveTo(x + radius, y);
	context.lineTo(x + width - radius, y);
	context.quadraticCurveTo(x + width, y, x + width, y + radius);
	context.lineTo(x + width, y + height - radius);
	context.quadraticCurveTo(x + width, y + height, x + width - radius, y + height);
	context.lineTo(x + radius, y + height);
	context.quadraticCurveTo(x, y + height, x, y + height - radius);
	context.lineTo(x, y + radius);
	context.quadraticCurveTo(x, y, x + radius, y);
	context.closePath();
	if (stroke) {
		context.stroke();
	}
	if (fill) {
		context.fill();
	}  
}

function shadow(context, blur) {
	context.shadowColor = COLOR_SHADOW_COLOR;
	context.shadowBlur = blur;
	context.shadowOffsetX = 2;
	context.shadowOffsetY = 2;
}

function eraseShadow(context) {
	context.shadowBlur = 0;
	context.shadowOffsetX = 0;
	context.shadowOffsetY = 0;
}

function drawRibbon(context, x, y, width, height) {
	var indent = width * 0.1;
	
	// background
	context.fillStyle = COLOR_RIBBON_BACKGROUND;
	ribbonShape(context, x, y, width, height, indent, true, false);
	
	ribbonLines(context, x+4, y+2, width-8, height-4, indent-2);
	
	context.strokeStyle = COLOR_RIBBON_GLOW;
	context.lineWidth = 3;
	ribbonShape(context, x+4, y+2, width-8, height-4, indent-2, false, true);
	
	//outline
	context.strokeStyle = COLOR_RIBBON_OUTLINE;
	context.lineWidth = 2;
	ribbonShape(context, x, y, width, height, indent, false, true);
}

function ribbonShape(context, x, y, width, height, indent, fill, stroke) {
	var center = new Point().init(x + width/2, y + height/2);
	context.beginPath();
	context.moveTo(x, 					y);
	context.lineTo(x + width, 			y);
	context.lineTo(x + width - indent, 	center.y);
	context.lineTo(x + width, 			y + height);
	context.lineTo(x, 					y + height);
	context.lineTo(x + indent, 			center.y);
	context.lineTo(x, 					y);
	context.closePath();
	if (stroke) {
	  context.stroke();
	}
	if (fill) {
	  context.fill();
	}
}

function ribbonLines(context, x, y, width, height, indent) {
	context.strokeStyle = COLOR_RIBBON_LINES;
	context.lineWidth = 2;
	context.beginPath();
	var centerY = y + height/2;
	for (var i = 0; i < width; i+=6) {
		if (i < indent) {
			context.moveTo(x + i, y);
			context.lineTo(x + i, y + (i * (height/2)/indent));
			context.moveTo(x + i, y + height - (i * (height/2)/indent));
			context.lineTo(x + i, y + height);
		} else if (i <= (width - indent)) {
			context.moveTo(x + i, y);
			context.lineTo(x + i, y + height);
		} else {
			context.moveTo(x + i, y);
			context.lineTo(x + i, y + ((width-i) * (height/2)/indent));
			context.moveTo(x + i, y + height - ((width-i) * (height/2)/indent));
			context.lineTo(x + i, y + height);
		}
		
	}
	context.stroke();
}
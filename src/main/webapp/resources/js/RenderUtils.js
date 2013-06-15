var COLOR_GREEN1 = "#2b7658";
var COLOR_GREEN2 = "rgba(13, 114, 98, 1.0)";
var COLOR_GREEN3 = "rgba(3, 85, 71, 1.0)";
var COLOR_BLUE1 = "#c0fcff";
var COLOR_DARK_GREEN1 = "#102f22";

var COLOR_RIBBON_BACKGROUND = "#749a66";
var COLOR_RIBBON_LINES = "#698d5d";
var COLOR_RIBBON_GLOW = "#86b172";
var COLOR_RIBBON_OUTLINE = "#699156";

var COLOR_SHADOW_COLOR = "#000000";
var COLOR_TILE = "rgba(248, 238, 207, 1.0)";
var COLOR_DARK_GREY = "#191919";
var COLOR_WHITE = "#ffffff";
var COLOR_BACKGROUND_SHADOW = "rgba(42, 42, 42, 0.5)";

var FONT_RIBBON_20 = "bold 20px Helvetica, Arial, sans-serif";
var FONT_RIBBON_24 = "bold 24px Helvetica, Arial, sans-serif";
var FONT_BOLD_46 = "bold 46px Helvetica, Arial, sans-serif";
var FONT_BOLD_24 = "bold 24px Helvetica, Arial, sans-serif";

var RIBBON_COLOR_BLUE = "ribbon-blue";
var COLOR_RIBBON_BACKGROUND_BLUE = "#84c7ef";
var COLOR_RIBBON_LINES_BLUE = "#69bde8";
var COLOR_RIBBON_GLOW_BLUE = "#3fa9f5";
var COLOR_RIBBON_OUTLINE_BLUE = "#335493";
var COLOR_RIBBON_TEXT_BLUE = COLOR_DARK_GREY;

var RIBBON_COLOR_PURPLE = "ribbon-purple";
var COLOR_RIBBON_BACKGROUND_PURPLE = "#858aed";
var COLOR_RIBBON_LINES_PURPLE = "#7688e2";
var COLOR_RIBBON_GLOW_PURPLE = "#546aed";
var COLOR_RIBBON_OUTLINE_PURPLE = "#333f91";
var COLOR_RIBBON_TEXT_PURPLE = COLOR_DARK_GREY;

var RIBBON_COLOR_PINK = "ribbon-pink";
var COLOR_RIBBON_BACKGROUND_PINK = "#d680b9";
var COLOR_RIBBON_LINES_PINK = "#c46cb7";
var COLOR_RIBBON_GLOW_PINK = "#d650b6";
var COLOR_RIBBON_OUTLINE_PINK = "#d30b90";
var COLOR_RIBBON_TEXT_PINK = COLOR_DARK_GREY;

var RIBBON_COLOR_RED = "ribbon-red";
var COLOR_RIBBON_BACKGROUND_RED = "#fa585c";
var COLOR_RIBBON_LINES_RED = "#e45155";
var COLOR_RIBBON_GLOW_RED = "#f77072";
var COLOR_RIBBON_OUTLINE_RED = "#c05e5f";
var COLOR_RIBBON_TEXT_RED = COLOR_DARK_GREY;

var RIBBON_COLOR_YELLOW = "ribbon-yellow";
var COLOR_RIBBON_BACKGROUND_YELLOW = "#f4e49d";
var COLOR_RIBBON_LINES_YELLOW = "#efdb89";
var COLOR_RIBBON_GLOW_YELLOW = "#f2d351";
var COLOR_RIBBON_OUTLINE_YELLOW = "#edba54";
var COLOR_RIBBON_TEXT_YELLOW = COLOR_DARK_GREY;

var RIBBON_COLOR_GREEN = "ribbon-yellow";
var COLOR_RIBBON_TEXT_GREEN = COLOR_DARK_GREY;

function drawScrabbleBackground(context, x, y, width, height) {
	// draw background
	context.fillStyle = COLOR_GREEN1;
	context.fillRect(0, 0, width, height);
	
	// draw lines
	var numSquaresX = 10;
	var lineWidth = width/numSquaresX;
	var offsetX = lineWidth / 2;
	var numSquaresY = Math.floor(height / lineWidth);
	var offsetY = (height - (numSquaresY * lineWidth)) / 2;
	
	// Draw colored tiles
	context.fillStyle = COLOR_BLUE1;
	for (var i = -1; i < numSquaresX; ++i) {
		for (var j = -1; j <= numSquaresY; ++j) {
			if ((i == j+1) || ((i + j) == numSquaresX - 3)) {
				context.fillRect(offsetX + i*lineWidth, offsetY + j*lineWidth, lineWidth, lineWidth);
			}
		}
	}
	
	// Draw vertical lines
	context.strokeStyle = COLOR_DARK_GREEN1;
	context.lineWidth = 3;
	for (var i = 0; i < numSquaresX; ++i) {
		context.beginPath();
		context.moveTo(offsetX + i*lineWidth, 0);
		context.lineTo(offsetX + i*lineWidth, height);
		context.stroke();
	}
	
	// Draw horizontal lines
	for (var i = 0; i <= numSquaresY; ++i) {
		context.beginPath();
		context.moveTo(0, offsetY + i*lineWidth);
		context.lineTo(width, offsetY + i*lineWidth);
		context.stroke();
	}
	
}

function drawTile(context, x, y, width, height) {
	var radius = height * 0.1;
	shadow(context, 5);
	drawRoundRect(context, x, y, width, height, radius, true, false, COLOR_TILE);
	eraseShadow(context);
}

function drawRoundRect(context, x, y, width, height, radius, fill, stroke, color) {
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
		context.strokeStyle = color;
		context.stroke();
	}
	if (fill) {
		context.fillStyle = color;
		context.fill();
	}  
}

function drawText(context, text, x, y, font, color) {
	context.lineWidth = 1;
	context.fillStyle = color;
	if (color === RIBBON_COLOR_BLUE) context.fillStyle = COLOR_RIBBON_TEXT_BLUE;
	if (color === RIBBON_COLOR_PURPLE) context.fillStyle = COLOR_RIBBON_TEXT_PURPLE;
	if (color === RIBBON_COLOR_PINK) context.fillStyle = COLOR_RIBBON_TEXT_PINK;
	if (color === RIBBON_COLOR_RED) context.fillStyle = COLOR_RIBBON_TEXT_RED;
	if (color === RIBBON_COLOR_YELLOW) context.fillStyle = COLOR_RIBBON_TEXT_YELLOW;
	if (color === RIBBON_COLOR_GREEN) context.fillStyle = COLOR_RIBBON_TEXT_GREEN;
	context.font = font;
	context.textAlign = "center";
	context.textBaseline = "middle";
	context.fillText(text, x, y);
}

function drawBackgroundShadow(context, x, y, width, height) {
	context.fillStyle = COLOR_BACKGROUND_SHADOW;
	shadow(context, 5);
	context.fillRect(x, y, width, height);
	eraseShadow(context);
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

function drawRibbon(context, x, y, width, height, color) {
	var indent = height*2/5;
	
	// background
	context.fillStyle = COLOR_RIBBON_BACKGROUND;
	if (color === RIBBON_COLOR_BLUE) context.fillStyle = COLOR_RIBBON_BACKGROUND_BLUE;
	if (color === RIBBON_COLOR_PURPLE) context.fillStyle = COLOR_RIBBON_BACKGROUND_PURPLE;
	if (color === RIBBON_COLOR_PINK) context.fillStyle = COLOR_RIBBON_BACKGROUND_PINK;
	if (color === RIBBON_COLOR_RED) context.fillStyle = COLOR_RIBBON_BACKGROUND_RED;
	if (color === RIBBON_COLOR_YELLOW) context.fillStyle = COLOR_RIBBON_BACKGROUND_YELLOW;
	ribbonShape(context, x, y, width, height, indent, true, false);
	
	context.strokeStyle = COLOR_RIBBON_LINES;
	if (color === RIBBON_COLOR_BLUE) context.strokeStyle = COLOR_RIBBON_LINES_BLUE;
	if (color === RIBBON_COLOR_PURPLE) context.strokeStyle = COLOR_RIBBON_LINES_PURPLE;
	if (color === RIBBON_COLOR_PINK) context.strokeStyle = COLOR_RIBBON_LINES_PINK;
	if (color === RIBBON_COLOR_RED) context.strokeStyle = COLOR_RIBBON_LINES_RED;
	if (color === RIBBON_COLOR_YELLOW) context.strokeStyle = COLOR_RIBBON_LINES_YELLOW;
	ribbonLines(context, x+4, y+2, width-8, height-4, indent-2);
	
	context.strokeStyle = COLOR_RIBBON_GLOW;
	if (color === RIBBON_COLOR_BLUE) context.strokeStyle = COLOR_RIBBON_GLOW_BLUE;
	if (color === RIBBON_COLOR_PURPLE) context.strokeStyle = COLOR_RIBBON_GLOW_PURPLE;
	if (color === RIBBON_COLOR_PINK) context.strokeStyle = COLOR_RIBBON_GLOW_PINK;
	if (color === RIBBON_COLOR_RED) context.strokeStyle = COLOR_RIBBON_GLOW_RED;
	if (color === RIBBON_COLOR_YELLOW) context.strokeStyle = COLOR_RIBBON_GLOW_YELLOW;
	context.lineWidth = 3;
	ribbonShape(context, x+4, y+2, width-8, height-4, indent-2, false, true);
	
	//outline
	context.strokeStyle = COLOR_RIBBON_OUTLINE;
	if (color === RIBBON_COLOR_BLUE) context.strokeStyle = COLOR_RIBBON_OUTLINE_BLUE;
	if (color === RIBBON_COLOR_PURPLE) context.strokeStyle = COLOR_RIBBON_OUTLINE_PURPLE;
	if (color === RIBBON_COLOR_PINK) context.strokeStyle = COLOR_RIBBON_OUTLINE_PINK;
	if (color === RIBBON_COLOR_RED) context.strokeStyle = COLOR_RIBBON_OUTLINE_RED;
	if (color === RIBBON_COLOR_YELLOW) context.strokeStyle = COLOR_RIBBON_OUTLINE_YELLOW;
	context.lineWidth = 2;
	ribbonShape(context, x, y, width, height, indent, false, true);
}

function drawArrow(context, x, y, width, height) {
	var indent = height*2/5;
	
	// background
	context.fillStyle = COLOR_RIBBON_BACKGROUND_RED;
	arrowShape(context, x, y, width, height, indent, true, false);
	
	arrowLines(context, x+4, y+2, width-4, height-4, indent-2);
	
	context.strokeStyle = COLOR_RIBBON_GLOW_RED;
	context.lineWidth = 3;
	arrowShape(context, x+4, y+2, width-4, height-4, indent-2, false, true);
	
	//outline
	context.strokeStyle = COLOR_RIBBON_OUTLINE_RED;
	context.lineWidth = 2;
	arrowShape(context, x, y, width, height, indent, false, true);
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

function arrowShape(context, x, y, width, height, indent, fill, stroke) {
	var center = new Point().init(x + width/2, y + height/2);
	context.beginPath();
	context.moveTo(x, 					y);
	context.lineTo(x + width, 			y);
	context.lineTo(x + width + indent, 	center.y);
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
	context.lineWidth = 2;
	context.beginPath();
	var centerY = y + height/2;
	var offset = (width/2) % 6;
	for (var i = offset; i < width + offset; i+=6) {
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

function arrowLines(context, x, y, width, height, indent) {
	context.strokeStyle = COLOR_RIBBON_LINES_RED;
	context.lineWidth = 2;
	context.beginPath();
	var centerY = y + height/2;
	var offset = (width/2) % 6;
	for (var i = offset; i < width + indent + offset; i+=6) {
		if (i < indent) {
			context.moveTo(x + i, y);
			context.lineTo(x + i, y + (i * (height/2)/indent));
			context.moveTo(x + i, y + height - (i * (height/2)/indent));
			context.lineTo(x + i, y + height);
		} else if (i <= width) {
			context.moveTo(x + i, y);
			context.lineTo(x + i, y + height);
		} else {
			context.moveTo(x + i, y + ((i-width) * (height/2)/indent));
			context.lineTo(x + i, y + height - ((i-width) * (height/2)/indent));
		}
		
	}
	context.stroke();
}
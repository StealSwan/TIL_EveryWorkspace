/**
 * 
 */

$(function(){
	
	$("#myimg").attr("src","../image/img1.png"); 
	
	//이벤트
	$("#myimg").hover(function(){
		$(this).attr("src","../image/img2.jpg");
	},function(){
		$(this).attr("src","../image/img1.png");
	});
});

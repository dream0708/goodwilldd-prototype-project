var g_step = [];
/**
 * 해당 페이지의 step의 갯수를 설정한다. 
 * @param number stepIndex
 */
function setStepView(stepIndex){
	g_step = [];
	if(!$.isNumeric(stepIndex)){
		console.log("parameter error // regStep("+ stepIndex +")");
		return;
	}
	for(var i = 0 ; i < stepIndex ; i++){
		g_step.push("step"+(i+1));
	}
}
/**
 * 현재 g_step에 설정되어 있는 step의 갯수와 id정보를 콘솔로그로 보여준다.
 */
function showAllStep(){
	var index = g_step.length; 
	for(var i = 0 ;  i < index ; i++){
		console.log(g_step.length+"/"+index+"     "+g_step.pop());
	}
}
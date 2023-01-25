//필수입력항목 입력여부확인 함수 
function emptyCheck(){
	var ok = true;
	$('.chk').each(function(){
		if( $.trim($(this).val())=='' ){
			
			
			var title =    $(this).attr('placeholder') ?  $(this).attr('placeholder') :$(this).attr('title') ;
			alert(title + '을 입력하세요');
			$(this).val('');
			$(this).focus();
			ok = false;
			return ok;
		}		
	});
	return ok;
} 
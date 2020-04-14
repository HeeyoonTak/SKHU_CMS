$(function() {
	$(".autosubmit").change(function(){
		$(this).parents("form").submit();
	})
})
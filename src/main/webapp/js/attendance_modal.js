var action = '';
var url = '';
var type = '';
var bno = 0;

$(document).ready(function() {

	// 출석체크 추가 버튼 클릭
	$("#createBtn").click(function() {
		action = 'create';
		type = 'POST'
		$("#modal-title").text("출석체크");
		$("#attendanceModal").modal();
	});

	// 출석체크 수정하기 날짜 클릭
	$(".attendance_check_table tr").click(function() {
		action = 'update';
		type = 'PUT';
		bno = this.value;

		/*// content 담기
		var row = $(this).parent().parent().parent();
		var tr = row.children();*/
		var tr = $(this);
		var td = tr.children();

		var userName = tr.eq(0).text();
		var check = tr.eq(1).text();

		$("#modal-title").text("출석체크 수정");

		$("#userName").val(userName);
		$("#check").val(check);

		$("#attendanceModal").modal();
	});

	// Modal의 Submit 버튼 클릭
	$("#modalSubmit").click(function() {

		if (action == 'create') {
			bno = 0;
			url = '/attendance';
		} else if (action == 'update') {
			url = '/attendance';
		}

		var data = {
			"bno" : bno,
			"userName" : $("#userName").val(),
			"check" : $("#check").val()
		};

		$.ajax({
			url : url,
			type : type,
			data : data
		})

		location.reload();
	});

});
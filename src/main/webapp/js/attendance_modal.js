var action = '';
var url = '';
var type = '';
var bno = 0;

$(document).ready(function() {

	// 출석체크 추가 버튼 클릭
	$("#createBtn").click(function() {
		action = 'update';
		$("#modal-title").text("출석체크");
		$("#attendanceModal").modal();
	});

	// 출석체크 수정하기 날짜 클릭
	$("#attendanceUpdate").click(function() {
		action = 'edit';
		type = 'GET';

		var tr = row.children();

		var userName = tr.eq(0).text();
		var check = tr.eq(1).text();

		$("#modal-title").text("출석체크 수정");

		$("#userName").val(userName);
		$("#check").val(check);

		$("#attendanceModal").modal();
	});

	// Modal의 Submit 버튼 클릭
	$("#modalSubmit").click(function() {

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
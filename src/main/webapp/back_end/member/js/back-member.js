$(document).ready(function() {
	$(".btn-enable").on("click", function(e) {
		e.preventDefault();
		var form = $(this).parents('form');
		Swal.fire({
			title: '確定要啟用嗎?',
			text: "按下確認鈕立即啟動所有會員功能!",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: '確定',
			cancelButtonText: '取消',
		}).then((result) => {
			if (result.isConfirmed) {
				Swal.fire(
					'啟用成功!',
					'已開啟所有會員功能',
					'success'
				).then(function() {
					form.submit(); // <--- submit form programmatically
				});
			};
		})
	})
})

$(document).ready(function() {
	$(".btn-disable").on("click", function(e) {
		e.preventDefault();
		var form = $(this).parents('form');
		Swal.fire({
			title: '確定要停權嗎?',
			text: "按下確認鈕立即停止所有會員功能!",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: '確定',
			cancelButtonText: '取消'
		}).then((result) => {
			if (result.isConfirmed) {
				Swal.fire(
					'停用成功!',
					'已關閉所有會員功能',
					'success'
				).then(function() {
					form.submit(); // <--- submit form programmatically
				});
			};
		})
	})
})







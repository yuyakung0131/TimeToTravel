$(document).ready(function() {
	$(".articleReportSubmit").on("click", function(e) {
		e.preventDefault();
		var form = $(this).parents('form')
		var optionValue = $(this).siblings('.articleReportReason')[0].value;
		var member_id = $(this).siblings('.member_id')[0].value;
		if (optionValue === '-1') {
			Swal.fire({
				icon: 'error',
				title: '檢舉失敗!',
				text: '請選擇檢舉理由!',
				confirmButtonText: '確定',
			})
		}
		else if (member_id === '' && optionValue !== '-1') {
			form.submit();
		}
		else if (member_id !== '' && optionValue !== '-1') {
			Swal.fire({
				title: '確定要檢舉嗎?',
				text: "按下確認即送出檢舉!",
				icon: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: '確定',
				cancelButtonText: '取消',
			}).then((result) => {
				if (result.isConfirmed) {
					Swal.fire(
						'檢舉成功!',
						'已把檢舉送出給管理員審核',
						'success'
					).then(function() {
						form.submit(); // <--- submit form programmatically
					})
				}
			})
		}
	})
})

$(document).ready(function() {
	$(".commentReportSubmit").on("click", function(e) {
		e.preventDefault();
		var form = $(this).parents('form')
		var optionValue = $(this).siblings('.commentReportReason')[0].value;
		var member_id = $(this).siblings('.member_id')[0].value;
		if (optionValue === '-1') {
			Swal.fire({
				icon: 'error',
				title: '檢舉失敗!',
				text: '請選擇檢舉理由!',
				confirmButtonText: '確定',
			})
		}
		else if (member_id === '' && optionValue !== '-1') {
			form.submit();
		}
		else if (member_id !== '' && optionValue !== '-1') {
			Swal.fire({
				title: '確定要檢舉嗎?',
				text: "按下確認即送出檢舉!",
				icon: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: '確定',
				cancelButtonText: '取消',
			}).then((result) => {
				if (result.isConfirmed) {
					Swal.fire(
						'檢舉成功!',
						'已把檢舉送出給管理員審核',
						'success'
					).then(function() {
						form.submit(); // <--- submit form programmatically
					})
				}
			})
		}
	})
})
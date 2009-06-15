
$().ready(function() {
	//.. validate the form when it is submitted
	$("#userForm").validate({
		rules: {
			name: {
				required: true,
				minlength: 6
			},
			login: {
				required: true,
				minlength: 6
			},
			email: {
				required: true,
				email: true
			},
			password: {
				required: true,
				minlength: 5
			},
			confirm_password: {
				required: true,
				minlength: 5,
				equalTo: "#password"
			},
			age: {
				 required: true,
			      number: true,
			      maxlength: 2

			},
			city: {
				required: true
			},
			state: {
				required: true
			},
			address: {
				required: true
			},
			country: {
				required: true
			}
		}
	});
	
	$("#bookmarkForm").validate({
		rules: {
			name: {
				required: true
			},
			url: {
				required: true,
				url: true
			},
			tags: {
				required: true
			},
			
			description: {
				required: true
			}
		}
	});
	
	$("#commentForm").validate({
		rules: {
			text: {
				required: true
			}
		}
	});
	
	$("#communityForm").validate({
		rules: {
			name: {
				required: true,
				minlength: 5
			},
			description: {
				required: true
			}
		}
	});
	
});

/**
 * JQuery for validation form
 */

$(document).ready(function() {
	
	// Create a method for checking whether username consist blankspaces
	jQuery.validator.addMethod("noWhiteSpace", function(value, element) {
		return value.indexOf(" ") < 0 && value != ""; 
	});
			
	// Create a method for letters only validation for first and the last name
	jQuery.validator.addMethod("lettersonly", function(value, element) {
		return this.optional(element) || /^[a-z]+$/i.test(value);
	});
	
	// Create a method for allowing only numbers
	jQuery.validator.addMethod("onlyNumbers", function(value, element) {
		return this.optional(element) || /^-?\d+$/.test(value);
	});
	
	// Validate email
	jQuery.validator.addMethod("email2", function(value, element, param) {
		return this.optional(element) || /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)*(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(value);
	}, jQuery.validator.messages.email);
	
	$('.form-validation').validate({
		rules: {
			userName: {
				required: true,
				minlength: 5,
				maxlength: 20,
 				noWhiteSpace: true
			},
			firstName: {
				required: true,
				minlength: 3,
				maxlength: 30,
				lettersonly: true
			},
			lastName: {
				required: true,
				minlength: 3,
				maxlength: 30,
				lettersonly: true
			},
			password: {
				required: true,
				minlength: 5,
				maxlength: 20
			},
			birthDate: {
				required: true,
				onlyNumbers: true
			},
			tel: {
				required: true,
				onlyNumbers: true
			},
			email: {
				email2: true
			}
		},
		messages: {
			userName: {
				required: "<p>The username is required!</p>",
				minlength: "<p>Length of the username has to be at least 5 characters long!</p>",
				maxlength: "<p>Length of the username can't exceed max limit of 20 characters!</p>",
 				noWhiteSpace: "<p>Username can't consist of blank spaces.</p>"
			},
			firstName: {
				required: "<p>The first name is required!</p>",
				minlength: "<p>Length of the first name has to be at least 3 characters long!</p>",
				maxlength: "<p>Length of the first name can't exceed max limit of 30 characters!</p>",
				lettersonly: "<p>First name must contain only letters</p>"
			},
			lastName: {
				required: "<p>The last name is required!</p>",
				minlength: "<p>Length of the last name has to be at least 3 characters long!</p>",
				maxlength: "<p>Length of the last name can't exceed max limit of 30 characters!</p>",
				lettersonly: "<p>Last name must contain only letters</p>"
			},
			password: {
				required: "<p>The password is required!</p>",
				minlength: "<p>Length of the password has to be at least 5 characters long!</p>",
				maxlength: "<p>Length of the password can't exceed max limit of 20 characters!</p>"
			},
			birthDate: {
				onlyNumbers: "<p>Only numbers allowed!</p>"
			},
			tel: {
				onlyNumbers: "<p>Only numbers allowed!</p>"
			},
			email: {
				email2: "<p>Please enter a valid email address.</p>"
			}
		}
	});
});
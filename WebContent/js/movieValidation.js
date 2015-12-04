/**
 * JQuery for validation form
 */

$(document).ready(function() {
			
	// Create a method for letters only validation for first and the last name
	jQuery.validator.addMethod("lettersonly", function(value, element) {
		return this.optional(element) || /^[a-z]+$/i.test(value);
	});
	
	// Create a method for allowing only numbers
	jQuery.validator.addMethod("onlyNumbers", function(value, element) {
		return this.optional(element) || /^-?\d+$/.test(value);
	});
	
	$('.form-validation').validate({
		rules: {
			movieTitle: {
				required: true,
				maxlength: 100
			},
			year: {
				required: true,
				minlength: 4,
				maxlength: 4,
				onlyNumbers: true
			},
			genre: {
				required: true,
				minlength: 3,
				maxlength: 30,
			},
			description: {
				required: true,
				minlength: 5,
				maxlength: 500
			},
			cast: {
				required: true,
				maxlength: 500
			},
			director: {
				required: true,
				maxlength: 100
			},
			photo: {
				required: true
			}
		},
		messages: {
			movieTitle: {
				required: "<p>The movie title is required!</p>",
				maxlength: "<p>Length of the title can't exceed max limit of 100 characters!</p>"
			},
			year: {
				required: "<p>The year of the movie is required!</p>",
				minlength: "<p>Length of the year has to be at least 4 characters long!</p>",
				maxlength: "<p>Length of the first name can't exceed max limit of 4 characters!</p>",
				onlyNumbers: "<p>The year must consist of only numbers!</p>"
			},
			genre: {
				required: "<p>The movie genre is required!</p>",
				minlength: "<p>Length of the genre has to be at least 3 characters long!</p>",
				maxlength: "<p>Length of the genre can't exceed max limit of 30 characters!</p>",
			},
			description: {
				required: "<p>Description of the movie is required!</p>",
				minlength: "<p>Length of the movie description has to be at least 5 characters long!</p>",
				maxlength: "<p>Length of the movie description can't exceed max limit of 500 characters!</p>"
			},
			cast: {
				required: "<p>Cast of the movie is required!</p>",
				maxlength: "<p>Length of the movie cast can't exceed max limit of 500 characters!</p>"
			},
			director: {
				required: "<p>Please enter the name of the movie director!</p>",
				maxlength: "<p>Length of the movie director can't exceed max limit of 100 characters!</p>"
			},
			photo: {
				required: "<p>Thumbnail of the movie is required!</p>"
			}
		}
	});
});
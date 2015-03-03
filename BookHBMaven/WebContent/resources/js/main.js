$(function() {
    var k = 1;
	$("#author_plus").on("click", function() {
		console.log("Clicked Plus");
		var authorId = ++k;
		//var row = $(".author_row").last().html();
		var rowTest = $(".author_row").last();
		//$("input", rowTest).attr("id", "author-" + authorId);
		//$("input", rowTest).attr("name", "author-" + authorId);
		
		console.log("%c rowTest is: ", 'color:blue', rowTest.html());

		
		//console.log("This is row", row);
		console.log("This is k", k);
		
		$("#add_table .author_row:last").after('<tr class="author_row">' + rowTest.html() + '</tr>');
		$("#add_table .author_row:last input").attr("id", "author-" + authorId);
		$("#add_table .author_row:last input").attr("name", "author-" + authorId);
		$("input#k").attr("value", authorId);
		
		
	});
	
	$("#author_minus").on("click", function() {
		console.log("Clicked Minus");
		var rowNum = $(".author_row");
		if (rowNum.size() > 1) {
			var authorId = --k;
			console.log("%c This is k", k, 'color:red');
			$("#add_table .author_row:last").remove();
			$("input#k").attr("value", authorId);
		}
	});
	
	
	
});
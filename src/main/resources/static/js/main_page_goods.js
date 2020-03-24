$(function() {



 		$('.badhon-tab li img').click(function(){

		var src = $(this).attr('src');
		

		$('.tab-pane img').attr("src", src);

});
 		
});


$(document).ready(function () {
    $('#list > li').click(function (event) {
        $(this).children("ul").slideToggle();
        event.stopPropagation();
    });
});


$("#watch").on("click", function(e) {
    var Data = {
        "Id" : "26"
    }


    $.ajax({
        type: "POST",
        url: "/main_page_goods?" + $.param(Data),
        dataType : 'json',
        contentType: 'application/json'
    });
});

/*
$( document ).ready(function() {

    // GET REQUEST
    $("#getAllCustomerId").click(function(event){
        event.preventDefault();
        ajaxGet();
    });

    // DO GET
    function ajaxGet(){
        $.ajax({
            type : "GET",
            url : window.location + "main_page_goods",
            success: function(result){
                if(result.status == "Done"){
                    $('#getResultDiv ul').empty();
                    var custList = "";
                    $.each(result.data, function(i, customer){
                        var customer = "- Customer with Id = " + i + ", firstname = " + customer.firstname + ", lastName = " + customer.lastname + "<br>";
                        $('#getResultDiv .list-group').append(customer)
                    });
                    console.log("Success: ", result);
                }else{
                    $("#getResultDiv").html("<strong>Error</strong>");
                    console.log("Fail: ", result);
                }
            },
            error : function(e) {
                $("#getResultDiv").html("<strong>Error</strong>");
                console.log("ERROR: ", e);
            }
        });
    }
})
*/








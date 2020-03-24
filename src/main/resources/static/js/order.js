


$(document).ready(function(){


$.ajax({
    type: "POST",
    contentType : 'application/json; charset=utf-8',
    dataType : "json",
    url: '/order',

    success :function(response){
        alert("I am in success");

        let select=" ";

        for(var i = 0 ;i<response.length;i++)
        {


            //response[i].date -- це дата впихнеш кудись її

            select += '' +
                '<li class="row all">\n' +
                '<span class="quantity minus">-</span><span class="quantity much">'+response[i].count+ '</span><span class="quantity plus">+</span>\n' +
                '<span class="itemName">' +'<span class="names" >' + response[i].goods.name + '</span ><span class="mode" >'+ response[i].goods.model + '</span > <span class="gg" >'+response[i].memory + '</span >GB'+ '</span>\n' +
                '<span class="popbtn">X</span>\n' +
                '<span class="price">' + response[i].price + '</span>\n' +
                '</li>';
            $('.goods').html(select);
        }

    },
    error:function(response,textStatus) {
        alert("I am in error ajax");



    }
});
});
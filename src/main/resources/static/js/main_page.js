$(function() {

    var slides = $('#slides .slide');
    var currentSlide = 0;
    var slideInterval = setInterval(nextSlide,3000);

    function nextSlide(){
        slides[currentSlide].className = 'slide';
        currentSlide = (currentSlide+1)%slides.length;
        slides[currentSlide].className = 'slide showing';

    }
});


$(function() {
    $('.badhon-tab li img').click(function(){
        var src = $(this).attr('src');
        $('.tab-pane img').attr("src", src);

    });
});

$(document).ready(function () {
    $('#list > li').click(function (event) {
        $(this).next().children("ul").slideToggle();
        event.stopPropagation();
    });
});

$(function() {

    $('#goods').click(function(){

        $("#list > li").children("ul").slideDown();
    });


    if (localStorage.getItem("basketGoods")===null){
        localStorage.setItem("basketGoods",'');
    }



    jQuery.post("is-user-auth",function(response) {
        if(response === 'yes') {


            $.ajax({
                type: "POST",
                contentType : 'application/json; charset=utf-8',
                dataType : "text",
                url: '/get-count-goods',

                success :function(response){

                  if (localStorage.getItem(basketGoods)!==''){
                      sel =  localStorage.getItem("basketGoods");

                      let count = sel.length;
                      var res = 0;

                      for(i=0; i < count; i++){

                          if(sel.charAt(i) == "-"){
                              res++;
                          }

                      }

                      let result = +response+res;


                      $('#countOfGoods').html(result);

                  }else {
                      $('#countOfGoods').html(response);
                  }

                },
                error:function(response,textStatus) {


                }
            });



        } else {

            sel =  localStorage.getItem("basketGoods");

            let count = sel.length;
            var res = 0;

            for(i=0; i < count; i++){

                if(sel.charAt(i) == "-"){
                    res++;
                }

            }

            $('#countOfGoods').html(res);

        }
    });










});


$(document).ready(function () {
    $('.phone').click(function (event) {
        var name = $(this).attr("id");
        var value = $(this).attr("value");
        getGoodsByModel(value, name);

    });
});

function getGoodsByModel(name, value ) {
    $.ajax({
        type: "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : "json",

        url: '/type='+value+'/model='+name,

        success :function(response){

            let models = response.allModels;//список моделей
            let memories = response.allMemories;
            let Photo = response.urlPhoto.replace(/["]/g, '').split(',');

            jQuery('button.memoryProduct').removeClass('checked');
            jQuery('button.memoryProduct').addClass('btn-success');

            for (var key in response.memoryAndPrice) {
                if (response.memoryAndPrice.hasOwnProperty(key)) {

                    $('#price').html(response.memoryAndPrice[key]+'$');
                    $('#name-model').html('<span class="names" >' + response.name + '</span ><span class="mode" >'+ response.model + '</span > <span class="gg" >'+key + '</span >GB');
                }}


            let select="";

            $('#shortDescription').html('***');

            $('#fullDescription').html('Full Description :'+response.fullDescription);
            $('#model').html('Model: ' + response.model);

            if (models!=null) {
                for (let i of models) {
                    select += '<button type="button" id="val" class="btn btn-success modelsProduct"  >' + i + '</button>';

                }


            $('.models').html(select);
            var lst = [];
            lst = document.querySelectorAll("#val");
            for (let i in models) {
                lst[i].id = models[i];
            }


        }

            select="";
            if (memories!=null) {
                for (let i of memories) {
                    select += '<button type="button" id="mem" class="btn btn-success memoryProduct"  >' + i + '</button>';

                }


                $('.memory').html(select);
                var lst = [];
                lst = document.querySelectorAll("#mem");
                for (let i in memories) {
                    lst[i].id = memories[i];
                }

            }

            var lst = [];
            lst = document.querySelectorAll("#goods_img");
            for (let i in Photo) {
                lst[i].src = Photo[i] ;

            }
            document.getElementById('photo').src=Photo[0];


        },
        error:function(response) {


        }
    });
}


$("div").on('click', '.memoryProduct', function (event) {
    var name = $(this).attr("id");
    var model = $('#model').html().toString().substr(7);
    $('#name-model .gg').html(name);

    getPriceByMemory(name,model);

    jQuery('button.memoryProduct').removeClass('checked');
    jQuery('button.memoryProduct').addClass('btn-success');
    jQuery(this).removeClass("btn-success");
    jQuery(this).addClass("checked");


});

$("div").on('click', '.modelsProduct', function (event) {
    var name = $(this).attr("id");


    getGoodsByModel(name);

    jQuery('button.modelsProduct').removeClass('checked');
    jQuery('button.modelsProduct').addClass('btn-success');
    jQuery(this).removeClass("btn-success");
    jQuery(this).addClass("checked");

});

function getPriceByMemory(memory,model) {

    $.ajax({
        type: "POST",
        contentType : 'application/json; charset=utf-8',
        dataType : "json",
        url: '/type=watch/model='+model+'/memory='+memory,

        success :function(response){

            $('#price').html(response + ' $');

        },
        error:function(response) {


        }
    });
}


$(function() {

    var countProducts;

    $('#32').click(function (event) {

        $('.goBack').remove();

        sel =  localStorage.getItem("basketGoods");//получаємо дані з корзини


        $('.goods').html(sel);//виводжу з локал стореджа на сторінку




        $('.header').css("display", "none");

        let select= $('.goods').html();

        select+= '' +
            '<li class="row all">\n' +
            '<span class="quantity minus">-</span><span class="quantity much">1</span><span class="quantity plus">+</span>\n' +
            '<span class="itemName">'+$('#name-model').html() +'</span>\n' +
            '<span class="popbtn">X</span>\n' +
            '<span class="price">'+$('#price').html() +'</span>\n' +
            '</li>';
        $('.goods').html(select);





        Plus(countProducts);
        Minus();
        Delete();
        howMuch();
        countOfItem();

        var basketGoods = document.getElementById("basketGoods").innerHTML;

        localStorage.setItem("basketGoods",select);








    });


});


$(function() {

    $('.Zam ').click(function(){

        $('#one').css("display", "none");
        $('#two').css("display", "none");

        $('.models,.memory').html('');



        $("#list > li").children("ul").slideUp();
        $('.goBack').remove();


        $('#God').val("");
        $('#Godname').val("");
        $('#Mode').val("");
        $('#Memory').val("");



        let name="";
        let mode="";
        let memory="";
        let  all="";
        $('.much').each(function(i,elem) {
            all+= $(this).html()+",";


        });

        $('#God').val(all.slice(0, -1));


        $(' .all .names').each(function(i,elem) {
            name+= $(this).html()+",";

        });
        console.log(name);

        $('#Godname').val(name.slice(0, -1));

        $('.all .mode').each(function(i,elem) {
            mode+= $(this).html()+",";

        });



        $('#Mode').val(mode.slice(0, -1));



        $('.all .gg').each(function(i,elem) {
            memory+= $(this).html()+",";

        });
        $('#Memory').val(memory.slice(0, -1));



/*
if ($('#Mode').val()!=="" && $('#Memory').val()!=="" &&  $('#Godname').val()!==""   && $('#God').val()!==""  &&  $('#Adress').val()!==""  && $('#Number').val()!=="")Х
*/

        var goods={
            model: $('#Mode').val(), //ось тут повставляй з інпутів
            memory: $('#Memory').val(),//і тут
            name: $('#Godname').val(),//і тут
            count:$('#God').val(),
            address:$('#Adress').val(),
            phone:$('#Number').val()
        }


        console.log(goods);

        $.ajax({
            type: "POST",
            contentType : 'application/json; charset=utf-8',

            dataType : "json",
            data:JSON.stringify(goods),
            url: '/create-order',

            success :function(response){


                $('#God').val("");
                $('#Godname').val("");
                $('#Mode').val("");
                $('#Memory').val("");
                countOfItem();

                localStorage.setItem("basketGoods",'');
            },
            error:function(response,textStatus) {

                $('#God').val("");
                $('#Godname').val("");
                $('#Mode').val("");
                $('#Memory').val("");
                countOfItem();




            }
        });





    });






});













// переходи

$(function() {

    $('#32').click(function(){


        $(".go").addClass('homess');
        $(".go").removeClass('kto');


        $('#two').css("display", "none");
        $('.koronovirys').css("display", "block");
        $("#list > li").children("ul").slideUp();

        $('head').append('<link rel="stylesheet" href="css/custom.css" type="text/css" />');



        $('.homess').click(function(){

            $('.koronovirys').css("display", "none");
            $('#two').css("display", "block");

            $('.header').css("display", "block");

            $("link[href*='css/custom.css']").remove();

            $("#list > li").children("ul").slideUp();
            $('.goBack').remove();
            Back();

            Plus();
            Minus();
            Delete();
            howMuch();


        });

      let select=" ";
            select += '' +
                '<li class="row all">\n' +
                '<span class="quantity minus">-</span><span class="quantity much">'+1+ '</span><span class="quantity plus">+</span>\n' +
                '<span class="itemName">' +$('#name-model').html() +'</span>\n' +
                '<span class="popbtn">X</span>\n' +
                '<span class="price">' + $('#price').html() + '</span>\n' +
                '</li>';
            $('.goods').html(select);











    Plus();
    Minus();
    Delete();
    howMuch();
    countOfItem();
    })

});


$(function() {

    $('.phone ').click(function(){

        $('#one').css("display", "none");
        $('.orde').css("display", "none");
        $('#two').css("display", "block");
        $("link[href*='css/order.css']").remove();
        $('head').append('<link rel="stylesheet" href="css/main1.css" type="text/css" />');
        $('.goBack').remove();

        $("#list > li").children("ul").slideUp();



        Back();




    });




});


$(function() {

    $('.home').click(function(){

        var src = $(".first").attr('src');

        $('.tab-pane img').attr("src", src);

        $('#two').css("display", "none");
        $('.orde').css("display", "none");
        $('#one').css("display", "block");

        $('.models,.memory').html('');
        $("link[href*='css/main1.css']").remove();
        $("link[href*='css/order.css']").remove();

        $("#list > li").children("ul").slideUp();
        $('.goBack').remove();



    });
});



$(function() {

    $('.or , .Zam ').click(function(){

        $('#one').css("display", "none");
        $('#two').css("display", "none");
        $('.koronovirys').css("display", "none");
        $('.orde').css("display", "block");

        $('.models,.memory').html('');
        $("link[href*='css/main1.css']").remove();

        $("link[href*='css/custom.css']").remove();

        $('head').append('<link rel="stylesheet" href="css/order.css" type="text/css" />');


        $("#list > li").children("ul").slideUp();
        $('.goBack').remove();


        $.ajax({
            type: "POST",
            contentType : 'application/json; charset=utf-8',
            dataType : "json",
            url: '/order',
            dateFormat: "yy-mm-dd",

            success :function(response){


                $('#God').val("");
                $('#Godname').val("");
                $('#Mode').val("");
                $('#Memory').val("");
                countOfItem();

                let select=" ";

                for(var i = 0 ;i<response.length;i++)
                {



                    select += '' +
                        '<li class="row all">\n' +
                        '</span><span class="quantity much">'+response[i].count+ '</span>\n' +
                        '<span class="itemName">' +'<span class="names" >' + response[i].goods.name + '</span ><span class="mode" >'+ response[i].goods.model + '</span > <span class="gg" >'+response[i].memory + '</span >GB'+ '</span>\n' +
                        '<span style="float: right;">'+ response[i].date+'</span>\n' +
                        '<span class="cent">' + response[i].price + '$</span>\n' +
                        '</li>';
                    $('.goo').html(select);
                    console.log(response[i].date);

                }

            },
            error:function(response,textStatus) {

                $('#God').val("");
                $('#Godname').val("");
                $('#Mode').val("");
                $('#Memory').val("");




            }
        });






    });
});







$(function() {

    $('.box_1').click(function (key){





        var countProducts;



        if($('#two').css('display') == "none"){


            $(".go").addClass('kto');
            $(".go").removeClass('homess');


            if (localStorage.getItem("basketGoods")==null){
             localStorage.setItem("basketGoods",'');

            }

            sel =  localStorage.getItem("basketGoods");//получаємо дані з корзини
            $('.goods').html(sel);//виводжу з локал стореджа на сторінку

            let name="";
            let mode="";
            let memory="";
            let  all="";

            $('#God').val("");
            $('#Godname').val("");
            $('#Mode').val("");
            $('#Memory').val("");

            $('.much').each(function(i,elem) {
                all+= $(this).html()+",";

            });

            $('#God').val(all.slice(0, -1));
            $(' .all .names').each(function(i,elem) {
                name+= $(this).html()+",";

            });
            $('#Godname').val(name.slice(0, -1));

            $('.all .mode').each(function(i,elem) {
                mode+= $(this).html()+",";
            });

            $('#Mode').val(mode.slice(0, -1));

            $('.all .gg').each(function(i,elem) {
                memory+= $(this).html()+",";
            });
            $('#Memory').val(memory.slice(0, -1));



        } else{
            $(".go").addClass('homess');
            $(".go").removeClass('kto');
            sel =  localStorage.getItem("basketGoods");//получаємо дані з корзини
            $('.goods').html(sel);//виводжу з локал стореджа на сторінку
            console.log('f,kz');

        }



        var goods={
            model: $('#Mode').val(), //ось тут повставляй з інпутів
            memory: $('#Memory').val(),//і тут
            name: $('#Godname').val(),//і тут
            count:$('#God').val()
        }

      if(localStorage.getItem("basketGoods")!=="") {
          $.ajax({

              type: "POST",
              contentType: 'application/json; charset=utf-8',
              data: JSON.stringify(goods),
              dataType: "json",
              url: '/current_user_name',
              success: function (response) {

                  localStorage.setItem("basketGoods", "");
                  $('#God').val("");
                  $('#Godname').val("");
                  $('#Mode').val("");
                  $('#Memory').val("");
                  countOfItem();

                  let select=" ";

                  for(var i = 0 ;i<response.length;i++)
                  {
                      select += '' +
                          '<li class="row all">\n' +
                          '<span class="quantity minus">-</span><span class="quantity much">'+response[i].count+ '</span><span class="quantity plus">+</span>\n' +
                          '<span class="itemName">' +'<span class="names" >' + response[i].goods.name + '</span ><span class="mode" >'+ response[i].goods.model + '</span > <span class="gg" >'+response[i].memory + '</span >GB'+ '</span>\n' +
                          '<span class="popbtn">X</span>\n' +
                          '<span class="price">' + response[i].price + '$</span>\n' +
                          '</li>';
                      $('.goods').html(select);
                      countOfItem();

                  }


                  Plus(countProducts);
                  Minus();
                  Delete();
                  howMuch();
                  countOfItem()





              },




              error: function (response) {

                  $('#God').val("");
                  $('#Godname').val("");
                  $('#Mode').val("");
                  $('#Memory').val("");



              }
          });
      }else if (localStorage.getItem("basketGoods")===""){

          $('#God').val("");
          $('#Godname').val("");
          $('#Mode').val("");
          $('#Memory').val("");

          $.ajax({

              type: "POST",
              contentType: 'application/json; charset=utf-8',
              data: JSON.stringify(goods),
              dataType: "json",
              url: '/get-goods-from-basket',
              success: function (response) {

/*
                  $('#user_name').html(response.userName + ' !');
                  $('#Number').val(response[0].mobileNumber);*/
                  $('#God').val("");
                  $('#Godname').val("");
                  $('#Mode').val("");
                  $('#Memory').val("");
                  let select= $('.goods').html();

                  for(var i = 0 ;i<response.length;i++)
                  {
                      select += '' +
                          '<li class="row all">\n' +
                          '<span class="quantity minus">-</span><span class="quantity much">'+response[i].count+ '</span><span class="quantity plus">+</span>\n' +
                          '<span class="itemName">' +'<span class="names" >' + response[i].goods.name + '</span ><span class="mode" >'+ response[i].goods.model + '</span > <span class="gg" >'+response[i].memory + '</span >GB'+ '</span>\n' +
                          '<span class="popbtn">X</span>\n' +
                          '<span class="price">' + response[i].price + '$</span>\n' +

                          '</li>';
                      $('.goods').html(select);
                      countOfItem();
                  }
                  Plus(countProducts);
                  Minus();
                  Delete();
                  howMuch();
                  countOfItem()






              },

              error: function (response) {


                  $('#God').val("");
                  $('#Godname').val("");
                  $('#Mode').val("");
                  $('#Memory').val("");


              }
          });












      }






        $('.goBack').remove();
        $('#one').css("display", "none");
        $('#two').css("display", "none");

        $('.header').css("display", "none");

        $('.koronovirys').css("display", "block");


        $('head').append('<link rel="stylesheet" href="css/custom.css" type="text/css" />');
        $("p:last").addClass("selected highlight");

        $("#list > li").children("ul").slideUp();




        $('.homess').click(function(){

            $('.koronovirys').css("display", "none");
            $('#two').css("display", "block");
            $('.goBack').remove();
            $('.header').css("display", "block");

            $("link[href*='css/custom.css']").remove();

            $("#list > li").children("ul").slideUp();
            countOfItem();

            Back();

        });





        $('.kto').click(function(){

            $('.koronovirys').css("display", "none");

            $('.header').css("display", "block");

            $('.goBack').remove();
            $('#one').css("display", "block");

            $("link[href*='css/custom.css']").remove();

            $("#list > li").children("ul").slideUp();


        });




        Plus(countProducts);
        Minus();
        Delete();
        howMuch();
        countOfItem();






    });





});




$(function() {

    $('#add').click(function(){

     var c=   $('#countOfGoods').text();
        $('#countOfGoods').html(+c+1);

        $('#God').val("");
        $('#Godname').val("");
        $('#Mode').val("");
        $('#Memory').val("");




        let select="";
        select += '' +
            '<li class="row all">\n' +
            '<span class="quantity minus">-</span><span class="quantity much">'+1+ '</span><span class="quantity plus">+</span>\n' +
            '<span class="itemName">' +$('#name-model').html() +'</span>\n' +
            '<span class="popbtn">X</span>\n' +
            '<span class="price">' + $('#price').html() + '</span>\n' +
            '</li>';



    //    $('.goods').html(select);


        var result = select;

        console.log("SHIT : " + result);
        let firstIndexForName =result.lastIndexOf("<span class=\"names\">");
        let lastIndexForName=result.indexOf("</span><span class=\"mode\">");

        let firstIndexForModel =result.lastIndexOf("<span class=\"mode\">");
        let lastIndexForModel=result.indexOf("</span> <span class=\"gg\">");

        let firstIndexForMemory =result.lastIndexOf("<span class=\"gg\">");
        let lastIndexForMemory=result.indexOf("</span>GB</span>");

        let firstIndexForCount =result.lastIndexOf("<span class=\"quantity much\">");
        let lastIndexForCount=result.indexOf("</span><span class=\"quantity plus\">");


        console.log(firstIndexForCount);
        console.log(lastIndexForCount);


        let nameOfgoods=result.substring(firstIndexForName+20,lastIndexForName);
        let modelOfGoods=result.substring(firstIndexForModel+19,lastIndexForModel);
        let memoryOfGoods=result.substring(firstIndexForMemory+17,lastIndexForMemory);
        let countOfGoods=result.substring(firstIndexForCount+28,lastIndexForCount);


        console.log(" name = " + nameOfgoods)
        console.log("model = " + modelOfGoods);
        console.log("memory = " + memoryOfGoods);
        console.log("count = " + countOfGoods);


        var data={
            name:nameOfgoods,
            model:modelOfGoods,
            memory:memoryOfGoods,
            count:countOfGoods

        };
        jQuery.post("is-user-auth",function(response) {
            if(response === 'yes') {



                $.ajax({
                    type: "POST",
                    contentType : 'application/json; charset=utf-8',
                    data: JSON.stringify(data),
                    dataType : "json",
                    url: 'current_user_name',

                    success :function(response){

                        $('#God').val("");
                        $('#Godname').val("");
                        $('#Mode').val("");
                        $('#Memory').val("");

                    },
                    error:function(response,textStatus) {
                        console.log("error in delete from db " + textStatus);

                        $('#God').val("");
                        $('#Godname').val("");
                        $('#Mode').val("");
                        $('#Memory').val("");


                    }
                });



            } else {
                sel =  localStorage.getItem("basketGoods");
                console.log(sel);
                localStorage.setItem("basketGoods",select+sel);

                $('#God').val("");
                $('#Godname').val("");
                $('#Mode').val("");
                $('#Memory').val("");

               $('.goods').html(select);

            }
        });











    })



});



$(function() {

    $('.bigcart').click(function(){

        var src = $(".first").attr('src');
        $('.header').css("display", "block");
        $('.tab-pane img').attr("src", src);

        $('.goBack').remove();

        $('.koronovirys').css("display", "none");
        $('#one').css("display", "block");
        $('.models,.memory').html('');
        $("link[href*='css/custom.css']").remove();
        $("link[href*='css/main1.css']").remove();
        $("link[href*='css/order.css']").remove();

        $("#list > li").children("ul").slideUp();


        if(localStorage.getItem("basketGoods")===null){
            localStorage.setItem("basketGoods",'');
        }

      /*  sel =  localStorage.getItem("basketGoods");


        count = sel.length;
        var res = 0;

        for(i=0; i < count; i++){

            if(sel.charAt(i) == "-"){
                res++;
            }

        }

        $('#countOfGoods').html(res);
*/



        jQuery.post("is-user-auth",function(response) {
            if(response === 'yes') {


                $.ajax({
                    type: "POST",
                    contentType : 'application/json; charset=utf-8',
                    dataType : "text",
                    url: '/get-count-goods',

                    success :function(response){


                        $('#countOfGoods').html(response);


                    },
                    error:function(response,textStatus) {


                    }
                });



            } else {

                sel =  localStorage.getItem("basketGoods");

                let count = sel.length;
                var res = 0;

                for(i=0; i < count; i++){

                    if(sel.charAt(i) == "-"){
                        res++;
                    }

                }

                $('#countOfGoods').html(res);

            }
        });




    });
});



// функції
function  Back() {


    $('#one').css("display", "none");

    let sel= $('.carting ul').html();
    sel+= '' + '<li><button class="btn goBack">BACK</button></li>';


    $('.carting ul').html(sel);

    $('.goBack').click(function(){

        $('#two').css("display", "none");

        var src = $(".first").attr('src');

        $('.tab-pane img').attr("src", src);

        $('.goBack').remove();

        $('#one').css("display", "block");

        $('.models,.memory').html('');
        $("link[href*='css/main1.css']").remove();

        $("#list > li").children("ul").slideUp();


    });


}

function  howMuch() {
    let price=0;

    list = document.querySelectorAll(".price");
    console.log(list);

    for ( let i=0; i< list.length; i++) {
        console.log(list[i].textContent.substring(0, list[i].textContent.length-1))
        price=price+Number(list[i].textContent.substring(0, list[i].textContent.length - 1));

        console.log(   "ціни які додаються " +Number(list[i].textContent.substring(0, list[i].textContent.length -1 )));


    }

    $('.totalPrice').html(price+"$");


}

function  Delete() {
    $('.popbtn').click(function (event) {

        let selectBeforeDelete = $('.goods').html();
        countOfItem()
        $(this).parent().remove();
        howMuch();
        let select = $('.goods').html();
        var basketGoods = document.getElementById("basketGoods").innerHTML





        var result = selectBeforeDelete.replace(select,"");

        console.log("SHIT : " + result);
 let firstIndexForName =result.lastIndexOf("<span class=\"names\">");
 let lastIndexForName=result.indexOf("</span><span class=\"mode\">");

 let firstIndexForModel =result.lastIndexOf("<span class=\"mode\">");
 let lastIndexForModel=result.indexOf("</span> <span class=\"gg\">");

        let firstIndexForMemory =result.lastIndexOf("<span class=\"gg\">");
        let lastIndexForMemory=result.indexOf("</span>GB</span>");

        let firstIndexForCount =result.lastIndexOf("<span class=\"quantity much\">");
        let lastIndexForCount=result.indexOf("</span><span class=\"quantity plus\">");


console.log(firstIndexForCount);
        console.log(lastIndexForCount);


        let nameOfgoods=result.substring(firstIndexForName+20,lastIndexForName);
         let modelOfGoods=result.substring(firstIndexForModel+19,lastIndexForModel);
        let memoryOfGoods=result.substring(firstIndexForMemory+17,lastIndexForMemory);
        let countOfGoods=result.substring(firstIndexForCount+28,lastIndexForCount);


        console.log(" name = " + nameOfgoods)
console.log("model = " + modelOfGoods);
        console.log("memory = " + memoryOfGoods);
        console.log("count = " + countOfGoods);


        var data={
            name:nameOfgoods,
            model:modelOfGoods,
            memory:memoryOfGoods,
            count:countOfGoods

        };
        howMuch();
        countOfItem();
        jQuery.post("is-user-auth",function(response) {
            if(response === 'yes') {



                $.ajax({
                    type: "POST",
                    contentType : 'application/json; charset=utf-8',
                    data: JSON.stringify(data),
                    dataType : "text",
                    url: 'delete-from-basket',

                    success :function(response){

                        console.log("Everything is cool")
                        localStorage.setItem("basketGoods",'');


                    },
                    error:function(response,textStatus) {
                        console.log("error in delete from db " + textStatus);

                    }
                });






            } else {

                localStorage.setItem("basketGoods", select);

            }
        });







    });
}

function  Minus() {
    $('.minus').click(function (event) {
        let b = +$(this).next(".much").text();
        let a = +$(this).next(".much").text() - 1;
        if (a < 1) {
            a = 1;
        }
        $(this).next(".much").text(a);
      $(this).next().next().next().next().next().text((a * Number(($(this).next().next().next().next().next()).text().substring(0, ($(this).next().next().next().next().next()).text().length - 1))) / b + '$');


        howMuch();


        let select = $('.goods').html();

        localStorage.setItem("basketGoods", select);




    });
}

function  Plus(countProducts) {
    $('.plus').click(function (event) {


        let b = +$(this).prev(".much").text();
        let a = +$(this).prev(".much").text() + 1;

        if (a >= countProducts) {
            a = $(this).prev(".much").text();
        }

        $(this).prev(".much").text(a);
        $(this).next().next().next().text((a * Number(($(this).next().next().next()).text().substring(0, ($(this).next().next().next()).text().length - 1))) / b + '$');


        howMuch();
        let select = $('.goods').html();
        var basketGoods = document.getElementById("basketGoods").innerHTML

        localStorage.setItem("basketGoods", select);//записую в локал сторедж


    });
}



function countOfItem(){

    let y="";

    if (!($('.goods').html()=='')){
        y = document.querySelectorAll(".price");
        console.log(y);
        $('#countOfGoods').html(y.length);

    }
    else {
        $('#countOfGoods').html(y.length);
    }



}





function clearStorage(){
    localStorage.setItem("basketGoods", "");//записую в локал сторедж
}







